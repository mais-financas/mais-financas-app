package com.neuralnet.maisfinancas.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.neuralnet.maisfinancas.data.room.model.estatistica.ValorTotalPorCategoria
import kotlinx.coroutines.flow.Flow

@Dao
interface EstatisticaDao {

    @Query(
        "SELECT categoria.nome_categoria AS categoria, SUM(registro_despesa.valor) AS valor_total " +
                "FROM categoria " +
                "INNER JOIN despesa ON categoria.categoria_id = despesa.categoria_id " +
                "LEFT JOIN registro_despesa ON despesa.despesa_id = registro_despesa.despesa_id " +
                "WHERE strftime('%Y-%m', datetime(registro_despesa.data / 1000, 'unixepoch')) = :mes " +
                "GROUP BY categoria.categoria_id"
    )
    fun getDespesasPorCategoriaPeloMes(mes: String): Flow<List<ValorTotalPorCategoria>>

}
