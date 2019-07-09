package com.android.hipart_android.ui.mypage.data

data class GetMyPickData(
    val user_img: String,
    val user_nickname: String,
    val user_type: Int,
    val pick: Int,
    val detail_platform: Int,
    val detail_field: Int,
    val detail_oneline: String,
    val concept: Int,
    val lang: Int,
    val pd: Int,
    val etc: Int
)