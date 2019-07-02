package com.android.hipart_android.ui.hipat.data

data class PortFolioData (
    val user_image_thumbnail: String,
    val user_name: String,
    val kind_of_pat: String,
    val is_picked: Boolean,
    val how_picked : Int,
    val message: String,
    val how_many_filter: Int
)