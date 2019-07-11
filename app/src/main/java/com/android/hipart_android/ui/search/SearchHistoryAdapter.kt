package com.android.hipart_android.ui.search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.util.SharedPreferenceController
import kotlinx.android.synthetic.main.item_search_act_history.view.*
import org.jetbrains.anko.layoutInflater

class SearchHistoryAdapter(val ctx: Context, val dataList: ArrayList<String>) :
    RecyclerView.Adapter<SearchHistoryAdapter.Holder>() {

    val TAG = "SearchHistoryAdapter"

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = ctx.layoutInflater.inflate(R.layout.item_search_act_history, p0, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.text.text = dataList[position]

        holder.delete.setOnClickListener {
            dataList.removeAt(position)
            notifyItemRemoved(position)

            SharedPreferenceController.searchHistoryList.removeAt(position)
            Log.d(TAG, SharedPreferenceController.searchHistoryList.size.toString())


            (ctx as SearchActivity).setRecentSearchList()
        }
    }



    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.findViewById<TextView>(R.id.tv_item_sear_act_history_text)
        val delete = itemView.findViewById<ImageView>(R.id.tv_item_sear_act_history_delete)
    }
}
