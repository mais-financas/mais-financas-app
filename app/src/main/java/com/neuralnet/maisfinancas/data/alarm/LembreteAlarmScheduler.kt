package com.neuralnet.maisfinancas.data.alarm

import com.neuralnet.maisfinancas.model.despesa.Despesa
import java.util.Calendar

interface LembreteAlarmScheduler {

    fun definirAlarme(calendar: Calendar, despesa: Despesa)

    fun cancelarAlarme(despesa: Despesa)

}
