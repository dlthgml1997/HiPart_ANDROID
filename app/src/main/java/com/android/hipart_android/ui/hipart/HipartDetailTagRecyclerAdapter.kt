package com.android.hipart_android.ui.hipart

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.hipart_android.R
import org.jetbrains.anko.layoutInflater

class HipartDetailTagRecyclerAdapter(val ctx : Context, val dataList : ArrayList<String>?) : RecyclerView.Adapter<HipartDetailTagRecyclerAdapter.Holder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = ctx.layoutInflater.inflate(R.layout.item_frag_hip_det_tag, p0, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tag!!.text = dataList!![position]
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tag = itemView.findViewById(R.id.tv_item_frag_hip_det_c_tag) as TextView?
    }
}