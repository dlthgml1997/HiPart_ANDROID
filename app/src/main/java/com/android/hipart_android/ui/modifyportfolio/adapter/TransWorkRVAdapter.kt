package com.android.hipart_android.ui.modifyportfolio.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.ui.modifyportfolio.data.TransWorkData


/**
 * Created by TakHyeongMin on 2019-07-06.
 */

class TransWorkRVAdapter(val ctx: Context, var dataList : ArrayList<TransWorkData>) : RecyclerView.Adapter<TransWorkRVAdapter.Holder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_modify_port_folio_act_translater, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        p0.beforeTransText.text = dataList[p1].beforeTransText
        p0.afterTransText.text = dataList[p1].afterTransText

        p0.cancleBtn.setOnClickListener {
            dataList.removeAt(p1)
            notifyDataSetChanged()
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val beforeTransText = itemView.findViewById<TextView>(R.id.tv_item_modify_portfolio_act_translater_beforetrans)
        val afterTransText = itemView.findViewById<TextView>(R.id.tv_item_modify_portfolio_act_translater_aftertrans)
        val cancleBtn = itemView.findViewById<ImageView>(R.id.btn_item_modify_portfolio_act_translater_cancel)

    }
}