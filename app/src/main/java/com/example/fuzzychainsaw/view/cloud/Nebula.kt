package com.example.fuzzychainsaw.view.cloud

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.fuzzychainsaw.util.toRadians
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

/**
 * 仿网易云音乐星云效果
 */
class Nebula(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 6f
        color = Color.WHITE
    }

    private var starsList: MutableList<Star> = mutableListOf()
    var maxOffset = 200
    private var centerX: Int = 0
    private var centerY: Int = 0
    private var radius: Float = 0f
    private var animation = ValueAnimator.ofFloat(0f, 1f).apply {
        repeatCount = -1
        duration = 2000
        interpolator = LinearInterpolator()
        addUpdateListener {
            updateStars()
            invalidate()
        }
    }

    private fun updateStars() {
        starsList.forEach {
            it.offset += it.speed
            if (it.offset >= maxOffset) {
                it.alpha = 255
                it.offset = 0f
                it.angleInRadians += (-10..10).random().toRadians()
                it.x = centerX + cos(it.angleInRadians) * radius
                it.y = centerY - sin(it.angleInRadians) * radius
            } else {
                it.alpha = ((1 - it.offset / maxOffset) * 255).toInt()
                it.x = centerX + cos(it.angleInRadians) * (radius + it.offset)
                it.y = centerY - sin(it.angleInRadians) * (radius + it.offset)
//                it.x += cos(it.angleInRadians) * it.offset
//                it.y -= sin(it.angleInRadians) * it.offset
            }
        }
    }

    private fun generateStars(starSize: Int) {
        for (i in 0 until starSize) {
            val angle = (0 until 360).random().toRadians()
            val x = centerX + cos(angle) * radius
            val y = centerY - sin(angle) * radius
            val speed = (3..12).random().toFloat()
            val randomMaxOffset: Int = (maxOffset / 2..maxOffset).random()
            starsList.add(Star(x, y, angle, speed, 0f, randomMaxOffset, 255))
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2
        centerY = h / 2
        radius = min(w, h) / 2f - maxOffset
        generateStars(300)
        animation.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        starsList.forEach {
            canvas?.drawPoint(it.x, it.y, paint)
        }
    }
}