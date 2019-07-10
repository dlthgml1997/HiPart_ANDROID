package com.android.hipart_android.ui.mypick.data

data class GetMyPickResponse(
    val data: ArrayList<GetMyPickData>,
    val message: String,
    val status: Int,
    val success: Boolean
)

data class GetMyPickData(
    val info: ArrayList<Info>,
    val pickState: Int
)

data class Info(
    val concept: Int,
    val detail_oneline: String,
    val detail_platform: Int,
    val etc: Int,
    val lang: Int,
    val pd: Int,
    val pick: Int,
    val user_img: String,
    val user_nickname: String,
    val user_type: Int
)