package com.android.hipart_android.ui.home.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.ui.home.data.HomeFragHipatData


/**
 * Created by TakHyeongMin on 2019-07-03.
 */
class HomeFragHipatAdapter(private val dataList: List<HomeFragHipatData>, private val context: Context?, private val picAnimListener : View.OnClickListener ) : PagerAdapter() {

    override fun getCount(): Int = dataList.size


    override fun isViewFromObject(view: View, obj: Any): Boolean = view.equals(obj)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context)!!.inflate(R.layout.vp_item_home_hipat, container, false)


        val userImg = view.findViewById<ImageView>(R.id.iv_rv_home_hipat_user)

        val userName = view.findViewById<TextView>(R.id.tv_rv_home_hipat_user)

        val job = view.findViewById<TextView>(R.id.tv_rv_home_hipat_job)

        val firstTheme = view.findViewById<TextView>(R.id.tv_rv_home_hipat_theme_first)

        val secondTheme = view.findViewById<TextView>(R.id.tv_rv_home_hipat_theme_second)

        val thirdTheme= view.findViewById<TextView>(R.id.tv_rv_home_hipat_theme_third)

        val des = view.findViewById<TextView>(R.id.tv_rv_home_hipat_description)

        val pickNum = view.findViewById<TextView>(R.id.tv_rv_home_hipat_pick_num)

        val btnPick = view.findViewById<LinearLayout>(R.id.btn_rv_home_hipat_pick)

        container.addView(view, 0)

        btnPick.setOnClickListener (picAnimListener)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

}