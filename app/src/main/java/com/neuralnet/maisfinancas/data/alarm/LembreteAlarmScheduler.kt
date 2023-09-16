package com.neuralnet.maisfinancas.data.alarm

import com.neuralnet.maisfinancas.model.Despesa
import java.util.Calendar

interface LembreteAlarmScheduler {

    fun definirAlarme(calendar: Calendar, despesa: Despesa)

    fun cancelarAlarme(despesa: Despesa)

}