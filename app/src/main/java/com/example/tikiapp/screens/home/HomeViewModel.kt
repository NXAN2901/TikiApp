package com.example.tikiapp.screens.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.tikiapp.common.domain.entity.Banner
import com.example.tikiapp.common.domain.entity.FlashDeal
import com.example.tikiapp.common.domain.entity.QuickLink
import com.example.tikiapp.common.domain.usecase.LoadBannerUseCase
import com.example.tikiapp.common.domain.usecase.LoadFlashDealUseCase
import com.example.tikiapp.common.domain.usecase.LoadQuickLinkUseCase
import com.example.tikiapp.data.exception.AppError
import com.example.tikiapp.data.remote.api.APICallback
import com.example.tikiapp.data.remote.api.response.BannerResponse
import com.example.tikiapp.data.remote.api.response.FlashDealResponse
import com.example.tikiapp.data.remote.api.response.QuickLinkResponse
import com.example.tikiapp.screens.base.BaseViewModel
import com.example.tikiapp.screens.home.models.HomeView
import com.example.tikiapp.utils.Mappers
import kotlinx.coroutines.Job

class HomeViewModel(
    application: Application,
    private val loadBannerUseCase: LoadBannerUseCase,
    private val loadQuickLinkUseCase: LoadQuickLinkUseCase,
    private val loadFlashDealUseCase: LoadFlashDealUseCase
) : BaseViewModel<HomeNavigator>(application) {

    private val data: MutableList<HomeView> = ArrayList()

    val homeLiveData by lazy { MutableLiveData<List<HomeView>>() }
    val job = Job()

    fun fetchData() {
        setShowLoading(true)
        data.clear()
        fetchBanner()
        fetchQuickLinks()
    }

    fun fetchFlashDeal() {
        if (unableFetchFlashDeal()) {
            return
        }
        loadFlashDealUseCase.fromRemote(object :
            APICallback<FlashDealResponse<ArrayList<FlashDeal>>> {
            override fun onResult(result: FlashDealResponse<ArrayList<FlashDeal>>) {
                data.add(HomeView.HomeFlashDeal().apply {
                    dataList = result.data?.map { flashDeal -> Mappers.remoteToHomeFlashDeal(flashDeal) }
                })
                homeLiveData.postValue(data)
                setShowLoading(false)
            }

            override fun onError(appError: AppError) {
                data.add(HomeView.HomeFlashDeal())
                setShowLoading(false)
            }
        })
    }

    fun fetchQuickLinks() {
        loadQuickLinkUseCase.fromRemote(object :
            APICallback<QuickLinkResponse<ArrayList<ArrayList<QuickLink>>>> {
            override fun onResult(result: QuickLinkResponse<ArrayList<ArrayList<QuickLink>>>) {
                val homeQuickLink = HomeView.HomeQuickLink()
                result.data?.takeIf { it.size > 0 }?.let { datas ->
                    val firstList = datas[0]
                    for (i in 1 until datas.size) {
                        firstList.addAll(datas[i])
                    }
                    homeQuickLink.dataList = firstList.map { quickLink -> Mappers.remoteToHomeQuickLink(quickLink) }
                }
                data.add(homeQuickLink)
                homeLiveData.postValue(data)
                fetchFlashDeal()
            }

            override fun onError(appError: AppError) {
                // TODO handle error
                data.add(HomeView.HomeQuickLink())
                fetchFlashDeal()
            }

        })
    }

    fun fetchBanner() {
        loadBannerUseCase.fromRemote(object : APICallback<BannerResponse<ArrayList<Banner>>> {
            override fun onResult(result: BannerResponse<ArrayList<Banner>>) {
                data.add(POSITION_BANNER, HomeView.HomeBanner().apply {
                    dataList = result.data?.map { banner -> Mappers.remoteToHomeBanner(banner) }
                })
                homeLiveData.postValue(data)
                fetchFlashDeal()
            }

            override fun onError(appError: AppError) {
                // TODO handle Error
                data.add(POSITION_BANNER, HomeView.HomeBanner())
                fetchFlashDeal()
            }

        })
    }

    private fun unableFetchFlashDeal() = data.size < 2

    companion object {
        const val POSITION_BANNER = 0
        const val POSITION_QUICKLINK = 1
        const val POSITION_FLASHDEAL = 2

    }
}