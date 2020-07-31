package com.example.tikiapp.screens.home.quicklink

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tikiapp.common.domain.entity.QuickLink
import com.example.tikiapp.utils.BaseViewItem

class QuickLinkAdapter(
    private var quickLinks: List<QuickLink> = ArrayList()
) : RecyclerView.Adapter<QuickLinkVH>() {

    fun setQuickLinks(newQuickLinks: List<QuickLink>) {
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

class QuickLinkVH(private val baseViewItem: BaseViewItem<QuickLink>) :
    RecyclerView.ViewHolder(baseViewItem.view) {

    fun bindQuickLink(quickLink: QuickLink) {
        baseViewItem.bind(quickLink)
    }
}