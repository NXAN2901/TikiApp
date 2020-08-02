package com.example.tikiapp.common.domain.usecase

import com.example.tikiapp.data.remote.api.TikiApiRepository

class LoadQuickLinkUseCase(private val tikiApiRepository: TikiApiRepository) {

    suspend fun fromRemote() = tikiApiRepository.fetchQuickLink()
}