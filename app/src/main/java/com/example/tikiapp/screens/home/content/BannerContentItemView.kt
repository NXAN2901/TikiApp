package com.example.tikiapp.screens.home.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.tikiapp.R
import com.example.tikiapp.screens.home.banner.BannerAdapter
import com.example.tikiapp.screens.home.banner.BannerIndicator
import com.example.tikiapp.screens.home.models.HomeView
import com.example.tikiapp.utils.BaseViewItem
import com.example.tikiapp.views.AutoSwipeRecyclerview

class BannerContentItemView(inflater: LayoutInflater, parentView: ViewGroup?) :
    BaseViewItem<HomeView.HomeBanner> {
    override val view: View = inflater.inflate(R.layout.home_content_banner, parentView, false)

    override fun bind(item: HomeView.HomeBanner) {
        val dataList = item.dataList
        if (dataList.isNullOrEmpty()) {
            view.visibility = View.GONE
            return
        }

        (view as AutoSwipeRecyclerview).let { rv ->
            if (rv.adapter == null) {
                rv.apply {
                    adapter = BannerAdapter()
                    layoutManager = LinearLayoutManager(view.context,  LinearLayoutManager.HORIZONTAL, false)
                    addItemDecoration(BannerIndicator(view.context))
                }
                PagerSnapHelper().attachToRecyclerView(rv)
            }
            view.visibility = View.VISIBLE
            (rv.adapter as BannerAdapter).setBanner(dataList)
        }
    }

}