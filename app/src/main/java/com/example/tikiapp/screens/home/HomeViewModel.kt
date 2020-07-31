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

class HomeViewModel(
    application: Application,
    private val loadBannerUseCase: LoadBannerUseCase,
    private val loadQuickLinkUseCase: LoadQuickLinkUseCase,
    private val loadFlashDealUseCase: LoadFlashDealUseCase
) : BaseViewModel<HomeNavigator>(application) {

    private val data: MutableList<HomeDataModel> = ArrayList()

    val homeLiveData = MutableLiveData<List<HomeDataModel>>()

    fun fetchData() {
        data.clear()
        fetchBanner()
        fetchQuickLinks()
    }

    fun fetchFlashDeal() {
        if (unableFetchFlashDeal()) {
            return
        }
        data.add(HomeDataModel.HomeFlashDeal())
        loadFlashDealUseCase.fromRemote(object :
            APICallback<FlashDealResponse<ArrayList<FlashDeal>>> {
            override fun onResult(result: FlashDealResponse<ArrayList<FlashDeal>>) {
                (data[POSITION_FLASHDEAL] as HomeDataModel.HomeFlashDeal).dataList = result.data
                homeLiveData.postValue(data)
            }

            override fun onError(appError: AppError) {
            }
        })
    }

    fun fetchQuickLinks() {
        loadQuickLinkUseCase.fromRemote(object :
            APICallback<QuickLinkResponse<ArrayList<ArrayList<QuickLink>>>> {
            override fun onResult(result: QuickLinkResponse<ArrayList<ArrayList<QuickLink>>>) {
                data.add(HomeDataModel.HomeQuickLink())
                result.data?.takeIf { it.size > 0 }?.let { datalist ->
                    val firstList = datalist[0]
                    for (i in 1 until datalist.size) {
                        firstList.addAll(datalist[i])
                    }
                    (data[POSITION_QUICKLINK] as HomeDataModel.HomeQuickLink).dataList = firstList
                }
                homeLiveData.postValue(data)
                fetchFlashDeal()
            }

            override fun onError(appError: AppError) {
                // TODO handle error
                fetchFlashDeal()
            }

        })
    }

    fun fetchBanner() {
        data.add(HomeDataModel.HomeBanner())
        loadBannerUseCase.fromRemote(object : APICallback<BannerResponse<ArrayList<Banner>>> {
            override fun onResult(result: BannerResponse<ArrayList<Banner>>) {
                (data[POSITION_BANNER] as HomeDataModel.HomeBanner).dataList = result.data
                homeLiveData.postValue(data)
                fetchFlashDeal()
            }

            override fun onError(appError: AppError) {
                // TODO handle Error
            }

        })
    }

    private fun unableFetchFlashDeal() = data.size != 2 || (data[0] as HomeDataModel.HomeBanner).dataList == null

    companion object {
        const val POSITION_BANNER = 0
        const val POSITION_QUICKLINK = 1
        const val POSITION_FLASHDEAL = 2

    }
}