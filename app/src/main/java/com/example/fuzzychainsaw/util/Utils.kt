package com.example.fuzzychainsaw.util

import android.os.SystemClock
import kotlin.math.PI


fun Int.toRadians(): Float {
    return (this / 180.0 * PI).toFloat()
}

fun measureFunTimeMills(method: () -> Unit): Long {
    val start = SystemClock.currentThreadTimeMillis()
    method.invoke()
    return SystemClock.currentThreadTimeMillis() - start
}

class Utils {
}