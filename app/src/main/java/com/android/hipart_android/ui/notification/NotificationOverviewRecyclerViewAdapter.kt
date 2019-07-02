package com.android.hipart_android.ui.notification

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.hipart_android.R

class NotificationOverviewRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<NotificationOverviewData>): RecyclerView.Adapter<NotificationOverviewRecyclerViewAdapter.Holder>(){
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
            val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_notification_overview, viewGroup, false)
            return Holder(view)
        }

        override fun getItemCount(): Int = dataList.size

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.noti_message.text = dataList[position].noti_message
            holder.noti_date.text = dataList[position].noti_date
        }
    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val noti_more = itemView.findViewById(R.id.btn_item_notification_more) as ImageView
        val noti_message= itemView.findViewById(R.id.txt_rv_item_notification_message) as TextView
        val noti_date= itemView.findViewById(R.id.txt_rv_item_notification_date) as TextView
    }
}