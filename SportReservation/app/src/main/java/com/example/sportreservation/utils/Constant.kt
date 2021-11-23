package com.example.sportreservation.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors



private val SINGLE_EXECUTOR = Executors.newSingleThreadExecutor()
private val MAIN_THREAD = Handler(Looper.getMainLooper())




fun diskIO(t: () -> Unit) {
    SINGLE_EXECUTOR.execute(t)
}

fun mainThread(t: () -> Unit) {
    MAIN_THREAD.post(t)
}