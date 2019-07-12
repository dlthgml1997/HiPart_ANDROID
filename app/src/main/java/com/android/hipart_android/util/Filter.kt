package com.android.hipart_android.util

import com.android.hipart_android.R

object Filter {

    fun type(num: Int): String {
        return when (num) {
            1 -> "Creator"
            2 -> "Editor"
            3 -> "Translator"
            4 -> "Etc."
            else -> ""
        }
    }

    fun typePat(num: Int): String {
        return when (num) {
            0 -> ""
            1 -> "C-PAT"
            2 -> "E-PAT"
            3 -> "T-PAT"
            else -> "ETC"
        }
    }

    fun platform(num: Int): Int {
        return when (num) {
            1 -> R.drawable.youtube_grey_img
            2 -> R.drawable.afreeca_grey_img
            3 -> R.drawable.twitch_grey_img
            else -> 0
        }
    }

    fun pd(num: Int): String {
        return when (num) {
            1 -> "편집"
            2 -> "기획"
            else -> ""
        }
    }

    fun concept(num: Int): String {
        return when (num) {
            1 -> "게임"
            2 -> "ASMR"
            3 -> "Prank"
            4 -> "스포츠"
            5 -> "쿡/먹방"
            6 -> "영화/음악"
            7 -> "교육/정보"
            else -> ""
        }
    }

    fun language(num: Int): String {
        return when (num) {
            1 -> "영어"
            2 -> "일본어"
            3 -> "중국어"
            4 -> "독일어"
            5 -> "인도어"
            6 -> "러시아어"
            7 -> "프랑스어"
            8 -> "스페인어"
            9 -> "베트남어"
            10 -> "이탈리아어"
            11 -> "인도네시아어"
            else -> ""
        }
    }

    fun etc(num: Int): String {
        return when (num) {
            1 -> "소풍"
            2 -> "코디"
            3 -> "조명"
            4 -> "촬영"
            5 -> "매니저"
            6 -> "썸네일"
            else -> ""
        }
    }

    fun pick(num: Int): Boolean {
        return when (num) {
            0 -> false
            1 -> true
            else -> false
        }
    }


}