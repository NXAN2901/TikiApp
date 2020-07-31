package com.example.tikiapp.screens.home.flashdeal

import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tikiapp.R
import com.example.tikiapp.common.domain.entity.FlashDeal

class FlashDealView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttrs) {

    private val tvFlashTitle by lazy {
        findViewById<AppCompatTextView>(R.id.tvDealTitle)
    }

    private val rvFlashDeal by lazy {
        findViewById<RecyclerView>(R.id.rvFlashDeal)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_home_flashdeal, this, true)
        setWillNotDraw(false)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setGradientTitle()
    }

    fun setGradientTitle() {
        val shader = LinearGradient(
            0f,
            0f,
            0f,
            tvFlashTitle.paint.textSize,
            intArrayOf(
                Color.parseColor("#FF7F39"),
                Color.parseColor("#FD5D17"),
                Color.parseColor("#F9601C"),
                Color.parseColor("#F75627")

            ),
            floatArrayOf(0f, 0.3f, 0.7f, 1f),
            Shader.TileMode.CLAMP
        )
        tvFlashTitle.paint.shader = shader
    }

    fun setFlashDeals(flashDeals: List<FlashDeal>) {
        if (rvFlashDeal.adapter == null) {
            rvFlashDeal.apply {
                adapter = FlashDealAdapter()
                layoutManager = LinearLayoutManager(
                    context,
                    GridLayoutManager.HORIZONTAL,
                    false
                )
            }
        }

        (rvFlashDeal.adapter as FlashDealAdapter).apply {
            setFlashDeals(flashDeals)
        }
    }
}