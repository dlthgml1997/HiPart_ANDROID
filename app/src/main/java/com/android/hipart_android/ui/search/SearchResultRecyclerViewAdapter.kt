package com.android.hipart_android.ui.search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.util.Filter
import com.bumptech.glide.Glide
import org.jetbrains.anko.layoutInflater

class SearchResultRecyclerViewAdapter (val ctx : Context, val dataList : ArrayList<User>) : RecyclerView.Adapter<SearchResultRecyclerViewAdapter.Holder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = ctx.layoutInflater.inflate(R.layout.item_frag_search, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //사진
        Glide.with(ctx)
            .load(dataList[position].user_img)
            .into(holder.photo)
        //이름
        holder.name.text = dataList[position].user_nickname
        //플랫폼
        holder.platform.setImageResource(Filter.platform(dataList[position].detail_platform))
        //타입
        holder.type.text = Filter.type(dataList[position].user_type)
        //pd
        holder.pd.text = Filter.pd(dataList[position].pd)

        //3가지 태그 리사이클러

        //소개
        holder.intro.text = dataList[position].detail_oneline

        //픽 수
        holder.pick_num.text = dataList[position].pick.toString()



    }

    inner class Holder( itemView : View) : RecyclerView.ViewHolder(itemView) {
        val photo = itemView.findViewById(R.id.iv_item_frag_search_photo) as ImageView
        val name = itemView.findViewById(R.id.tv_item_frag_sear_name) as TextView
        val platform = itemView.findViewById(R.id.iv_item_frag_search_platform) as ImageView
        val type = itemView.findViewById(R.id.tv_item_frag_search_type) as TextView
        val pd = itemView.findViewById(R.id.tv_item_frag_search_pd) as TextView
        val concept = itemView.findViewById(R.id.tv_item_frag_search_concept) as TextView
        val language = itemView.findViewById(R.id.tv_item_frag_search_lang) as TextView
        val etc = itemView.findViewById(R.id.tv_item_frag_search_etc) as TextView
        val intro = itemView.findViewById(R.id.tv_item_frag_search_intro) as TextView
        val pick_num = itemView.findViewById(R.id.tv_item_frag_search_pick_num) as TextView

    }
}