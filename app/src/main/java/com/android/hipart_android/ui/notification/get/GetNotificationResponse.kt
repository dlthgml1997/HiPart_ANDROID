package com.android.hipart_android.ui.notification.get

data class GetNotificationResponse(
    val data: ArrayList<MyNotificationData>,
    val message: String,
    val status: Int,
    val success: Boolean
)