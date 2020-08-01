package com.example.tikiapp.utils

import com.example.tikiapp.common.domain.entity.Banner
import com.example.tikiapp.common.domain.entity.FlashDeal
import com.example.tikiapp.common.domain.entity.QuickLink
import com.example.tikiapp.screens.home.models.HomeData

object Mappers {

    fun remoteToHomeBanner(banner: Banner): HomeData.BannerModel {
        return HomeData.BannerModel(
            id = banner.id,
            url = banner.url,
            imageUrl = banner.mobileUrl
        )
    }

    fun remoteToHomeQuickLink(quickLink: QuickLink): HomeData.QuickLinkModel {
        return HomeData.QuickLinkModel(
            url = quickLink.url,
            imageUrl = quickLink.imageUrl,
            title = quickLink.title
        )
    }

    fun remoteToHomeFlashDeal(flashDeal: FlashDeal): HomeData.FlashDealModel {
        val progress = flashDeal.progress
        val product = flashDeal.product
        return HomeData.FlashDealModel(
            discountPercent = flashDeal.discountPercent,
            specialPrice = flashDeal.specialPrice,
            ordered = progress?.qtyOrdered ?: 0,
            percent = progress?.percent ?: 0.toDouble(),
            imageUrl = product?.thumbnailUrl ?: "",//TODO Add default image url
            prefixPriceFormat = product?.pricePrefix ?: "#.###"
        )
    }
}