package com.example.tikiapp.screens.home.quicklink

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tikiapp.screens.home.models.HomeData
import com.example.tikiapp.utils.BaseViewItem

class QuickLinkAdapter(
    private var quickLinks: List<HomeData.QuickLinkModel> = ArrayList()
) : RecyclerView.Adapter<QuickLinkVH>() {

    fun setQuickLinks(newQuickLinks: List<HomeData.QuickLinkModel>) {
        quickLinks = newQuickLinks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuickLinkVH {
        return QuickLinkVH(QuickLinkItemView(LayoutInflater.from(parent.context), parent))
    }

    override fun getItemCount(): Int {
        return quickLinks.size
    }

    override fun onBindViewHolder(holder: QuickLinkVH, position: Int) {
        holder.bindQuickLink(quickLinks[position])
    }

}

class QuickLinkVH(private val baseViewItem: BaseViewItem<HomeData.QuickLinkModel>) :
    RecyclerView.ViewHolder(baseViewItem.view) {

    fun bindQuickLink(quickLink: HomeData.QuickLinkModel) {
        baseViewItem.bind(quickLink)
    }
}