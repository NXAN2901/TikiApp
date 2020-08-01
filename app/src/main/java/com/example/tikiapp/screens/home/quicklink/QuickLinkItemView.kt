package com.example.tikiapp.screens.home.quicklink

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.example.tikiapp.R
import com.example.tikiapp.screens.home.models.HomeData
import com.example.tikiapp.utils.BaseViewItem

class QuickLinkItemView(inflater: LayoutInflater, parentView: ViewGroup?) :
    BaseViewItem<HomeData.QuickLinkModel> {
    override val view: View = inflater.inflate(R.layout.item_home_quicklink, parentView, false)

    private val ivQuickLink: AppCompatImageView by lazy {
        view.findViewById<AppCompatImageView>(R.id.ivQuickLink)
    }
    private val tvQuickLink: AppCompatTextView by lazy {
        view.findViewById<AppCompatTextView>(R.id.tvQuickLink)
    }

    override fun bind(item: HomeData.QuickLinkModel) {
        Glide
            .with(view)
            .load(item.imageUrl)
            .into(ivQuickLink)
        tvQuickLink.text = item.title
    }

}