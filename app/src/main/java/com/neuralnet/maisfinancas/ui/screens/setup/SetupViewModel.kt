package com.neuralnet.maisfinancas.ui.screens.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.data.repository.ObjetivoRepository
import com.neuralnet.maisfinancas.data.repository.SugestoesRepository
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import com.neuralnet.maisfinancas.model.despesa.Categoria
import com.neuralnet.maisfinancas.model.despesa.Frequencia
import com.neuralnet.maisfinancas.model.despesa.Recorrencia
import com.neuralnet.maisfinancas.ui.screens.ConnectionState
import com.neuralnet.maisfinancas.util.FieldValidationError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class SetupViewModel @Inject constructor(
    private val despesaRepository: DespesaRepository,
    private val objetivoRepository: ObjetivoRepository,
    sugestoesRepository: SugestoesRepository,
    gestorRepository: GestorRepository,
) : ViewModel() {

    private val gestor: Flow<GestorEntity?> = gestorRepository.getGestor()

    private val _connectionState: MutableStateFlow<ConnectionState> =
        MutableStateFlow(ConnectionState.Idle)
    val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    private val _uiState: MutableStateFlow<SetupUiState> = MutableStateFlow(SetupUiState())
    val uiState: StateFlow<SetupUiState> = _uiState.asStateFlow()

    private val _selectedItem: MutableStateFlow<ItemDespesa?> = MutableStateFlow(null)
    val selectedItem: StateFlow<ItemDespesa?> = _selectedItem.asStateFlow()

    private val selectedCategoria: MutableStateFlow<Categoria?> = MutableStateFlow(null)

    init {
        viewModelScope.launch {
            _uiState.value = SetupUiState(despesas = sugestoesRepository.getDespesasComuns())
        }
    }

    fun selecionarItem(categoria: Categoria, itemDespesa: ItemDespesa) {
        _selectedItem.update { itemDespesa }
        selectedCategoria.value = categoria
    }

    fun updateItem(itemDespesa: ItemDespesa) {
        _selectedItem.update { itemDespesa }
    }

    fun adicionarDespesa(itemDespesa: ItemDespesa) {
        updtateDespesas(itemDespesa) { item ->
            item.copy(selecionado = true)
        }
    }

    fun removerItem() {
        val selectedItem = checkNotNull(_selectedItem.value)
        updtateDespesas(selectedItem) { item ->
            item.copy(
                valor = "",
                dataEmMillis = Instant.now().toEpochMilli(),
                recorrencia = Recorrencia(Frequencia.NENHUMA),
                selecionado = false
            )
        }
    }

    fun resetSelection() {
        _selectedItem.value = null
        selectedCategoria.value = null
    }

    fun isItemValid(): Boolean {
        val item = checkNotNull(selectedItem.value)

        if (item.valor.toBigDecimalOrNull() == null) {
            _selectedItem.update { it?.copy(valorErrorField = FieldValidationError.NUMERO_INVALIDO) }
        }
        return _selectedItem.value?.isItemValid() ?: false
    }

    private fun updtateDespesas(
        itemDespesa: ItemDespesa,
        transform: (item: ItemDespesa) -> ItemDespesa
    ) {
        val categoria = checkNotNull(selectedCategoria.value)

        val despesas = uiState.value.despesas.toMutableMap()
        val despesasAtualizadas = despesas[categoria]?.map { despesa ->
            if (itemDespesa.nome == despesa.nome) {
                transform(itemDespesa)
            } else despesa
        }

        despesas[categoria] = despesasAtualizadas.orEmpty()
        _uiState.update { it.copy(despesas = despesas) }
        resetSelection()
    }

    fun inserirDespesas() = viewModelScope.launch {
        val gestorId = checkNotNull(gestor.first()?.id)

        val despesas = uiState.value.mapDespesasSelecionadasToList(gestorId)
        try {
            _connectionState.value = ConnectionState.Loading
            despesaRepository.registrarDespesas(despesas)
            _connectionState.value = ConnectionState.Success
        } catch (e: SocketTimeoutException) {
            _connectionState.value = ConnectionState.ServerUnavailable
        } catch (e: IOException) {
            _connectionState.value = ConnectionState.NoInternet
        } catch (e: Exception) {
            _connectionState.value = ConnectionState.Error
        }
    }

    fun inserirObjetivos() = viewModelScope.launch {
        val gestorId = checkNotNull(gestor.first()?.id)
        objetivoRepository.inserirObjetivos(gestorId)
    }

}
