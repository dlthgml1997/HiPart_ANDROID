package com.android.hipart_android.ui.search

data class GetSearchResponse(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : ArrayList<User>
)

data class User(
    val user_img : String?,
    val user_nickname : String?,
    val user_type : Int,
    val pick : Int,
    val detail_platform : Int,
    val detail_oneline : String?,
    val concept : Int,
    val lang : Int,
    val pd : Int,
    val etc : Int
    /*
    "detail_oneline": "나를 대려가",
            "concept": 0,
            "lang": 0,
            "pd": 0,
            "etc": 0
     */

)