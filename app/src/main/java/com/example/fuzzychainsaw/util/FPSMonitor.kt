package com.example.fuzzychainsaw.util

import android.os.Handler
import android.os.Looper
import android.view.Choreographer

object FPSMonitor {
    private var count = 0
    private var isFPSOpen = false
    private val handler by lazy {
        Handler(Looper.getMainLooper())
    }
    private val fpsRunnable by lazy {
        FPSRunnable()
    }
    val listener = mutableListOf<(Int) -> Unit>()

    class FPSRunnable : Choreographer.FrameCallback, Runnable {
        override fun doFrame(frameTimeNanos: Long) {
            count++
            Choreographer.getInstance().postFrameCallback(this)
        }

        override fun run() {
            listener.forEach {
                it.invoke(count)
            }
            count = 0
            handler.postDelayed(this, 1000)
        }
    }


    fun start() {
        if (!isFPSOpen) {
            isFPSOpen = true
            Choreographer.getInstance().postFrameCallback(fpsRunnable)
            handler.postDelayed(fpsRunnable, 1000)
        }
    }

    fun stop() {
        if (isFPSOpen) {
            isFPSOpen = false
            count = 0
            Choreographer.getInstance().removeFrameCallback(fpsRunnable)
            handler.removeCallbacks(fpsRunnable)
        }
    }
}