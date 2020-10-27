package com.example.fuzzychainsaw.view

import android.graphics.*

class MultiColorProcess(private val colorMap: Map<IntRange, IntArray>) : IProcess {
    private var rectF = RectF()
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    private var curColorArray: IntArray = intArrayOf(Color.RED)

    override fun draw(canvas: Canvas?, processBar: ProcessBar) {
        rectF.apply {
            left = processBar.padding
            right = processBar.width * processBar.percentage / 100f - processBar.padding
            top = processBar.padding
            bottom = processBar.height - processBar.padding
        }
        canvas?.drawRoundRect(rectF, processBar.processRound, processBar.processRound, paint)
    }

    override fun updateProcess(old: Int, new: Int) {
        for ((key, value) in colorMap) {
            if (new in key) {
                curColorArray = value
                paint.shader = LinearGradient(rectF.left, rectF.top, rectF.right, rectF.bottom, curColorArray,
                        null, Shader.TileMode.CLAMP)
                return
            }
        }
    }
}