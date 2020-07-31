package com.example.tikiapp.common.domain.usecase

import com.example.tikiapp.common.domain.entity.Banner
import com.example.tikiapp.data.remote.api.APICallback
import com.example.tikiapp.data.remote.api.TikiApiRepository
import com.example.tikiapp.data.remote.api.response.BannerResponse

class LoadBannerUseCase(private val tikiApiRepository: TikiApiRepository) {
    fun fromRemote(apiCallback: APICallback<BannerResponse<ArrayList<Banner>>>) =
        tikiApiRepository.fetchBanners(apiCallback)
}