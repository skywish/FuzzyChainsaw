package com.example.fuzzychainsaw.view.qqmanager

import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.PointF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View

/**
 * @Author banli
 * @Date 2020/11/18
 * @Description
 */
class ShieldView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val path = Path()
    private val dstPath = Path()
    private var linearGradientLength = 0f
    private var linearGradient: LinearGradient? = null
    private var linearGradient2: LinearGradient? = null

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }
    private val paint2 = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }
    private var measure = PathMeasure()
    private val pointL = PointF()
    private val pointT = PointF()
    private val pointR = PointF()
    private val pointB = PointF()
    private var process = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        pointL.set(0f, h / 6f)
        pointR.set(w.toFloat(), h / 6f)
        pointT.set(w / 2f, 0f)
        pointB.set(w / 2f, h.toFloat())
        path.moveTo(pointL.x, pointL.y)
        path.lineTo(pointT.x, pointT.y)
        path.lineTo(pointR.x, pointR.y)
        path.quadTo(w.toFloat(), h / 3f * 2, pointB.x, pointB.y)
        path.quadTo(0f, h / 3f * 2, pointL.x, pointL.y)
        measure = PathMeasure(path, false)
        linearGradientLength = measure.length * 0.2f
        paint.shader = LinearGradient(
            0f, 0f, linearGradientLength, 0f,
            0x00FFFFFF, 0xFFFFFFFF.toInt(), Shader.TileMode.CLAMP
        )
        paint2.shader = LinearGradient(
            0f, 0f, linearGradientLength, 0f,
            0xFFFFFFFF.toInt(), 0x00FFFFFF, Shader.TileMode.CLAMP
        )
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        dstPath.reset()
        measure.getSegment(
            0f,
            measure.length * 0.4f,
            dstPath,
            true
        )
        canvas?.drawPath(dstPath, paint)
        dstPath.reset()
        measure.getSegment(
            measure.length * 0.5f,
            measure.length * 0.9f,
            dstPath,
            true
        )
        canvas?.drawPath(dstPath, paint2)
    }
}