package com.example.tikiapp.screens.home.models

import com.example.tikiapp.screens.home.HomeItemListType

sealed class HomeView {

    class HomeBanner(
        var viewType: HomeItemListType = HomeItemListType.BANNER,
        var hasMoreTitle: Boolean = false,
        var dataList: List<HomeData.BannerModel>? = null
    ) : HomeView()

    class HomeQuickLink(
        var viewType: HomeItemListType = HomeItemListType.QUICKLINK,
        var hasMoreTitle: Boolean = false,
        var dataList: List<HomeData.QuickLinkModel>? = null
    ) : HomeView()

    class HomeFlashDeal(
        var viewType: HomeItemListType = HomeItemListType.FLASHDEAL,
        var hasMoreTitle: Boolean = false,
        var dataList: List<HomeData.FlashDealModel>? = null
    ) : HomeView()

}

fun HomeView.getDataList(): List<HomeData>? {
    return when (this) {
        is HomeView.HomeQuickLink -> this.dataList
        is HomeView.HomeBanner -> this.dataList
        is HomeView.HomeFlashDeal -> this.dataList
    }
}