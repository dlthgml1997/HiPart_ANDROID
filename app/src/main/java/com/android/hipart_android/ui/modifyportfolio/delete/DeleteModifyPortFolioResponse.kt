package com.android.hipart_android.ui.modifyportfolio.delete


import com.google.gson.annotations.SerializedName

data class DeleteModifyPortFolioResponse(
    @SerializedName("status")
    var status: Int,
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("message")
    var message: String
)