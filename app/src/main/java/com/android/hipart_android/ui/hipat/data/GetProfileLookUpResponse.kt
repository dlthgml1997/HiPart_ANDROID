package com.android.hipart_android.ui.hipat.data

import com.android.hipart_android.ui.mypick.data.GetMyPickData

data class GetProfileLookUpResponse(
    val data: ArrayList<GetMyPickData>,
    val message: String,
    val status: Int,
    val success: Boolean
)