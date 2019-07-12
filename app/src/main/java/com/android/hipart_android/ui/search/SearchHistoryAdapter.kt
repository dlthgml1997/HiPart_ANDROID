package com.android.hipart_android.ui.search

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.util.SharedPreferenceController
import org.jetbrains.anko.layoutInflater

class SearchHistoryAdapter(val dataList: ArrayList<String>) :
    RecyclerView.Adapter<SearchHistoryAdapter.Holder>() {

    val TAG = "SearchHistoryAdapter"

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val ctx = p0.context

        val view = ctx.layoutInflater.inflate(R.layout.item_search_act_history, p0, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val ctx = holder.itemView.context

        holder.item.setOnClickListener {
            val editSearch: EditText = (ctx as SearchActivity).findViewById(R.id.et_search_act_search)
            editSearch.setText(holder.searchText.text.toString())
            ctx.getSearchResponse(SharedPreferenceController.getAuthorization(ctx))
            ctx.setViewPagerVisibility()
        }

        holder.searchText.text = dataList[position]

        holder.delete.setOnClickListener {
            dataList.removeAt(position)
            notifyItemRemoved(position)

            Log.d(TAG, SharedPreferenceController.getSearchHistory(ctx).size.toString())
            SharedPreferenceController.removeSearchHistory(ctx, position)

            (ctx as SearchActivity).setRecentSearchList()
        }
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val item = itemView.findViewById<RelativeLayout>(R.id.rl_item_search_act_history)
        val searchText = itemView.findViewById<TextView>(R.id.tv_item_sear_act_history_text)
        val delete = itemView.findViewById<ImageView>(R.id.tv_item_sear_act_history_delete)
    }
}
