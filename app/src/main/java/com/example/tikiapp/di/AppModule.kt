package com.example.tikiapp.di

import com.example.tikiapp.common.domain.usecase.LoadBannerUseCase
import com.example.tikiapp.common.domain.usecase.LoadFlashDealUseCase
import com.example.tikiapp.common.domain.usecase.LoadQuickLinkUseCase
import com.example.tikiapp.data.remote.api.TikiApiRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { TikiApiRepository(get()) }
}

val useCaseModule = module {
    factory { LoadBannerUseCase(get()) }
    factory { LoadQuickLinkUseCase(get()) }
    factory { LoadFlashDealUseCase(get()) }
}