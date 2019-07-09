package com.android.hipart_android.ui.modifyportfolio.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.ui.modifyportfolio.get.GetModifyPortFolioDataTpat


/**
 * Created by TakHyeongMin on 2019-07-06.
 */

class TransWorkRVAdapter(val ctx: Context, var dataList: GetModifyPortFolioDataTpat) :
    RecyclerView.Adapter<TransWorkRVAdapter.Holder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_modify_port_folio_act_translater, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.after.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.beforeTransText.text = dataList.before[position]
        holder.afterTransText.text = dataList.after[position]

        holder.cancleBtn.setOnClickListener {
            //dataList.removeAt(position)
            notifyDataSetChanged()
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val beforeTransText = itemView.findViewById<TextView>(R.id.tv_item_modify_portfolio_act_translater_beforetrans)
        val afterTransText = itemView.findViewById<TextView>(R.id.tv_item_modify_portfolio_act_translater_aftertrans)
        val cancleBtn = itemView.findViewById<ImageView>(R.id.btn_item_modify_portfolio_act_translater_cancel)

    }
}