package com.example.tikiapp.screens.base

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel<N : BaseNavigator>(application: Application) :
    AndroidViewModel(application) {
    private lateinit var navigator: WeakReference<N>

    private val isLoading: ObservableBoolean = ObservableBoolean()

    fun isShowLoading() = isLoading

    fun setShowLoading(isShown: Boolean) {
        isLoading.set(isShown)
    }

}