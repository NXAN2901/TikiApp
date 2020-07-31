package com.example.tikiapp.common.domain.entity

import com.google.gson.annotations.SerializedName

class FlashDeal(

    @SerializedName("status")
    var status: Int = 0,

    @SerializedName("url")
    var url: String = "",

    @SerializedName("tags")
    var tags: String = "",

    @SerializedName("discount_percent")
    var discountPercent: Int = 0,

    @SerializedName("special_price")
    var specialPrice: Int = 0,

    @SerializedName("special_from_date")
    var specialFromDateTimeStamp: Long = 0,

    @SerializedName("from_date")
    var fromDate: String = "",

    @SerializedName("special_to_date")
    var specialToDateTimeStamp: Long = 0,

    @SerializedName("progress")
    var progress: Progress? = null,

    @SerializedName("product")
    var product: Product? = null

)

data class Product(

    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("sku")
    var sku: String? = null,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("url_path")
    var urlPath: String = "",

    @SerializedName("price")
    var price: Int = 0,

    @SerializedName("list_price")
    var listPrice: Int = 0,

    @SerializedName("badges")
    var badges: List<Any> = emptyList(),

    @SerializedName("discount")
    var discount: Int = 0,

    @SerializedName("rating_average")
    var ratingAverage: Int = 0,

    @SerializedName("review_count")
    var review_count: Int = 0,

    @SerializedName("order_count")
    var orderCount: Int = 0,

    @SerializedName("thumbnail_url")
    var thumbnailUrl: String = "",

    @SerializedName("is_visible")
    var is_visible: Boolean = false,

    @SerializedName("is_fresh")
    var isFresh: Boolean = false,

    @SerializedName("is_flower")
    var isFlower: Boolean = false,

    @SerializedName("is_gift_card")
    var isGiftCard: Boolean = false,

    @SerializedName("inventory")
    var inventor: Inventor? = null,

    @SerializedName("url_attendant_input_form")
    var urlAttendantInputForm: String = "",

    @SerializedName("master_id")
    var masterID: Int = 0,

    @SerializedName("seller_product_id")
    var sellerProductID: Int = 0,

    @SerializedName("price_prefix")
    var pricePrefix: String = ""

)

data class Inventor(

    @SerializedName("product_virtual_type")
    var productVirtualType: Int? = null,

    @SerializedName("fulfillment_type")
    var fulfillType: String = ""
)

data class Tab(

    @SerializedName("query_value")
    var queryValue: Int = 0,

    @SerializedName("from_date")
    var fromDate: String = "",

    @SerializedName("to_date")
    var toDate: String = "",

    @SerializedName("display")
    var display: String = "",

    @SerializedName("active")
    var isActive: Boolean = false

)

data class Progress(
    @SerializedName("qty")
    var qty: Int = 0,

    @SerializedName("qty_ordered")
    var qtyOrdered: Int = 0,

    @SerializedName("qty_remain")
    var qtyRemain: Int = 0,

    @SerializedName("percent")
    var percent: Double = 0.toDouble(),

    @SerializedName("status")
    var status: String = ""
)


