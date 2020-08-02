package com.example.tikiapp.data.remote.api

import com.example.tikiapp.common.domain.entity.Banner
import com.example.tikiapp.common.domain.entity.FlashDeal
import com.example.tikiapp.common.domain.entity.QuickLink
import com.example.tikiapp.data.remote.api.response.BannerResponse
import com.example.tikiapp.data.remote.api.response.FlashDealResponse
import com.example.tikiapp.data.remote.api.response.QuickLinkResponse
import retrofit2.http.GET

interface TikiAPI {

    @GET("v2/home/banners/v2")
    suspend fun fetchBanners(): BannerResponse<ArrayList<Banner>>

    @GET("shopping/v2/widgets/quick_link")
    suspend fun fetchQuickLinks(): QuickLinkResponse<ArrayList<ArrayList<QuickLink>>>

    @GET("v2/widget/deals/hot")
    suspend fun fetchFlashDeal(): FlashDealResponse<ArrayList<FlashDeal>>
}