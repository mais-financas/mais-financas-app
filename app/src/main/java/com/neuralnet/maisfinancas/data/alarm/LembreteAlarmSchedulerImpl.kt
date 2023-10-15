package com.neuralnet.maisfinancas.data.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.neuralnet.maisfinancas.model.despesa.Despesa
import com.neuralnet.maisfinancas.model.despesa.Recorrencia
import com.neuralnet.maisfinancas.model.despesa.Frequencia.ANUAL
import com.neuralnet.maisfinancas.model.despesa.Frequencia.DIARIA
import com.neuralnet.maisfinancas.model.despesa.Frequencia.MENSAL
import com.neuralnet.maisfinancas.model.despesa.Frequencia.NENHUMA
import com.neuralnet.maisfinancas.model.despesa.Frequencia.SEMANAL
import java.util.Calendar

class LembreteAlarmSchedulerImpl(
    private val context: Context,
) : LembreteAlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)
    private lateinit var pendingIntent: PendingIntent

    override fun definirAlarme(calendar: Calendar, despesa: Despesa) {
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra(DESPESA_ID_EXTRA, despesa.id.toInt())
            putExtra(TITULO_DESPESA_EXTRA, despesa.nome)
        }
        pendingIntent = PendingIntent.getBroadcast(
            context,
            despesa.id.toInt(),
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        alarmManager.setInexactRepeating(
            AlarmManager.RTC,
            calendar.timeInMillis,
            recorrenciaEmMillis(despesa.recorrencia),
            pendingIntent
        )
    }

    override fun cancelarAlarme(despesa: Despesa) {
        try {
            alarmManager.cancel(pendingIntent)
        } catch (e: Exception) {
            Log.d(
                "LembreteAlarmScheduler",
                "cancelarAlarme: não pode ser cancelado antes de ser definido"
            )
        }
    }

    private fun recorrenciaEmMillis(recorrencia: Recorrencia): Long {
        val calendar = Calendar.getInstance().apply {clear() }

        when (recorrencia.frequencia) {
            DIARIA -> calendar.add(Calendar.DAY_OF_YEAR, recorrencia.quantidade)
            SEMANAL -> calendar.add(Calendar.WEEK_OF_YEAR, recorrencia.quantidade)
            MENSAL -> calendar.add(Calendar.MONTH, recorrencia.quantidade)
            ANUAL -> calendar.add(Calendar.YEAR, recorrencia.quantidade)
            else -> throw Exception("Impossível definir lembrete para $NENHUMA")
        }

        return calendar.timeInMillis.also { println(it) }
    }
}
