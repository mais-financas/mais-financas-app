package com.neuralnet.maisfinancas.data.alarm

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.neuralnet.maisfinancas.R

const val CHANNEL_ID = "lembretes_channel"
const val TITULO_DESPESA_EXTRA = "titulo_despesa"
const val DESPESA_ID_EXTRA = "id_despesa"

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val despesaId = intent.getIntExtra(DESPESA_ID_EXTRA, -1)
        val nomeDespesa = intent.getStringExtra(TITULO_DESPESA_EXTRA)
        val notification: Notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(context.getString(R.string.lembrete_despesa_mensagem, nomeDespesa))
            .setContentText(context.getString(R.string.mensagem_lembrete, nomeDespesa))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(despesaId, notification)
    }
}
