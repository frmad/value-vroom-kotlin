package sdu.mobile.xpence.ui.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.provider.Settings.System.getString
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.Constants.MessageNotificationKeys.TAG
import com.google.firebase.messaging.FirebaseMessaging
import sdu.mobile.xpence.R


actual object NotificationUtil {
    private lateinit var appContext: Context

    actual fun init(context: Any) {
        appContext = context as Context

    }

    actual fun createNotificationChannel(channelId: String, channelName: String, channelDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }
            val notificationManager: NotificationManager =
                appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    actual fun sendNotification(channelId: String, title: String, content: String) {
        println("PWEASE WORK")
        val builder = NotificationCompat.Builder(appContext, channelId)
            .setSmallIcon(androidx.core.R.drawable.ic_call_decline_low)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager: NotificationManager =
            appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, builder.build())
    }

    fun handleFirebaseMessage(data: Map<String, String>) {
        val title = data["title"] ?: "Default Title"
        val content = data["message"] ?: "Default Content"
        val channelId = "8"

        createNotificationChannel(channelId, "Channel Name", "Channel Description")

        sendNotification(channelId, title, content)
    }
}