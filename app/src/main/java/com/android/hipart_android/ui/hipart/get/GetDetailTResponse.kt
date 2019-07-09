package com.android.hipart_android.ui.hipart.get

import java.io.Serializable

data class GetDetailTResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: DetailTData
)

data class DetailTData(
    val hifiveState: Int,
    val resData: UserDetailTData
)

/*
 "user_nickname": "bj_ho",
            "user_img": "https://igbb.s3.ap-northeast-2.amazonaws.com/",
            "user_type": 1,
            "detail_platform": 1,
            "detail_subscriber": "11",
            "detail_oneline": "한줄소",
            "detail_appeal": "학력/경력/빠빠1",
            "detail_want": "나는 이런사람을 ",
            "thumbnail": [
                "https://igbb.s3.ap-northeast-2.amazonaws.com/1562062301183.jpg",
                "https://igbb.s3.ap-northeast-2.amazonaws.com/1562062301183.jpg"
            ],
            "url": [
                "https:ig.com",
                "https:ig2.com"
            ],
            "title": [
                "작품1",
                "작품2"
            ],
            "content": [
                "내용은 짱짱 ",
                "내용2"
            ],
            "pick": 0,
            "concept": 0,
            "lang": 0,
            "pd": 0,
            "etc": 0
        }
    }
 */
data class UserDetailTData(
    var user_nickname: String,
    val user_img: String,
    var user_type: Int,
    val detail_platform: Int,
    val detail_oneline: String,
    val detail_detail: String,
    val detail_appeal: String,
    val detail_want: String,
    val work_idx: ArrayList<Int>,
    val before: ArrayList<String>?,
    val after: ArrayList<String>?,
    val pick: Int,
    val concept: Int,
    val lang: Int,
    val pd: Int,
    val etc: Int,
    val hifive: Int
) : Serializable
