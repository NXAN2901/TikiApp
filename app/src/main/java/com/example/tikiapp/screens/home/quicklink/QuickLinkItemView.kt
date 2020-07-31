package com.example.tikiapp.screens.home.quicklink

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.example.tikiapp.R
import com.example.tikiapp.common.domain.entity.QuickLink
import com.example.tikiapp.utils.BaseViewItem

class QuickLinkItemView(inflater: LayoutInflater, parentView: ViewGroup?) :
    BaseViewItem<QuickLink> {
    override val view: View = inflater.inflate(R.layout.item_home_quicklink, parentView, false)

    private val ivQuickLink: AppCompatImageView
    private val tvQuickLink: AppCompatTextView

    init {
        ivQuickLink = view.findViewById(R.id.ivQuickLink)
        tvQuickLink = view.findViewById(R.id.tvQuickLink)
    }

    override fun bind(item: QuickLink) {
        Glide
            .with(view)
            .load(item.imageUrl)
            .into(ivQuickLink)
        tvQuickLink.text = item.title

    }

}