package com.example.tikiapp.screens.home

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.tikiapp.common.domain.usecase.LoadBannerUseCase
import com.example.tikiapp.common.domain.usecase.LoadFlashDealUseCase
import com.example.tikiapp.common.domain.usecase.LoadQuickLinkUseCase
import com.example.tikiapp.screens.base.BaseViewModel
import com.example.tikiapp.screens.home.models.HomeView
import com.example.tikiapp.screens.home.models.getDataList
import com.example.tikiapp.utils.Mappers
import kotlinx.coroutines.*

class HomeViewModel(
    application: Application,
    private val loadBannerUseCase: LoadBannerUseCase,
    private val loadQuickLinkUseCase: LoadQuickLinkUseCase,
    private val loadFlashDealUseCase: LoadFlashDealUseCase
) : BaseViewModel<HomeNavigator>(application) {

    private val data: MutableList<HomeView> = ArrayList()
    private val homeLiveData by lazy { MutableLiveData<List<HomeView>>() }
    val homeDataTransformation = Transformations.map(homeLiveData) { homeViews ->
        homeViews.filter { it.getDataList() != null }
    }

    val refreshing = ObservableBoolean(false)

    val dataJob = Job()
    val dataScope = CoroutineScope(Dispatchers.IO + dataJob)

    override fun onCleared() {
        dataJob.cancel()
        super.onCleared()
    }

    val onRefreshing = SwipeRefreshLayout.OnRefreshListener {
        refreshing.set(true)
        clearData()
        fetchData()
    }

    fun fetchData() {
        clearData()
        refreshing.set(false)
        setShowLoading(true)
        dataScope.launch {
            fetchBanner()
            fetchQuickLink()
            fetchFlashDeals()
            viewModelScope.launch {
                setShowLoading(false)
            }
        }
        data.clear()
    }

    suspend fun fetchBanner() {
        val banners =
            withContext(Dispatchers.Default) { loadBannerUseCase.fromRemote() }.data
        val homeBanner = HomeView.HomeBanner()
            .apply { dataList = banners?.map { banner -> Mappers.remoteToHomeBanner(banner) } }
        data.add(POSITION_BANNER, homeBanner)
        postHomeData()
    }

    suspend fun fetchQuickLink() {
        val quickLinks =
            withContext(Dispatchers.Default) { loadQuickLinkUseCase.fromRemote() }.data
        val homeQuickLink = HomeView.HomeQuickLink()
        quickLinks?.takeIf { it.size > 0 }?.let { datas ->
            val firstList = datas[0]
            for (i in 1 until datas.size) {
                firstList.addAll(datas[i])
            }
            homeQuickLink.dataList =
                firstList.map { quickLink -> Mappers.remoteToHomeQuickLink(quickLink) }
        }

        data.add(homeQuickLink)
        postHomeData()
    }

    suspend fun fetchFlashDeals() {
        val flashDeals =
            withContext(Dispatchers.Default) { loadFlashDealUseCase.fromRemote() }.data
        val homeDeal = HomeView.HomeFlashDeal(dataList = flashDeals?.map { flashDeal ->
            Mappers.remoteToHomeFlashDeal(flashDeal)
        })
        data.add(homeDeal)
        postHomeData()
    }

    fun postHomeData() = viewModelScope.launch {
        homeLiveData.postValue(data)
    }

    fun clearData() {
        data.clear()
        homeLiveData.postValue(data)
    }

    companion object {
        const val POSITION_BANNER = 0
        const val POSITION_QUICKLINK = 1
        const val POSITION_FLASHDEAL = 2

    }
}