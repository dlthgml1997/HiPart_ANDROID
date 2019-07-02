package com.android.hipart_android.ui.hipat.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.ui.hipat.data.HipatFragAdData

class HipatFragAdRecyclerViewAdapter(private val context: Context, private val dataList: ArrayList<HipatFragAdData>) :
    PagerAdapter() {


    override fun isViewFromObject(view: View, obj: Any): Boolean = view.equals(obj)

    override fun getCount(): Int = 4

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_hipat_frag_advertisement, container, false)

        val image = view.findViewById(R.id.img_rv_item_hipat_frag_ad) as ImageView
        val title = view.findViewById(R.id.txt_rv_item_hipat_frag_ad_title) as TextView
        val context = view.findViewById(R.id.txt_rv_item_hipat_frag_ad_more) as TextView

        image.setImageResource(R.drawable.hipat_ad_1_img)

        container.addView(view, 0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj : Any) {
        container.removeView(obj as View)
    }
}