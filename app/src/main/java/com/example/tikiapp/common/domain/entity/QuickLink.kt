package com.example.tikiapp.common.domain.entity

import com.google.gson.annotations.SerializedName

data class QuickLink(

    @SerializedName("title")
    var title: String = "",

    @SerializedName("content")
    var content: String = "",

    @SerializedName("url")
    var url: String = "",

    @SerializedName("authentication")
    var authentication: Boolean = false,

    @SerializedName("web_url")
    var webUrl: String = "",

    @SerializedName("image_url")
    var imageUrl: String = ""

)