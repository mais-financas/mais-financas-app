package com.neuralnet.maisfinancas.data.room.model.estatistica

import androidx.room.ColumnInfo
import java.math.BigDecimal

data class ValorTotalPorCategoria(
    @ColumnInfo("categoria") val categoria: String,
    @ColumnInfo("valor_total") val valorTotal: BigDecimal,
)
