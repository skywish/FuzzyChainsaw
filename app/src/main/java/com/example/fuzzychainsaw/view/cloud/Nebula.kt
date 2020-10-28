package com.example.fuzzychainsaw.view.cloud

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.sin

/**
 * 仿网易云音乐星云效果
 */
class Nebula(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    init {

    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 2f
    }

    private var floatArray = floatArrayOf()

    private fun generateStars(centerX: Int, centerY: Int, radius: Int, starSize: Int): FloatArray {
        val result = FloatArray(starSize * 2)
        for (i in 0...)
        return result
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawPoints()
    }
}