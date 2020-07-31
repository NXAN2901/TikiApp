package com.example.tikiapp.screens.home.banner

import android.animation.ArgbEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tikiapp.R
import com.example.tikiapp.utils.dpToPx

class BannerIndicator(context: Context) : RecyclerView.ItemDecoration() {

    private var unselectedColor =
        ContextCompat.getColor(context, R.color.banner_indicator_unselected)
    private var selectedColor = ContextCompat.getColor(context, R.color.banner_indicator_selected)

    private val diffRadius: Float = context.dpToPx(1.5f)
    private val unselectedRadius: Float = context.dpToPx(3f)
    private val margin: Float = unselectedRadius + context.dpToPx(8f)


    private val evaluator by lazy {
        ArgbEvaluator()
    }

    private val interpolator by lazy { AccelerateDecelerateInterpolator() }

    private val paint by lazy {
        Paint().apply {
            color = unselectedColor
            style = Paint.Style.FILL
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        parent.adapter?.takeIf { it.itemCount > 0 }?.run {
            val itemCount = (this as BannerAdapter).realItemCount
            (parent.layoutManager as? LinearLayoutManager)?.let { manager ->
                val firstItemPosition = manager.findFirstVisibleItemPosition()
                val actualItemPosition = firstItemPosition % itemCount
                manager.findViewByPosition(firstItemPosition)?.let { firstView ->
                    val totalWidth = margin * (itemCount - 1)
                    val startX = (parent.width - totalWidth) / 2
                    val y = parent.height - margin

                    val left = firstView.left
                    val width = firstView.width
                    val progress = interpolator.getInterpolation(-1 * left / width.toFloat())

                    for (i in 0 until itemCount) {
                        var radius = unselectedRadius
                        when (i) {
                            actualItemPosition -> {
                                radius += diffRadius * (1 - progress)
                                paint.color = evaluator.evaluate(
                                    1 - progress,
                                    unselectedColor,
                                    selectedColor
                                ) as Int
                            }
                            actualItemPosition + 1 -> {
                                radius += diffRadius * progress
                                paint.color = evaluator.evaluate(progress, unselectedColor, selectedColor) as Int
                            }
                            else -> paint.color = unselectedColor
                        }
                        c.drawCircle(startX + i * margin, y, radius, paint)
                    }
                }
            }

        }
    }
}