package com.example.tikiapp.screens.home.header

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.tikiapp.R

class HeaderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttrs: Int = 0
) : MotionLayout(context, attrs, defStyleAttrs) {

    private val path = Path()
    private val background = BitmapFactory.decodeResource(resources, R.drawable.bg)
    private val bitmapShader = BitmapShader(background, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = Color.BLUE
    }

    init {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.view_home_header, this, true)
        paint.shader = bitmapShader
        setWillNotDraw(false)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) {
            return
        }
        path.lineTo(0f, (height - 180).toFloat())
        path.quadTo(
            (width / 2).toFloat(), height.toFloat(),
            width.toFloat(), (height - 180).toFloat()
        )
        path.lineTo(width.toFloat(), 0f)
        canvas.drawPath(path, paint)
    }
}