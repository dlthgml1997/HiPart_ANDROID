package com.android.hipart_android.ui.modifyportfolio.put

import com.google.gson.annotations.SerializedName

data class PutModifyPortFolioResponse(
    @SerializedName("status")
    var status: Int,
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("message")
    var message: String
)