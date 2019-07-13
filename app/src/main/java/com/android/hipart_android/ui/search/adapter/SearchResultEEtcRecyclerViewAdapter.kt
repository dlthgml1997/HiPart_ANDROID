package com.android.hipart_android.ui.search.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.hipart_android.BuildConfig
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.ui.hipart.HipartDetailActivity
import com.android.hipart_android.ui.hipart.HipartDetailTagRecyclerAdapter
import com.android.hipart_android.ui.home.data.post.PickDTO
import com.android.hipart_android.ui.home.data.post.PickResponse
import com.android.hipart_android.ui.search.SearchActivity
import com.android.hipart_android.ui.search.get.User
import com.android.hipart_android.util.Filter
import com.android.hipart_android.util.SharedPreferenceController
import com.bumptech.glide.Glide
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultEEtcRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<User>) :
    RecyclerView.Adapter<SearchResultEEtcRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = ctx.layoutInflater.inflate(R.layout.item_frag_search, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.item.setOnClickListener {
            if (SharedPreferenceController.getNickName(ctx) == BuildConfig.TEST_USER_NCIKNAME) {
                (ctx as SearchActivity).showGoToLoginDialog()
            } else {
                ctx!!.startActivity<HipartDetailActivity>(
                    "user_nickname" to dataList[position].info[0].user_nickname,
                    "user_type" to dataList[position].info[0].user_type
                )
            }

        }


        //사진
        Glide.with(ctx)
            .load(dataList[position].info[0].user_img)
            .into(holder.photo)
        //이름
        holder.name.text = dataList[position].info[0].user_nickname
        //플랫폼
        holder.platform.setImageResource(Filter.platform(dataList[position].info[0].detail_platform))
        //타입
        holder.type.text = Filter.type(dataList[position].info[0].user_type)
        //pd
        if (dataList[position].info[0].pd != 0) {
            holder.pd.text = Filter.pd(dataList[position].info[0].pd)
        } else {
            holder.pd_bg.visibility = View.GONE
        }

        //3가지 태그 리사이클러
        var fullLength = 0
        val tagList = ArrayList<String>()
        if (dataList[position].info[0].concept != 0)
            tagList.add(Filter.concept(dataList[position].info[0].concept))
        if (dataList[position].info[0].lang != 0)
            tagList.add(Filter.language(dataList[position].info[0].lang))
        if (dataList[position].info[0].etc != 0)
            tagList.add(Filter.etc(dataList[position].info[0].etc))

        if (tagList.size > 0) {
            for (i in 0..tagList.size - 1) {
                fullLength += tagList[i].length
            }
            if (fullLength > 9) {
                if (dataList[position].info[0].pd == 0) {
                    if (tagList.size > 2) {
                        tagList.set(2, "...")
                    }
                }else {
                    if(tagList.size ==2) {
                        tagList.set(1,"...")
                        tagList.removeAt(2)
                    }else if(tagList.size ==3) {
                        tagList.set(2, "...")
                    }
                }
            }
            holder.tag.adapter = HipartDetailTagRecyclerAdapter(ctx, tagList)
            holder.tag.layoutManager =
                LinearLayoutManager(ctx, OrientationHelper.HORIZONTAL, false)
        }

        //소개
        holder.intro.text = dataList[position].info[0].detail_oneline


        val iniPickStatus = Filter.pick(dataList[position].pickState)
        var pickStatus = false


        //pick
        holder.pick.apply {
            if (iniPickStatus) {
                holder.pick.setBackgroundResource(R.drawable.main_pick_on_icon)
                pickStatus = iniPickStatus
            } else {
                holder.pick.setBackgroundResource(R.drawable.main_pick_off_icon)
                pickStatus = iniPickStatus
            }
            setOnClickListener {
                if (SharedPreferenceController.getNickName(ctx) == BuildConfig.TEST_USER_NCIKNAME) {
                    (ctx as SearchActivity).showGoToLoginDialog()
                } else {
                    if (pickStatus == false) {
                        holder.pick.setBackgroundResource(R.drawable.main_pick_on_icon)
                        holder.pick_num.text = (dataList[position].info[0].pick + 1).toString()
                        addPick(dataList[position].info[0].user_nickname!!, false)
                        pickStatus = true
                    } else {
                        holder.pick.setBackgroundResource(R.drawable.main_pick_off_icon)
                        holder.pick_num.text = (dataList[position].info[0].pick).toString()
                        deletePick(dataList[position].info[0].user_nickname!!)
                        pickStatus = false
                    }
                }
            }
        }

        //픽 수
        holder.pick_num.text = dataList[position].info[0].pick.toString()


    }

    private fun addPick(nickName: String, mainActFlag: Boolean) {

        val networkService = ApplicationController.instance.networkService
        val addPick = networkService.addPick(SharedPreferenceController.getAuthorization(ctx!!), PickDTO(nickName))
        addPick.enqueue(object : Callback<PickResponse> {
            override fun onFailure(call: Call<PickResponse>, t: Throwable) {
                Log.e("Add Pick Error", Log.getStackTraceString(t))
            }

            override fun onResponse(call: Call<PickResponse>, response: Response<PickResponse>) {
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        when (it?.message ?: " ") {
                            "픽 성공" -> {
                                (ctx as SearchActivity).setAnimPickIcon()

                                (ctx as SearchActivity).getSearchResponse(SharedPreferenceController.getAuthorization(ctx))
                            }
                            " " -> {
                                Log.v("태그", "message가 널인데 ?")
                            }
                            "닉네임을 가진 유저가 없습니다." -> {
                                Log.v("태그", "닉네임을 가진 유저가 없다.")
                            }
                            // TODO : 픽리스트 없음
                            else -> {
                                Log.v("태그", it.message)
                            }
                        }
                    }
            }
        })
    }


    private fun deletePick(nickName: String) {

        val networkService = ApplicationController.instance.networkService
        val deletePick =
            networkService.deletePick(SharedPreferenceController.getAuthorization(ctx!!), PickDTO(nickName))
        deletePick.enqueue(object : Callback<PickResponse> {
            override fun onFailure(call: Call<PickResponse>, t: Throwable) {
                Log.e("Delete Pick Error", Log.getStackTraceString(t))
            }

            override fun onResponse(call: Call<PickResponse>, response: Response<PickResponse>) {
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        when (it?.message ?: " ") {

                            "픽 취소 성공" -> {
                                Log.v("태그", it.message)

                                (ctx as SearchActivity).getSearchResponse(SharedPreferenceController.getAuthorization(ctx))
                            }
                            " " -> {
                                Log.v("태그", "message가 널인데 ?")
                            }
                            "닉네임을 가진 유저가 없습니다." -> {
                                Log.v("태그", "닉네임을 가진 유저가 없다.")
                            }
                            // TODO : 픽리스트 없음
                            else -> {
                                Log.v("태그", it.message)
                            }
                        }
                    }
            }
        })
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item = itemView.findViewById<RelativeLayout>(R.id.rl_item_frag_search_item)
        val photo = itemView.findViewById(R.id.iv_item_frag_search_photo) as ImageView
        val name = itemView.findViewById(R.id.tv_item_frag_sear_name) as TextView
        val platform = itemView.findViewById(R.id.iv_item_frag_search_platform) as ImageView
        val type = itemView.findViewById(R.id.tv_item_frag_search_type) as TextView
        val pd_bg = itemView.findViewById<RelativeLayout>(R.id.rl_item_frag_search_pd)
        val pd = itemView.findViewById(R.id.tv_item_frag_search_pd) as TextView
        val tag = itemView.findViewById(R.id.rv_item_frag_search_tag) as RecyclerView
        val intro = itemView.findViewById(R.id.tv_item_frag_search_intro) as TextView
        val pick = itemView.findViewById(R.id.tb_item_frag_search_pick) as ImageView
        var pick_num = itemView.findViewById(R.id.tv_item_frag_search_pick_num) as TextView

    }
}