package com.example.fuzzychainsaw.view

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

class TestView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

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
    var progress = 0
        set(value) {
            field = value
            invalidate()
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {

    }
}