package com.android.hipart_android.util

import android.content.Context


/**
 * Created by TakHyeongMin on 2019-07-06.
 */
object SharedPreferenceController {

    // 변수부
    private val USER_NAME = "MYKEY"
    private val kakaoKey = "kakao_Key"
    //807465239
    private val my_id = "my_id"

    private val USER_TYPE= "USER_TYPE"

    fun setAuthorization(context: Context, authorization : String){
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        val editor = pref.edit()
        editor.putString(kakaoKey, authorization)
        editor.commit()
    }

    fun getAuthorization(context: Context) : String {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        return pref.getString(kakaoKey, "")
    }

    fun setUserType(context: Context, userType : Int){
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        val editor = pref.edit()
        editor.putInt(USER_TYPE, userType)
        editor.commit()
    }

    fun getUserType(context: Context) : String {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        return pref.getString(USER_TYPE, "")
    }

    fun setMyId(context: Context, id : String){
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        val editor = pref.edit()
        editor.putString(my_id, id)
        editor.commit()
    }

    fun getMyId(context: Context) : String {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        return pref.getString(my_id, "")
    }

    fun clearSPC(context: Context){
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        val editor = pref.edit()
        editor.clear()
        editor.commit()
    }
}