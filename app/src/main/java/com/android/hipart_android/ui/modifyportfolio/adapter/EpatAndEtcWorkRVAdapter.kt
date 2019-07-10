package com.android.hipart_android.ui.modifyportfolio.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.ui.modifyportfolio.ModifyPortFolioActivity
import com.android.hipart_android.ui.modifyportfolio.get.GetModifyPortFolioDataEpatAndEtc
import com.bumptech.glide.Glide

class EpatAndEtcWorkRVAdapter(val ctx: Context, var dataList : GetModifyPortFolioDataEpatAndEtc) : RecyclerView.Adapter<EpatAndEtcWorkRVAdapter.Holder>() {

    var removeIndexList: ArrayList<Int> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_frag_hip_det_c_article, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.thumbnail.size

    override fun onBindViewHolder(p0: Holder, p1: Int) {

        Glide.with(ctx)
            .load(dataList.thumbnail[p1])
            .into(p0.img)

        p0.cancleBtn.visibility = View.VISIBLE

        p0.title.text = dataList.title[p1]
        p0.description.text = dataList.content[p1]

        if(p1 == 0){
            val dp = ctx.resources.displayMetrics.density
            val rootLayoutParams = p0.root.layoutParams as RelativeLayout.LayoutParams
            rootLayoutParams.leftMargin = (25 * dp).toInt()
            p0.root.layoutParams = rootLayoutParams
        }else if(p1 == dataList.thumbnail.size - 1){
            val dp = ctx.resources.displayMetrics.density
            val rootLayoutParams = p0.root.layoutParams as RelativeLayout.LayoutParams
            rootLayoutParams.rightMargin = (19 * dp).toInt()
            p0.root.layoutParams = rootLayoutParams
        }


        p0.img.clipToOutline = true


        p0.cancleBtn.setOnClickListener {
            removeDataList(p1)
            //지워진 작품 인덱스 값 액티비티로 보내기
            removeIndexToActivity(p1)
            notifyDataSetChanged()
        }

    }

    private fun removeDataList(position: Int) {
        dataList.thumbnail.removeAt(position)
        dataList.url.removeAt(position)
        dataList.title.removeAt(position)
        dataList.content.removeAt(position)
    }

    private fun removeIndexToActivity(position: Int) {
        removeIndexList.add(dataList.workIdx[position])
        dataList.workIdx.removeAt(position)
        if(dataList.thumbnail.isEmpty())
            (ctx as ModifyPortFolioActivity).setRemoveIndexListFromAdapter(removeIndexList,0)
        else
            (ctx as ModifyPortFolioActivity).setRemoveIndexListFromAdapter(removeIndexList,1)
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var root = itemView.findViewById(R.id.root_item_frag_hip_detc_article) as RelativeLayout
        var img = itemView.findViewById(R.id.iv_item_frag_hip_det_c_acticle) as ImageView
        var cancleBtn = itemView.findViewById(R.id.btn_item_frag_hip_det_c_acticle) as ImageView
        var title = itemView.findViewById(R.id.tv_item_frag_hip_det_c_acticle_title) as TextView
        var description = itemView.findViewById(R.id.tv_item_frag_hip_det_c_acticle_description) as TextView
    }
}