package com.example.fuzzychainsaw.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.min
import kotlin.properties.Delegates


class RoundImage(context: Context?, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

//    var bitmap: Bitmap? by Delegates.observable(null) { _, oldValue, newValue ->
//        if (newValue != null) {
//            paint.shader = BitmapShader(newValue, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
//            invalidate()
//        }
//    }

    override fun setImageResource(resId: Int) {
        super.setImageResource(resId)
        paint.shader = BitmapShader(
            (drawable as BitmapDrawable).bitmap,
            Shader.TileMode.CLAMP,
            Shader.TileMode.CLAMP
        )
        invalidate()
    }


    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
        val cx = width / 2.0f
        val cy = height / 2.0f
        val radius = min(cx, cy)
        canvas?.drawCircle(cx, cy, radius, paint)
    }
}