package com.example.tikiapp.utils

import android.view.View

interface BaseViewItem<T> {
    val view: View
    fun bind(item: T)
}