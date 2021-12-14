package com.example.sportreservation.notification

import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.sportreservation.R
import com.example.sportreservation.data.SportReservationRepository
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.ui.order.OrderActivity
import com.example.sportreservation.utils.*
import org.koin.java.KoinJavaComponent.inject
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("UnspecifiedImmutableFlag")
class NotificationOrder: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        singleThreadIO {
            val repository : SportReservationRepository by inject(SportReservationRepository::class.java)

            val date = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

            val orderList = repository.getOrderByDate(dateFormat.format(date))
            
            if(orderList.isNotEmpty()) {
                showNotification(context, orderList)
            }
        }
    }

    fun setRemainderOrder(context: Context) {
        Log.d(TAG, "setRemainderOrder: isRunning . . .")
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, ALARM_HOURS)
            set(Calendar.MINUTE, ALARM_MINUTE)
        }
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(context, NotificationOrder::class.java).let { intent ->
            PendingIntent.getBroadcast(context, ID_REPEATING, intent, 0)
        }

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmIntent
        )
    }

    fun cancelRemainderOrder(context: Context) {
        Log.d(TAG, "cancelRemainderOrder: cancel . . .")
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationOrder::class.java).let {
            PendingIntent.getBroadcast(context, ID_REPEATING, it, 0)
        }
        intent.cancel()
        alarmManager.cancel(intent)
    }

    private fun showNotification(context: Context, orderList: List<OrderEntity>) {
        val intent = Intent(context, OrderActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT,)
        }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationStyle = NotificationCompat.InboxStyle()

        val timeString = context.getString(R.string.notification_message_format)
        orderList.forEach {
            val orderData = String.format(timeString, it.startTime, it.endTime, it.name)
            notificationStyle.addLine(orderData)
        }

        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_NAME)
            .setSmallIcon(R.drawable.ic_notifications_active_24)
            .setContentTitle(context.getString(R.string.notification_content_title))
            .setVibrate(longArrayOf(1000,1000,1000,1000,1000))
            .setContentIntent(pendingIntent)


        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        builder.setChannelId(NOTIFICATION_CHANNEL_ID)
        notificationManager.createNotificationChannel(channel)

        builder.setStyle(notificationStyle)
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    companion object {
        private const val ALARM_HOURS = 5
        private const val ALARM_MINUTE = 30
    }
}