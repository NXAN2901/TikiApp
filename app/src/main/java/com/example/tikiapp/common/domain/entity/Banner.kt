package com.example.tikiapp.common.domain.entity

import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("id")
    var id: String ="",
    @SerializedName("title")
    var content: String = "",
    @SerializedName("content")
    var url: String = "",
    @SerializedName("image_url")
    var imageUrl: String = "",
    @SerializedName("thumbnail_url")
    var thumbnailUrl: String = "",
    @SerializedName("mobile_url")
    var mobileUrl: String = "",
    @SerializedName("ratio")
    var ratio: Double = 3.0
)