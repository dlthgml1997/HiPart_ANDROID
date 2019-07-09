package com.android.hipart_android.ui.login.data

data class RefreshTokenResponse(
    val data: RefreshToken,
    val message: String,
    val status: Int,
    val success: Boolean
)

data class RefreshToken(
    val token : String
)

