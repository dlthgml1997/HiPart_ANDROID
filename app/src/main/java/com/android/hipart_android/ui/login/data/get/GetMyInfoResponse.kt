package com.android.hipart_android.ui.login.data.get

data class GetMyInfoResponse(
    val data: List<GetMyInfoData>,
    val message: String,
    val status: Int,
    val success: Boolean
)

data class GetMyInfoData(
    val hifive: Int,
    val notistate: Int,
    val pick: Int,
    val point: Int,
    val refresh_token: String,
    val salt: String,
    val user_email: String,
    val user_idx: Int,
    val user_img: String,
    val user_nickname: String,
    val user_number: String,
    val user_pw: String,
    val user_type: Int
)