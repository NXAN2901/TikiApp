package com.example.tikiapp.screens.home

import com.example.tikiapp.BR
import com.example.tikiapp.R
import com.example.tikiapp.databinding.ActivityHomeBinding
import com.example.tikiapp.screens.base.BaseActivity
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.home_header_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeNavigator,
    AppBarLayout.OnOffsetChangedListener {

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

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val isZeroProgress = collapseToolbar.progress == 0f
        if (swipeRefresh.isEnabled != isZeroProgress) {
            swipeRefresh.isEnabled = isZeroProgress
        }
        val progress = -verticalOffset / appBar.totalScrollRange.toFloat()

        collapseToolbar.setDrawProgress(progress)
        homeContent.progress = progress
    }

    override fun onResume() {
        super.onResume()
        appBar.addOnOffsetChangedListener(this)
    }

    override fun onPause() {
        super.onPause()
        appBar.removeOnOffsetChangedListener(this)
    }

}