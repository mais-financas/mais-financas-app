package com.neuralnet.maisfinancas.data.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.neuralnet.maisfinancas.model.despesa.Despesa
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

        if (despesa.definirLembrete) {
            alarmManager.set(
                AlarmManager.RTC,
                calendar.timeInMillis,
                pendingIntent
            )
        } else {
            alarmManager.cancel(pendingIntent)
            pendingIntent.cancel()
        }
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

}
