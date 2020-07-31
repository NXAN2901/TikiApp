package com.example.tikiapp.data.remote.api.response

import com.example.tikiapp.common.domain.entity.Tab
import com.google.gson.annotations.SerializedName

data class FlashDealResponse<T>(
    @SerializedName("data") override val data: T?,
    @SerializedName("tabs") val tabs: ArrayList<Tab>?,
    @SerializedName("version") val version: String?
) : BaseResponse<T>()