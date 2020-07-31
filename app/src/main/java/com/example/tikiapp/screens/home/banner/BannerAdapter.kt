package com.example.tikiapp.screens.home.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tikiapp.common.domain.entity.Banner
import com.example.tikiapp.utils.BaseViewItem

class BannerAdapter(
    private var banners: List<Banner> = ArrayList()
) : RecyclerView.Adapter<BannerVH>() {

    val realItemCount
        get() = banners.size

    fun setBanner(newBanners: List<Banner>) {
        banners = newBanners
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerVH {
        return BannerVH(BannerItemView(LayoutInflater.from(parent.context), parent))
    }

    override fun getItemCount(): Int {
        return if (banners.isEmpty()) 0 else Integer.MAX_VALUE
    }

    override fun onBindViewHolder(holder: BannerVH, position: Int) {
        holder.bindBanner(banners[position % banners.size])
    }

}

class BannerVH(private val baseViewItem: BaseViewItem<Banner>) :
    RecyclerView.ViewHolder(baseViewItem.view) {

    fun bindBanner(banner: Banner) {
        baseViewItem.bind(banner)
    }

}