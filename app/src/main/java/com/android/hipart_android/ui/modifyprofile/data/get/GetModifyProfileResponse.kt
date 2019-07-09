package com.android.hipart_android.ui.modifyprofile.data.get

data class GetModifyProfileResponse(
    val data: ArrayList<GetModifyProfileData>,
    val message: String,
    val status: Int,
    val success: Boolean
)

data class GetModifyProfileData(
    val user_img: String,
    val user_nickname: String,
    val user_number: String,
    val user_type: Int
)
