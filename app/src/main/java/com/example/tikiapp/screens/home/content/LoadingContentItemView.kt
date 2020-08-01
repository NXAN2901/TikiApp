package com.example.tikiapp.screens.home.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tikiapp.R
import com.example.tikiapp.screens.home.models.HomeView
import com.example.tikiapp.utils.BaseViewItem

class LoadingContentItemView (inflater: LayoutInflater, parentView: ViewGroup?): BaseViewItem<HomeView> {
    override val view: View = inflater.inflate(R.layout.home_content_loading, parentView, false)

    override fun bind(item: HomeView) {
        // TODO Init loading
    }

}