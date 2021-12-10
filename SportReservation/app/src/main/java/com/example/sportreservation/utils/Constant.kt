package com.example.sportreservation.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

const val NOTIFICATION_CHANNEL_NAME = "Order Channel"
const val NOTIFICATION_CHANNEL_ID = "notify-schedule"
const val NOTIFICATION_ID = 10
const val ID_REPEATING = 100

private val SINGLE_THREAD = Executors.newSingleThreadExecutor()
private val MAIN_THREAD = Handler(Looper.getMainLooper())
private const val SERVICE_LATENCY_IN_MILLIS: Long = 2500

fun singleThreadIO(t: () -> Unit) {
    SINGLE_THREAD.execute(t)
}

fun mainThreadDelay(t: () -> Unit)  {
    MAIN_THREAD.postDelayed(t, SERVICE_LATENCY_IN_MILLIS)
}

fun mainThread(t: () -> Unit) {
    MAIN_THREAD.post(t)
}

