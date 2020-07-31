package com.example.tikiapp.screens.home

import com.example.tikiapp.BR
import com.example.tikiapp.R
import com.example.tikiapp.databinding.ActivityHomeBinding
import com.example.tikiapp.screens.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun initViews() {
        getViewModel().fetchData()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun getViewModel(): HomeViewModel {
        return homeViewModel
    }

    override fun getViewModelVariable(): Int = BR.viewModel

}