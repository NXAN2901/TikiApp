package com.example.tikiapp.data.remote.api

class TikiApiRepository(private val tikiAPI: TikiAPI) : BaseApiRepository() {

    suspend fun fetchBanners() = tikiAPI.fetchBanners()

    suspend fun fetchQuickLink() = tikiAPI.fetchQuickLinks()

    suspend fun fetchFlashDeal() = tikiAPI.fetchFlashDeal()
}