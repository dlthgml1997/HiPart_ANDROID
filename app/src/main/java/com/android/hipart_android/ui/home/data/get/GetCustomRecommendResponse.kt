package com.android.hipart_android.ui.home.data.get

data class GetCustomRecommendResponse(
    val data: GetCustomRecommendData,
    val message: String,
    val status: Int,
    val success: Boolean
)

data class GetCustomRecommendData(
    val nickname: String,
    val resData: ArrayList<ResData>
)

data class ResData(
    val info: ArrayList<Info>,
    val pickState: Int
)

data class Info(
    val concept: Int,
    val detail_field: Int,
    val detail_oneline: String,
    val detail_platform: Int,
    val etc: Int,
    val lang: Int,
    val pd: Int,
    val pick: Int,
    val user_idx: Int,
    val user_img: String,
    val user_nickname: String,
    val user_type: Int
)