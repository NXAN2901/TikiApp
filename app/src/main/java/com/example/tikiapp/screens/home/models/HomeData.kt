package com.example.tikiapp.screens.home.models

sealed class HomeData {

    data class BannerModel (
        val id: String,
        val url: String,
        val imageUrl: String
    ): HomeData()

    data class QuickLinkModel(
        val title: String,
        val url: String,
        val imageUrl: String
    ): HomeData()

    data class FlashDealModel(
        val discountPercent: Int,
        val specialPrice: Int,
        val ordered: Int,
        val percent: Double,
        val imageUrl: String,
        val prefixPriceFormat: String
    ): HomeData()
}