package com.android.hipart_android.ui.modifyportfolio.data

import com.google.gson.annotations.SerializedName

data class ModifyList(
    @SerializedName("detail_platform")
    var detailPlatform : Int,
    @SerializedName("detail_subscriber")
    var detailSubscriber : String,
    @SerializedName("detail_oneline")
    var detailOneline: String,
    @SerializedName("detail_want")
    var detailWant: String,
    @SerializedName("detail_appeal")
    var detailAppeal: String,
    @SerializedName("concept")
    var concept: Int,
    @SerializedName("lang")
    var lang: Int,
    @SerializedName("pd")
    var pd: Int,
    @SerializedName("etc")
    var etc: Int
)