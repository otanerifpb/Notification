package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // Declaração das variáveis do Back-End
    private lateinit var btEnviarMain: Button
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val canalId = "i.apps.notifications"
    private val descricao = "Hello World!!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sincronizar Back-End com Front-End
        this.btEnviarMain = findViewById(R.id.btEnviarMain)
        //this.tvTextoAfter = findViewById(R.id.txTextoAfter)

        // Config da Class para a notificação
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Config do click do button, que chama a fun enviar()
        this.btEnviarMain.setOnClickListener {enviar()}
    }

    fun enviar() {
        // Config do intent para chamar a Tela 2
        val intent = Intent(this@MainActivity, AfterActivity::class.java)

        // Para verificar se tem intent pendente
        //val pendingIntent = PendingIntent.getActivity(this@MainActivity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val pendingIntent = PendingIntent.getActivity(this@MainActivity, 0, intent, PendingIntent.FLAG_MUTABLE)

        // Para usar a Activity diferente da atual
        val contentView = RemoteViews(packageName, R.layout.activity_after)

        // Verificar a versão do Android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(canalId, descricao, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, canalId)
                .setContent(contentView)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                .setContentIntent(pendingIntent)
        } else {

            builder = Notification.Builder(this)
                .setContent(contentView)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1234, builder.build())
    }
}