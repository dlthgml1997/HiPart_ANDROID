package com.android.hipart_android.ui.modifyportfolio.get



import com.google.gson.annotations.SerializedName

data class GetModifyPortFolioResponseEpatAndEtc(
    @SerializedName("status")
    var status: Int,
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("message")
    var message: String,
    @SerializedName("data")
    var data: GetModifyPortFolioDataEpatAndEtc
)

//에디터
data class GetModifyPortFolioDataEpatAndEtc(
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
    var workIdx: List<Int>,
    @SerializedName("thumbnail")
    var thumbnail: List<String>,
    @SerializedName("url")
    var url: List<String>,
    @SerializedName("title")
    var title: List<String>,
    @SerializedName("content")
    var content: List<String>,
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
