package com.android.hipart_android.util

import com.android.hipart_android.R

object Filter {

    fun type(num : Int) : String{
        return when(num) {
            0 -> "게임"
            1 -> "크리에이터"
            2 -> "에디터"
            3 -> "편집자"
            4 -> "기타"
            else -> ""
        }
    }

    fun platform(num: Int) : Int{
        return when(num){
            1 -> R.drawable.youtube_grey_img
            2 -> R.drawable.afreeca_grey_img
            3 -> R.drawable.twitch_grey_img
            else -> 0
        }
    }

    fun pd(num: Int) : String{
        return when(num) {
            1 -> "편집"
            2 -> "기획"
            else -> ""
        }
    }

    fun concept(num: Int) : String {
        return when(num) {
            1 -> "게임"
            2 -> "ASMR"
            3 ->"프랭크"
            4 -> "스포츠"
            5 -> "먹방/쿡"
            6 -> "영화/음악"
            7 -> "교육/정보"
            else -> ""
        }
    }

}