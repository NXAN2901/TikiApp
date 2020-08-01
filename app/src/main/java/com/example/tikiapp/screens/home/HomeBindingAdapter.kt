package com.example.tikiapp.screens.home

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.tikiapp.screens.home.content.HomeContentAdapter
import com.example.tikiapp.screens.home.models.HomeView

@BindingAdapter(value = ["homeData", "isShowLoading"], requireAll = false)
fun homeData(
    recyclerview: RecyclerView,
    homeData: LiveData<List<HomeView>>?,
    isShowLoading: ObservableBoolean
) {
    if (recyclerview.adapter == null) {
        recyclerview.apply {
            adapter = HomeContentAdapter()
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    (recyclerview.adapter as HomeContentAdapter).apply {
        showLoading(isShowLoading.get())
        homeData?.value?.let {
            setHomeData(it)
        }
    }
}

@BindingAdapter(value = ["onRefresh", "isRefreshing"], requireAll = false)
fun homePullRefresh(swipeRefreshLayout: SwipeRefreshLayout, onRefresh: SwipeRefreshLayout.OnRefreshListener?, isRefreshing: Boolean) {
    swipeRefreshLayout.isRefreshing = isRefreshing
    swipeRefreshLayout.setOnRefreshListener(onRefresh)
}
