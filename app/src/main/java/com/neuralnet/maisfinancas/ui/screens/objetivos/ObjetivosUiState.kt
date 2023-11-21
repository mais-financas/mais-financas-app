package com.neuralnet.maisfinancas.ui.screens.objetivos

import com.neuralnet.maisfinancas.data.datasource.SugestoesDatasource
import com.neuralnet.maisfinancas.model.objetivo.Objetivo

data class ObjetivosUiState(val objetivos: List<Objetivo> = SugestoesDatasource.objetivos)
