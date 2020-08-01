package com.example.tikiapp.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.tikiapp.R

class CollapsibleToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MotionLayout(context, attrs, defStyleAttr) {

    private val path = Path()
    private val background = BitmapFactory.decodeResource(resources, R.drawable.bg)
    private val bitmapShader =
        BitmapShader(background, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = Color.BLUE
        shader = bitmapShader
    }

    init {
        setWillNotDraw(false)
    }

    fun setDrawProgress(newProgress: Float) {
        progress = newProgress
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) {
            return
        }
        path.rewind()
        path.lineTo(0f, (height - 180 * (1 - progress)))
        path.quadTo(
            (width / 2).toFloat(), height.toFloat(),
            width.toFloat(), (height - 180 * (1 - progress))
        )
        path.lineTo(width.toFloat(), 0f)
        canvas.drawPath(path, paint)
    }
}