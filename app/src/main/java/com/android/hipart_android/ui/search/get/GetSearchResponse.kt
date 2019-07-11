package com.android.hipart_android.ui.search.get

data class GetSearchResponse(
    val status : Int,
    val success : Boolean,
    val message : String,
    var data : ArrayList<User>
)

data class User(
    var pickState : Int,
    var info : ArrayList<SearchUserDetail>
)

data class SearchUserDetail(
    var user_idx : Int,
    var user_email : String?,
    var user_nickname : String?,
    var user_img : String?,
    var user_pw : String?,
    var user_number : String?,
    var user_type : Int,
    var point : Int,
    var pick : Int,
    var hifive : Int,
    var salt : String?,
    var refresh_token : String?,
    var notistate : Int,
    var detail_idx : Int,
    var detail_platform : Int,
    var detail_subscriber : String?,
    var detail_oneline : String?,
    var detail_appeal : String?,
    var detail_want : String?,
    var concept : Int,
    var lang : Int,
    var pd : Int,
    var etc : Int,
    var createdAt : String?
)
    /*
    "detail_oneline": "나를 대려가",
            "concept": 0,
            "lang": 0,
            "pd": 0,
            "etc": 0
     */
