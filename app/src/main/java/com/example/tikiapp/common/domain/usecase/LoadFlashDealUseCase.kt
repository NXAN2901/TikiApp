package com.example.tikiapp.common.domain.usecase

import com.example.tikiapp.common.domain.entity.FlashDeal
import com.example.tikiapp.data.remote.api.APICallback
import com.example.tikiapp.data.remote.api.TikiApiRepository
import com.example.tikiapp.data.remote.api.response.FlashDealResponse

class LoadFlashDealUseCase(private val tikiApiRepository: TikiApiRepository) {

    fun fromRemote(apiCallback: APICallback<FlashDealResponse<ArrayList<FlashDeal>>>) =
        tikiApiRepository.fetchFlashDeal(apiCallback)
}