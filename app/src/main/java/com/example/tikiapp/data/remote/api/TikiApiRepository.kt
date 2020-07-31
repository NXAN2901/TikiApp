package com.example.tikiapp.data.remote.api

import com.example.tikiapp.common.domain.entity.Banner
import com.example.tikiapp.common.domain.entity.FlashDeal
import com.example.tikiapp.common.domain.entity.QuickLink
import com.example.tikiapp.data.remote.api.response.BannerResponse
import com.example.tikiapp.data.remote.api.response.FlashDealResponse
import com.example.tikiapp.data.remote.api.response.QuickLinkResponse

class TikiApiRepository(private val tikiAPI: TikiAPI) : BaseApiRepository() {

    fun fetchBanners(apiCallback: APICallback<BannerResponse<ArrayList<Banner>>>) =
        tikiAPI.fetchBanners().enqueue(generateCallback(apiCallback))

    fun fetchQuickLink(apiCallback: APICallback<QuickLinkResponse<ArrayList<ArrayList<QuickLink>>>>) =
        tikiAPI.fetchQuickLinks().enqueue(generateCallback(apiCallback))

    fun fetchFlashDeal(apiCallback: APICallback<FlashDealResponse<ArrayList<FlashDeal>>>) =
        tikiAPI.fetchFlashDeal().enqueue(generateCallback(apiCallback))
}