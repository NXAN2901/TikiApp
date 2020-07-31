package com.example.tikiapp.screens.home.flashdeal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.tikiapp.R
import com.example.tikiapp.common.domain.entity.FlashDeal
import com.example.tikiapp.utils.BaseViewItem
import java.util.*

class FlashDealItemView(inflater: LayoutInflater, parent: ViewGroup?): BaseViewItem<FlashDeal> {
    override val view: View = inflater.inflate(R.layout.item_home_flashdeal, parent, false)

    private val ivPlashDeal: AppCompatImageView by lazy {
        view.findViewById<AppCompatImageView>(R.id.ivFlashDeal)
    }

    private val tvPercentDiscount: AppCompatTextView by lazy {
        view.findViewById<AppCompatTextView>(R.id.tvPercentDiscount)
    }

    override fun bind(item: FlashDeal) {
        tvPercentDiscount.text = String.format(Locale.US, view.resources.getString(R.string.flashdeal_discount_percent_format), item.discountPercent)
        item.product?.let { product ->
            Glide
                .with(view)
                .load(product.thumbnailUrl)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(view.resources.getDimensionPixelSize(R.dimen.home_banner_corner))))
                .into(ivPlashDeal)
        }

    }

}