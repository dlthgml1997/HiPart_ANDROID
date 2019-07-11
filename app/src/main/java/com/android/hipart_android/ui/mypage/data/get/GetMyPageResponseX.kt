package com.android.hipart_android.ui.mypage.data.get

data class GetMyPageResponse(
    val data: GetMyPageData,
    val message: String,
    val status: Int,
    val success: Boolean
)

data class GetMyPageData(
    val detail_platform: Int,
    val hifive: Int,
    val pick: Int,
    val point: Int,
    val user_img: String,
    val user_nickname: String,
    val user_type: Int
)
