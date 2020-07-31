package com.example.tikiapp.screens.home.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tikiapp.R
import com.example.tikiapp.screens.home.HomeDataModel
import com.example.tikiapp.screens.home.quicklink.QuickLinkAdapter
import com.example.tikiapp.utils.BaseViewItem

class QuickLinkContentItemView(inflater: LayoutInflater, parentView: ViewGroup?) :
    BaseViewItem<HomeDataModel.HomeQuickLink> {
    override val view: View = inflater.inflate(R.layout.home_content_quicklink, parentView, false)

    private val rvQuickLink by lazy {
        view.findViewById<RecyclerView>(R.id.rvQuickLink)
    }

    override fun bind(item: HomeDataModel.HomeQuickLink) {
        val dataList = item.dataList
        if (dataList.isNullOrEmpty()) {
            view.visibility = View.GONE
            return
        }

        if (rvQuickLink.adapter == null) {
            rvQuickLink.apply {
                adapter = QuickLinkAdapter()
                layoutManager =
                    GridLayoutManager(view.context, 2, GridLayoutManager.HORIZONTAL, false)
            }
        }
        view.visibility = View.VISIBLE
        (rvQuickLink.adapter as QuickLinkAdapter).setQuickLinks(dataList)
    }

}