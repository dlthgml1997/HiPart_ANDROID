package com.android.hipart_android.util

import android.content.Context
import org.json.JSONArray
import org.json.JSONException



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

    private val searchKey = "Search_Key"
    val searchHistoryList = ArrayList<String>()

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

    fun getUserType(context: Context) : Int {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        return pref.getInt(USER_TYPE, 0)
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

    fun addSearchHistory(context : Context, text : String) {
        for(i in 0..searchHistoryList.size-1) {
            if(searchHistoryList[i] == text) {

            }else{
                searchHistoryList.add(text)
                val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
                val editor = pref.edit()
                val ja = JSONArray()
                for(i in 0..searchHistoryList.size-1) {
                    ja.put(searchHistoryList[i])
                }
                if(!searchHistoryList.isEmpty()) {
                    editor.putString(searchKey,ja.toString())
                }else {
                    editor.putString(searchKey, null)
                }
                editor.apply()
            }
        }

    }

    fun removeSearchHistory(context : Context, index : Int) {
        if(searchHistoryList.size>0) {
            searchHistoryList.removeAt(index)
            val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
            val editor = pref.edit()
            val ja = JSONArray()
            for (i in 0..searchHistoryList.size - 1) {
                ja.put(searchHistoryList[i])
            }
            if (!searchHistoryList.isEmpty()) {
                editor.putString(searchKey, ja.toString())
            } else {
                editor.putString(searchKey, null)
            }
            editor.apply()
        }
    }
    fun getSearchHistory(context : Context) : ArrayList<String> {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        val json : String = pref.getString(searchKey, "")
        val recentSearchList = ArrayList<String>()
        if(json != null) {
            try{
                val ja = JSONArray(json)
                for(i in 0..ja.length()-1) {
                    var searchText = ja.optString(i)
                    recentSearchList.add(searchText)
                }

            }catch(e : JSONException) {
                e.printStackTrace()
            }
        }

        return recentSearchList
    }


}