package com.example.tikiapp.common.domain.usecase

import com.example.tikiapp.common.domain.entity.QuickLink
import com.example.tikiapp.data.remote.api.APICallback
import com.example.tikiapp.data.remote.api.TikiApiRepository
import com.example.tikiapp.data.remote.api.response.QuickLinkResponse

class LoadQuickLinkUseCase(private val tikiApiRepository: TikiApiRepository) {

    fun fromRemote(apiCallback: APICallback<QuickLinkResponse<ArrayList<ArrayList<QuickLink>>>>) =
        tikiApiRepository.fetchQuickLink(apiCallback)
}