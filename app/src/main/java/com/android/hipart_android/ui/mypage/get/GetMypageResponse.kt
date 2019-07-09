package com.android.hipart_android.ui.mypage.get

data class GetMypageResponse(

    val `data`: ArrayList<MypageInfoData>,
    val message: String,
    val status: Int,
    val success: Boolean
)