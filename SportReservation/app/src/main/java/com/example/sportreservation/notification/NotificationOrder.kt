package com.example.sportreservation.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.utils.ID_REPEATING
import com.example.sportreservation.utils.singleThreadIO
import java.util.*

@SuppressLint("UnspecifiedImmutableFlag")
class NotificationOrder: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        singleThreadIO {

        }
    }

    fun setRemainderOrder(context: Context) {
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
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationOrder::class.java).let {
            PendingIntent.getBroadcast(context, ID_REPEATING, it, 0)
        }
        intent.cancel()
        alarmManager.cancel(intent)
    }

    private fun showNotification(context: Context, orderList: List<OrderEntity>) {

    }

    companion object {
        private const val ALARM_HOURS = 5
        private const val ALARM_MINUTE = 30
    }

}