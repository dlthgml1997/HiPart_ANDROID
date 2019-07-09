package com.android.hipart_android.ui.login.data

data class PostLoginResponse(
    val data: PostLoginData,
    val message: String,
    val status: Int,
    val success: Boolean
)

data class PostLoginData(
    val tokens: Tokens,
    val user_type: Int
)

data class Tokens(
    val refreshToken: String,
    val token: String
)