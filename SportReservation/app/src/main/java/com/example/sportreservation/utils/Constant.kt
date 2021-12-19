package com.example.sportreservation.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.webkit.MimeTypeMap
import androidx.core.app.NotificationCompat
import com.example.sportreservation.R
import java.util.concurrent.Executors

const val NOTIFICATION_CHANNEL_NAME = "Order Channel"
const val NOTIFICATION_CHANNEL_ID = "notify-schedule"
const val NOTIFICATION_ID = 10
const val ID_REPEATING = 100

const val CUSTOM_NOTIFICATION_NAME = "custom-notfication"
const val CUSTOM_NOTIFICATION_CHANNEL_ID = "notify-custom"
const val CUSTOM_NOTIFICATION_ID = 10001

private val SINGLE_THREAD = Executors.newSingleThreadExecutor()
private val MAIN_THREAD = Handler(Looper.getMainLooper())
private const val SERVICE_LATENCY_IN_MILLIS: Long = 2500

fun singleThreadIO(t: () -> Unit) {
    SINGLE_THREAD.execute(t)
}

fun mainThreadDelay(t: () -> Unit) {
    MAIN_THREAD.postDelayed(t, SERVICE_LATENCY_IN_MILLIS)
}

fun mainThread(t: () -> Unit) {
    MAIN_THREAD.post(t)
}

fun isValidEmail(email: CharSequence): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun setNotification(context: Context, title: String, msg: String) {
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val builder = NotificationCompat.Builder(context, CUSTOM_NOTIFICATION_NAME)
        .setSmallIcon(R.drawable.ic_notifications_active_24)
        .setContentTitle(title)
        .setContentText(msg)
        .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))

    val channel = NotificationChannel(
        CUSTOM_NOTIFICATION_CHANNEL_ID,
        CUSTOM_NOTIFICATION_NAME,
        NotificationManager.IMPORTANCE_DEFAULT
    )

    builder.setChannelId(CUSTOM_NOTIFICATION_CHANNEL_ID)
    notificationManager.createNotificationChannel(channel)
    notificationManager.notify(CUSTOM_NOTIFICATION_ID, builder.build())
}

fun getExtension(uri: Uri, context: Context): String? {
    val cr: ContentResolver = context.contentResolver
    val mimeTypeMap = MimeTypeMap.getSingleton()
    return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri))
}