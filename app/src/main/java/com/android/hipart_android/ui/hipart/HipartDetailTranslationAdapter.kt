package com.android.hipart_android.ui.hipart

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.hipart_android.R
import org.jetbrains.anko.layoutInflater

class HipartDetailTranslationAdapter (val ctx : Context, val dataList : ArrayList<HipartDetailTranslationData>): RecyclerView.Adapter<HipartDetailTranslationAdapter.Holder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = ctx.layoutInflater.inflate(R.layout.item_frag_hip_det_t_article, p0, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.before.text = dataList[position].before
        holder.after.text = dataList[position].after
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val before = itemView.findViewById<TextView>(R.id.tv_item_frag_hip_det_t_before)
        val after = itemView.findViewById<TextView>(R.id.tv_item_frag_hip_det_t_after)
    }
}