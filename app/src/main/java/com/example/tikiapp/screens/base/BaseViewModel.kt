package com.example.tikiapp.screens.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel<N : BaseNavigator>(application: Application) :
    AndroidViewModel(application) {
    private lateinit var navigator: WeakReference<N>

}