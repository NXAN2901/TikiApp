package com.example.tikiapp.screens.home.flashdeal

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.tikiapp.R
import com.example.tikiapp.screens.home.models.HomeData
import com.example.tikiapp.utils.BaseViewItem
import com.example.tikiapp.utils.toTextFormat
import java.util.*

class FlashDealItemView(inflater: LayoutInflater, parent: ViewGroup?) :
    BaseViewItem<HomeData.FlashDealModel> {
    override val view: View = inflater.inflate(R.layout.item_home_flashdeal, parent, false)

    private val ivPlashDeal: AppCompatImageView by lazy {
        view.findViewById<AppCompatImageView>(R.id.ivFlashDeal)
    }

    private val tvPercentDiscount: AppCompatTextView by lazy {
        view.findViewById<AppCompatTextView>(R.id.tvPercentDiscount)
    }

    private val tvPrice: AppCompatTextView by lazy {
        view.findViewById<AppCompatTextView>(R.id.tvPrice)
    }

    private val tvSoldCount: AppCompatTextView by lazy {
        view.findViewById<AppCompatTextView>(R.id.tvSoldCount)
    }

    private val flashDealProgress: ProgressBar by lazy {
        view.findViewById<ProgressBar>(R.id.flashDealProgress)
    }

    override fun bind(item: HomeData.FlashDealModel) {
        val resources = view.resources
        tvPrice.text = item.specialPrice.toTextFormat(resources.getString(R.string.flashdeal_price))
        tvPercentDiscount.text = String.format(
            Locale.US,
            resources.getString(R.string.flashdeal_discount_percent_format),
            item.discountPercent
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            flashDealProgress.setProgress(item.percent.toInt(), true)
        } else {
            flashDealProgress.progress = item.percent.toInt()
        }
        tvSoldCount.text = String.format(
            Locale.US,
            resources.getString(R.string.flashdeal_sold_count),
            item.ordered
        )
        Glide
            .with(view)
            .load(item.imageUrl)
            .apply(
                RequestOptions.bitmapTransform(
                    RoundedCorners(
                        view.resources.getDimensionPixelSize(
                            R.dimen.home_banner_corner
                        )
                    )
                )
            )
            .into(ivPlashDeal)

    }

}