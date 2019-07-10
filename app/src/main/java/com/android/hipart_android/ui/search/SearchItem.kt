package com.android.hipart_android.ui.search

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

class SearchItem : Parcelable {
    //    user_img : String?,

    var user_img: String = ""
    var user_nickname: String = ""
    var user_type: Int = 0
    var pick: Int = 0
    var detail_platform: Int = 0
    var detail_oneline: String = ""
    var concept: Int = 0
    var lang: Int = 0
    var pd: Int = 0
    var etc: Int = 0

    constructor(user_nickname : String,
                user_type : Int,
                pick : Int,
                detail_platform : Int,
                detail_oneline : String,
                concept : Int,
                lang : Int,
                pd : Int,
                etc : Int) {
        this.user_nickname = user_nickname
        this.user_type = user_type
        this.pick = pick
        this.detail_platform = detail_platform
        this.detail_oneline = detail_oneline
        this.concept = concept
        this.lang = lang
        this.pd = pd
        this.etc = etc
    }

    constructor(`in`: Parcel) {
        user_nickname = `in`.readString()
        user_type = `in`.readInt()
        pick =`in`.readInt()
        detail_platform = `in`.readInt()
        detail_oneline = `in`.readString()
        concept = `in`.readInt()
        lang = `in`.readInt()
        pd = `in`.readInt()
        etc = `in`.readInt()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(user_nickname)
        dest.writeInt(user_type)
        dest.writeInt(pick)
        dest.writeInt(detail_platform)
        dest.writeString(detail_oneline)
        dest.writeInt(concept)
        dest.writeInt(lang)
        dest.writeInt(pd)
        dest.writeInt(etc)

    }

    companion object {

        @SuppressLint("ParcelCreator")
        val CREATOR: Parcelable.Creator<SearchItem> = object : Parcelable.Creator<SearchItem> {
            override fun createFromParcel(`in`: Parcel): SearchItem {
                return SearchItem(`in`)
            }

            override fun newArray(size: Int): Array<SearchItem?> {
                return arrayOfNulls(size)
            }
        }
    }
}


//class SearchItem(in : Parcel) : Parcelable {
//
//
//    constructor(){
//
//    }
//
//    fun Item2(index: Int, address: String, title: String): ??? {
//        this.index = index
//        this.address = address
//        this.title = title
//    }
//
//    override fun writeToParcel(dest: Parcel?, flags: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun describeContents(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}