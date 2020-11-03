package com.example.fuzzychainsaw.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.PointF
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.fuzzychainsaw.util.measureFunTimeMills

/**
 * 比较drawArc drawPath
 */
class CompeteView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val TAG = "CompeteView"

    private var position: PointF = PointF()

    private val rectF = RectF()

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        strokeWidth = 10f
        style = Paint.Style.STROKE
    }

    private val path = Path()
    private var measure = PathMeasure()
    private val dstPath = Path()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        position.set(w / 2f, h / 2f)
        rectF.set(w / 2f - 100, h / 2f - 100, w / 2f + 100, h / 2f + 100)
        path.addCircle(w / 2f, h / 2f, 100f, Path.Direction.CW)
        measure = PathMeasure(path, false)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val time = measureFunTimeMills { drawArc(canvas) }
        Log.i(TAG, "drawArc: $time")
        val time2 = measureFunTimeMills { drawPath(canvas) }
        Log.i(TAG, "drawPath: $time2")
    }

    private fun drawArc(canvas: Canvas?) {
        for (i in 1..10000) {
            canvas?.drawArc(rectF, 0f, 180f, false, paint)
        }
    }

    private fun drawPath(canvas: Canvas?) {
        val length = measure.length
        dstPath.reset()
        measure.getSegment(0f, 0.5f * length, dstPath, true)
        for (i in 1..10000) {
            canvas?.drawPath(dstPath, paint)
        }
    }
}