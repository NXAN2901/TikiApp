package com.example.tikiapp.screens.home

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tikiapp.screens.home.content.HomeContentAdapter

@BindingAdapter("homeData")
fun homeData(
    recyclerview: RecyclerView,
    homeData: MutableLiveData<List<HomeDataModel>>?
) {
    Log.e("ANNX", "BindingAdapter homeData $homeData")
    if (homeData == null) {
        return
    }

    if (recyclerview.adapter == null) {
        recyclerview.apply {
            adapter = HomeContentAdapter()
            layoutManager = LinearLayoutManager(recyclerview.context)
        }
    }

    (recyclerview.adapter as HomeContentAdapter).apply {
        homeData.value?.let {
            setHomeData(it)
        }
    }
}
