package com.example.tikiapp.screens.home.banner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.tikiapp.R
import com.example.tikiapp.screens.home.models.HomeData
import com.example.tikiapp.utils.BaseViewItem


class BannerItemView(inflater: LayoutInflater, parentView: ViewGroup?) : BaseViewItem<HomeData.BannerModel> {
    override val view: View = inflater.inflate(R.layout.item_home_banner, parentView, false)

    private val ivBanner: AppCompatImageView

    init {
        ivBanner = view.findViewById(R.id.ivBanner)
    }

    override fun bind(item: HomeData.BannerModel) {
        Glide
            .with(view)
            .load(item.imageUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(view.resources.getDimensionPixelSize(R.dimen.home_banner_corner))))
            .into(ivBanner)
    }

}