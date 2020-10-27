package com.example.fuzzychainsaw.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

class ProcessBar(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var bgColor: Int = Color.GRAY
        set(value) {
            field = value
            paint.color = bgColor
        }
    var padding: Float = 0f
    var process: IProcess? = null
    var percentage: Int by Delegates.observable(0) { _, old, new ->
        process?.updateProcess(old, new)
        postInvalidate()
    }
    var round: Float = 10f
    var processRound: Float = 6f
    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = bgColor
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), round, round, paint)
        process?.draw(canvas, this)
    }
}