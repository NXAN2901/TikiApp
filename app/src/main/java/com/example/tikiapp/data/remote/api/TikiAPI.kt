package com.example.tikiapp.data.remote.api

import com.example.tikiapp.common.domain.entity.Banner
import com.example.tikiapp.common.domain.entity.FlashDeal
import com.example.tikiapp.common.domain.entity.QuickLink
import com.example.tikiapp.data.remote.api.response.BannerResponse
import com.example.tikiapp.data.remote.api.response.FlashDealResponse
import com.example.tikiapp.data.remote.api.response.QuickLinkResponse
import retrofit2.Call
import retrofit2.http.GET

interface TikiAPI {

    @GET("v2/home/banners/v2")
    fun fetchBanners(): Call<BannerResponse<ArrayList<Banner>>>

    @GET("shopping/v2/widgets/quick_link")
    fun fetchQuickLinks(): Call<QuickLinkResponse<ArrayList<ArrayList<QuickLink>>>>

    @GET("v2/widget/deals/hot")
    fun fetchFlashDeal(): Call<FlashDealResponse<ArrayList<FlashDeal>>>
}