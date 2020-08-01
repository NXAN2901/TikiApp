package com.example.tikiapp.screens.home.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tikiapp.screens.home.models.HomeItemListType
import com.example.tikiapp.screens.home.models.HomeView
import com.example.tikiapp.utils.BaseViewItem

class HomeContentAdapter(
    private var homeViews: List<HomeView> = ArrayList(),
    private var showLoading: Boolean = false
) :
    RecyclerView.Adapter<CommonContentVH<HomeView>>() {

    fun showLoading(isShowLoading: Boolean) {
        showLoading = isShowLoading
        notifyDataSetChanged()
    }

    fun setHomeData(newHomeDataList: List<HomeView>) {
        homeViews = newHomeDataList
        notifyDataSetChanged()
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonContentVH<HomeView> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            HomeItemListType.QUICKLINK.ordinal -> CommonContentVH(
                QuickLinkContentItemView(
                    layoutInflater,
                    parent
                )
            )
            HomeItemListType.BANNER.ordinal -> CommonContentVH(
                BannerContentItemView(
                    layoutInflater,
                    parent
                )
            )
            HomeItemListType.FLASHDEAL.ordinal -> CommonContentVH(
                FlashDealContentItemView(
                    layoutInflater,
                    parent
                )
            )
            HomeItemListType.LOADING.ordinal -> CommonContentVH(
                LoadingContentItemView(
                    layoutInflater,
                    parent
                )
            )
            else -> throw RuntimeException("Unknown Home Content ViewType")
        } as CommonContentVH<HomeView>
    }

    override fun getItemCount(): Int {
        return if (showLoading) homeViews.size + 1 else homeViews.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == homeViews.size) {
            return HomeItemListType.LOADING.ordinal
        }
        val item: HomeView = homeViews[position]
        return item.getViewType().ordinal
    }

    override fun onBindViewHolder(holder: CommonContentVH<HomeView>, position: Int) {
        if (position < homeViews.size) {
            holder.bind(homeViews[position])
        }
    }

}

fun HomeView.getViewType(): HomeItemListType {
    return when (this) {
        is HomeView.HomeBanner -> HomeItemListType.BANNER
        is HomeView.HomeQuickLink -> HomeItemListType.QUICKLINK
        is HomeView.HomeFlashDeal -> HomeItemListType.FLASHDEAL
    }
}

class CommonContentVH<T : HomeView>(private val baseViewItem: BaseViewItem<T>) :
    RecyclerView.ViewHolder(baseViewItem.view) {
    fun bind(item: T) {
        baseViewItem.bind(item)
    }
}
