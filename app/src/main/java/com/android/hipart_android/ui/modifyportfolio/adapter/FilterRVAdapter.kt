package com.android.hipart_android.ui.modifyportfolio.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.ui.modifyportfolio.data.FilterData


/**
 * Created by TakHyeongMin on 2019-07-06.
 */
class FilterRVAdapter(val ctx: Context, var arrayList: ArrayList<FilterData>) :
    RecyclerView.Adapter<FilterRVAdapter.Holder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_modify_port_folio_act_filter, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(p0: Holder, p1: Int) {

        p0.filterName.text = arrayList[p1].filterName

        if (arrayList[p1].filterFlag == true) {
            p0.rootLayout.setBackgroundResource(R.drawable.box_modify_portfolio_act)
            p0.filterName.setTextColor(Color.parseColor("#FFFFFF"))
        }


    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        var rootLayout = itemView.findViewById(R.id.root_item_modify_port_folio_act) as RelativeLayout
        var filterName = itemView.findViewById(R.id.tv_item_modify_port_folio_act_filter_name) as TextView
    }

    fun changeDataList(changeArrayList: ArrayList<FilterData>){
        arrayList = changeArrayList
    }
}