package com.example.tikiapp.screens.home.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tikiapp.R
import com.example.tikiapp.screens.home.flashdeal.FlashDealView
import com.example.tikiapp.screens.home.models.HomeView
import com.example.tikiapp.utils.BaseViewItem

class FlashDealContentItemView(inflater: LayoutInflater, parent: ViewGroup?) :
    BaseViewItem<HomeView.HomeFlashDeal> {
    override val view: View = inflater.inflate(R.layout.home_content_flashdeal, parent, false)

    private val flashDealContent by lazy {
        view.findViewById<FlashDealView>(R.id.flashDealContent)
    }

    override fun bind(item: HomeView.HomeFlashDeal) {
        val dataList = item.dataList
        if (dataList.isNullOrEmpty()) {
            view.visibility = View.GONE
            return
        }
        view.visibility = View.VISIBLE
        flashDealContent.setFlashDeals(dataList)
    }

}