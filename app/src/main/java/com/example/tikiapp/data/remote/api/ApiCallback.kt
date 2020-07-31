package com.example.tikiapp.data.remote.api

import com.example.tikiapp.data.exception.AppError

interface APICallback<T> {
    fun onResult(result: T)
    fun onError(appError: AppError = AppError())
}