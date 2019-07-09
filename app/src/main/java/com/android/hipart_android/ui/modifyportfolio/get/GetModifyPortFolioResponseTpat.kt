package com.android.hipart_android.ui.modifyportfolio.get

import com.google.gson.annotations.SerializedName

data class GetModifyPortFolioResponseTpat(
    @SerializedName("status")
    var status: Int,
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("message")
    var message: String,
    @SerializedName("data")
    var data: GetModifyPortFolioDataTpat
)

//트랜슬레이터
data class GetModifyPortFolioDataTpat(
    @SerializedName("user_nickname")
    var userNickname: String,
    @SerializedName("user_img")
    var userImg: String,
    @SerializedName("user_type")
    var userType: Int,
    @SerializedName("detail_platform")
    var detailPlatform: Int,
    @SerializedName("detail_oneline")
    var detailOneline: String,
    @SerializedName("detail_appeal")
    var detailAppeal: String,
    @SerializedName("detail_want")
    var detailWant: String,
    @SerializedName("work_idx")
    var workIdx: ArrayList<Int>,
    @SerializedName("before")
    var before: ArrayList<String>,
    @SerializedName("after")
    var after: ArrayList<String>,
    @SerializedName("hifive")
    var hifive: Int,
    @SerializedName("pick")
    var pick: Int,
    @SerializedName("concept")
    var concept: Int,
    @SerializedName("lang")
    var lang: Int,
    @SerializedName("pd")
    var pd: Int,
    @SerializedName("etc")
    var etc: Int
)