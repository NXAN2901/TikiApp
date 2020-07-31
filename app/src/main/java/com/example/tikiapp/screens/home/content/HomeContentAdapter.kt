package com.example.tikiapp.screens.home.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tikiapp.screens.home.HomeDataModel
import com.example.tikiapp.screens.home.HomeItemListType
import com.example.tikiapp.utils.BaseViewItem

class HomeContentAdapter(private var homeDataModels: List<HomeDataModel> = ArrayList()) :
    RecyclerView.Adapter<CommonContentVH<HomeDataModel>>() {

    fun setHomeData(newHomeDataList: List<HomeDataModel>) {
        homeDataModels = newHomeDataList
        notifyDataSetChanged()
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonContentVH<HomeDataModel> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            HomeItemListType.QUICKLINK.ordinal -> CommonContentVH(QuickLinkContentItemView(layoutInflater, parent))
            HomeItemListType.BANNER.ordinal -> CommonContentVH(BannerContentItemView(layoutInflater, parent))
            HomeItemListType.FLASHDEAL.ordinal -> CommonContentVH(FlashDealContentItemView(layoutInflater, parent))
            else -> throw RuntimeException("Unknown Home Content ViewType")
        } as CommonContentVH<HomeDataModel>
    }

    override fun getItemCount(): Int {
        return homeDataModels.size
    }

    override fun getItemViewType(position: Int): Int {
        val item: HomeDataModel = homeDataModels[position]
        return item.getViewType().ordinal
    }

    override fun onBindViewHolder(holder: CommonContentVH<HomeDataModel>, position: Int) {
        holder.bind(homeDataModels[position])

    }

}

fun HomeDataModel.getViewType(): HomeItemListType {
    return when (this) {
        is HomeDataModel.HomeBanner -> HomeItemListType.BANNER
        is HomeDataModel.HomeQuickLink -> HomeItemListType.QUICKLINK
        is HomeDataModel.HomeFlashDeal -> HomeItemListType.FLASHDEAL
    }
}

class CommonContentVH<T: HomeDataModel>(private val baseViewItem: BaseViewItem<T>): RecyclerView.ViewHolder(baseViewItem.view) {
    fun  bind(item: T) {
        baseViewItem.bind(item)
    }
}

