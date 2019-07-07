package com.android.hipart_android.ui.hipat.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.ui.hipart.HipartDetailActivity
import com.android.hipart_android.ui.hipat.data.PortFolioData
import com.android.hipart_android.ui.main.MainActivity
import com.android.hipart_android.ui.mypage.MyPickActivity
import org.jetbrains.anko.startActivity

class PortFolioRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<PortFolioData>, val mainActivityFlag : Boolean) :
    RecyclerView.Adapter<PortFolioRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.rv_item_hipat_frag_portfolio, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.user_image_thumbnail.setImageResource(R.drawable.main_profile_photo_image)
        holder.user_name.text = dataList[position].user_name
        holder.kind_of_pat.text = dataList[position].kind_of_pat
        when (dataList[position].how_many_filter) {
            0 -> {
                holder.img_filter_holder1.visibility = View.GONE
                holder.img_filter_holder2.visibility = View.GONE
                holder.img_filter_more.visibility = View.GONE
            }
            1 -> {
                holder.img_filter_holder2.visibility = View.GONE
                holder.img_filter_more.visibility = View.GONE
            }
            2 -> {
                holder.img_filter_more.visibility = View.GONE
            }
        }
        holder.is_picked.isSelected = dataList[position].is_picked
        holder.how_picked.text = dataList[position].how_picked.toString()


            holder.root.setOnClickListener { ctx!!.startActivity<HipartDetailActivity>() }

        if(mainActivityFlag == true){
            holder.is_picked.setOnClickListener {
                if(holder.is_picked.isSelected == false) {
                    (ctx as MainActivity).setAnimPickIcon()
                }
                holder.is_picked.isSelected = !holder.is_picked.isSelected
            }
        }else {
            holder.is_picked.setOnClickListener {
                if(holder.is_picked.isSelected == false) {
                    (ctx as MyPickActivity).setAnimPickIcon()
                }
                holder.is_picked.isSelected = !holder.is_picked.isSelected
            }
        }

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var user_image_thumbnail = itemView.findViewById(R.id.img_rv_item_hipat_frag_port_user_image) as ImageView
        var user_name = itemView.findViewById(R.id.img_rv_item_hipat_frag_port_user_name) as TextView
        var kind_of_pat = itemView.findViewById(R.id.txt_hipat_frag_kind_of_pat) as TextView
        var img_filter_holder1 = itemView.findViewById(R.id.img_hipat_frag_filter_holder1) as ImageView
        var img_filter_holder2 = itemView.findViewById(R.id.img_hipat_frag_filter_holder2) as ImageView
        var img_filter_more = itemView.findViewById(R.id.img_hipat_frag_filter_more) as ImageView
        var is_picked = itemView.findViewById(R.id.img_rv_item_hipat_frag_pick) as ImageView
        var how_picked = itemView.findViewById(R.id.txt_rv_item_hipat_frag_how_picked) as TextView
        var root = itemView.findViewById(R.id.btn_rv_item_hipat_frag_port_root) as RelativeLayout

    }

}