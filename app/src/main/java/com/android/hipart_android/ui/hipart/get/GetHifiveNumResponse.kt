package com.android.hipart_android.ui.hipart.data

data class GetHifiveNumResponse(
    val `data`: HifiveData,
    val message: String,
    val status: Int,
    val success: Boolean
)