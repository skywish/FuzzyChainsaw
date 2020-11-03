package com.example.fuzzychainsaw.view.load

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View

/**
 * 圆形进度条
 * Date: 2020/10/29
 */
class ProgressView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 30f
        color = Color.RED
        // 线头形状
        strokeCap = Paint.Cap.ROUND
    }

    private val textPaint = Paint().apply {
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
        textSize = 100f
        color = Color.BLACK
    }

    private val path = Path()
    private val dstPath = Path()
    private var measure = PathMeasure()
    private var progress = 0
        set(value) {
            field = value
            invalidate()
        }

    private val keyframe1 = Keyframe.ofInt(0f, 0)
    private val keyframe2 = Keyframe.ofInt(0.5f, 100)
    private val keyframe3 = Keyframe.ofInt(1f, 80)
    private val holder =
        PropertyValuesHolder.ofKeyframe("progress", keyframe1, keyframe2, keyframe3)
    private val objectAnimator =
        ObjectAnimator.ofPropertyValuesHolder(this, holder).apply {
            repeatCount = -1
            duration = 4000
        }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.addCircle(w / 2f, h / 2f, 200f, Path.Direction.CW)
        measure = PathMeasure(path, false)
        animation()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        canvas?.rotate(90f, width / 2f, height / 2f)
        dstPath.reset()
        measure.getSegment(
            measure.length * 0.1f,
            measure.length * 0.1f + progress / 100f * measure.length * 0.8f,
            dstPath,
            true
        )
        canvas?.drawPath(dstPath, paint)
        canvas?.restore()
        canvas?.drawText(
            "$progress%",
            width / 2f,
            // (baseline + top + baseline + bottom) / 2 = height / 2 => y == baseline
            (height - textPaint.fontMetrics.top - textPaint.fontMetrics.bottom) / 2,
            textPaint
        )
    }

    private fun animation() {
        objectAnimator.start()
    }
}