package com.example.fuzzychainsaw.view.load

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View
import kotlin.math.abs

/**
 * 加载动画转圈效果
 */
class LoadView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val path = Path()
    private val dst = Path()
    private var measure = PathMeasure()
    private var value: Float = 0.5f

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 10f
        color = Color.BLUE
        style = Paint.Style.STROKE
    }

    private val animation = ValueAnimator.ofFloat(0f, 1f).apply {
        repeatCount = ValueAnimator.INFINITE
        duration = 2000
        addUpdateListener {
            value = animatedValue as Float
            invalidate()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.reset()
        path.addCircle(w / 2f, h / 2f, 100f, Path.Direction.CW)
        measure = PathMeasure(path, false)
        animation.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // 增强动画效果
        canvas?.rotate(360.0f * value - 45.0f, width / 2f, height / 2f);
        dst.reset()
        val end = value * measure.length
        val start = end - (0.5f - abs(value - 0.5f)) * measure.length
        measure.getSegment(start, end, dst, true)
        canvas?.drawPath(dst, paint)
    }
}