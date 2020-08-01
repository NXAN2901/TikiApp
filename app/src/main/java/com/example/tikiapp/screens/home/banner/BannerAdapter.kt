package com.example.tikiapp.screens.home.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tikiapp.screens.home.models.HomeData
import com.example.tikiapp.utils.BaseViewItem

class BannerAdapter(
    private var banners: List<HomeData.BannerModel> = ArrayList()
) : RecyclerView.Adapter<BannerVH>() {

    val realItemCount
        get() = banners.size

    fun setBanner(newBanners: List<HomeData.BannerModel>) {
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

class BannerVH(private val baseViewItem: BaseViewItem<HomeData.BannerModel>) :
    RecyclerView.ViewHolder(baseViewItem.view) {

    fun bindBanner(banner: HomeData.BannerModel) {
        baseViewItem.bind(banner)
    }

}