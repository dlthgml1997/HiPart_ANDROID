package com.android.hipart_android.ui.home.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.ui.home.data.HomeFragAdData


/**
 * Created by TakHyeongMin on 2019-07-03.
 */
class HomeFragAdAdapter(private val dataList: List<HomeFragAdData>, private val context: Context?) : PagerAdapter() {

    override fun getCount(): Int = dataList.size

    // Determines whether a page View is associated with a specific key object as returned by instantiateItem(ViewGroup, int).
    override fun isViewFromObject(view: View, obj: Any): Boolean = view.equals(obj)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(context).inflate(R.layout.vp_item_home_ad, container, false)

        val img = view.findViewById<ImageView>(R.id.iv_tv_item_home_ad)

        val title = view.findViewById<TextView>(R.id.tv_rv_item_home_ad_title)

        val des = view.findViewById<TextView>(R.id.tv_rv_item_home_ad_des)

        container.addView(view, 0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}