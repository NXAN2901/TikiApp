package com.example.tikiapp.screens.home

import com.example.tikiapp.common.domain.entity.Banner
import com.example.tikiapp.common.domain.entity.FlashDeal
import com.example.tikiapp.common.domain.entity.QuickLink

sealed class HomeDataModel {

    class HomeBanner(
        var viewType: HomeItemListType = HomeItemListType.BANNER,
        var hasMoreTitle: Boolean = false,
        var dataList: List<Banner>? = null
    ) : HomeDataModel()

    class HomeQuickLink(
        var viewType: HomeItemListType = HomeItemListType.QUICKLINK,
        var hasMoreTitle: Boolean = false,
        var dataList: List<QuickLink>? = null
    ) : HomeDataModel()

    class HomeFlashDeal(
        var viewType: HomeItemListType = HomeItemListType.FLASHDEAL,
        var hasMoreTitle: Boolean = false,
        var dataList: List<FlashDeal>? = null
    ) : HomeDataModel()

}


//class HomeDataModel<T>(
//    var viewType: HomeItemListType = HomeItemListType.OTHER,
//    var hasMoreTitle: Boolean = false,
//    var dataList: List<T>? = null
//
//) {
//
//    fun setData(newDataList: List<T>?) {
//        dataList = newDataList
//    }
//
//}