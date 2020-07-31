package com.example.tikiapp.data.remote.api.response

abstract class BaseResponse<T> {
    abstract val data: T?
}