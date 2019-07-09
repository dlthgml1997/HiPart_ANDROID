package com.android.hipart_android.ui.notification

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.ui.notification.get.MyNotificationData

class NotificationOverviewRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<MyNotificationData>):
    RecyclerView.Adapter<NotificationOverviewRecyclerViewAdapter.Holder>(){
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
            val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_notification_overview, viewGroup, false)
            return Holder(view)
        }

        override fun getItemCount(): Int = dataList.size

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.noti_message.text =  dataList[position].content + getMessage(dataList[position].type)
            holder.createdAt.text = dataList[position].createdAt
        }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val noti_message= itemView.findViewById(R.id.txt_rv_item_notification_message) as TextView
        val createdAt= itemView.findViewById(R.id.txt_rv_item_notification_date) as TextView
    }

    private fun getMessage(p0: Int): String {
        return when (p0) {
            1 -> "님이 픽했습니다."
            2 -> "님이 하이파이브했습니다."
            3 -> "팜을 얻었습니다."
            else -> ""
        }
    }
}