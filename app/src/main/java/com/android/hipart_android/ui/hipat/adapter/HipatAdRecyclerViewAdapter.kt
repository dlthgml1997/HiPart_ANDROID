package com.android.hipart_android.ui.hipat.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.android.hipart_android.R
import com.android.hipart_android.ui.hipat.AdData
import com.bumptech.glide.Glide
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.backgroundColorResource
import org.jetbrains.anko.image

class HipatAdRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<AdData>) :
    RecyclerView.Adapter<HipatAdRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.rv_item_hipat_frag_advertisement, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = 4

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        p0.img_thumbnail.setImageResource(R.drawable.hipat_ad_1_img)
//        Glide.with(ctx)
//            .load(dataList[p1].img_url)
//            .into(p0.img_thumbnail)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_thumbnail = itemView.findViewById(R.id.img_rv_item_hipat_frag_ad) as ImageView
    }
}