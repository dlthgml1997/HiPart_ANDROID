package com.android.hipart_android.ui.ad_manage_admin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.hipart_android.R
import com.bumptech.glide.Glide

class ManageAdRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<ManageAdData>): RecyclerView.Adapter<ManageAdRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_manage_ad_act_advertisement, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(dataList[position].img)
            .into(holder.image)
        holder.title.text = dataList[position].title
        holder.context.text = dataList[position].des
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById(R.id.img_rv_item_manage_ad) as ImageView
        val title = itemView.findViewById(R.id.txt_rv_item_manage_ad_title) as TextView
        val context = itemView.findViewById(R.id.txt_rv_item_manage_ad_more) as TextView
    }
}