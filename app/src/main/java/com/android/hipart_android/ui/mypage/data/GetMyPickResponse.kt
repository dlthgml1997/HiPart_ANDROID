package com.android.hipart_android.ui.mypage.data

data class GetMyPickResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: ArrayList<GetMyPickData>
)