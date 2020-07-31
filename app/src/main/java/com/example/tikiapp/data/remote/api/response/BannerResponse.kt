package com.example.tikiapp.data.remote.api.response

import com.google.gson.annotations.SerializedName

class BannerResponse<T>(
    @SerializedName("data") override val data: T?
) : BaseResponse<T>()