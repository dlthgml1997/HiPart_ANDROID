package com.android.hipart_android.ui.hipart

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.url.UrlActivity
import com.bumptech.glide.Glide
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.support.v4.startActivity

class HipartDetailCpatArticleAdapter(val ctx: Context, val dataList: ArrayList<HipartDetailArticleData>) :
    RecyclerView.Adapter<HipartDetailCpatArticleAdapter.Holder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = ctx.layoutInflater.inflate(R.layout.item_frag_hip_det_c_article, p0, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val url = dataList[position].url

        Glide.with(ctx)
            .load(dataList[position].thumbnail)
            .into(holder.photo)
        holder.title.text = dataList[position].title
        holder.photo.clipToOutline = true
        holder.content.text = dataList[position].content
        holder.photo.setOnClickListener {
            if (url!!.isNotEmpty()) {
                val intent = Intent(ctx, UrlActivity::class.java).apply {
                    putExtra("url", url)
                }
                startActivity(ctx, intent, null)
            }
        }

    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo = itemView.findViewById(R.id.iv_item_frag_hip_det_c_acticle) as ImageView
        val title = itemView.findViewById(R.id.tv_item_frag_hip_det_c_acticle_title) as TextView
        val content = itemView.findViewById(R.id.tv_item_frag_hip_det_c_acticle_description) as TextView
    }
}