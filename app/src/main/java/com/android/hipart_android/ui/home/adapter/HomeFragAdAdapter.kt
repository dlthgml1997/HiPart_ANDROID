package com.android.hipart_android.ui.home.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.android.hipart_android.R
import com.android.hipart_android.ui.ad_add.AddAdCheckActivity
import com.android.hipart_android.ui.home.data.HomeFragAdData
import com.android.hipart_android.ui.main.MainActivity
import com.bumptech.glide.Glide
import org.jetbrains.anko.startActivity


/**
 * Created by TakHyeongMin on 2019-07-03.
 */
class HomeFragAdAdapter(private val dataList: List<HomeFragAdData>, private val context: Context?) : PagerAdapter() {

    var showAnimFlag = false

    override fun getCount(): Int = dataList.size

    // Determines whether a page View is associated with a specific key object as returned by instantiateItem(ViewGroup, int).
    override fun isViewFromObject(view: View, obj: Any): Boolean = view.equals(obj)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(context).inflate(R.layout.vp_item_home_ad, container, false)

        val img = view.findViewById<ImageView>(R.id.iv_tv_item_home_ad)

//        val title = view.findViewById<TextView>(R.id.tv_rv_item_home_ad_title)
//
//        val des = view.findViewById<TextView>(R.id.tv_rv_item_home_ad_des)
//
//        val des2 = view.findViewById<TextView>(R.id.tv_rv_item_home_ad_des2)

        when(dataList[position].clickFlag){
            true -> {
                Glide.with(context!!).load(R.drawable.main_ad_2_img).into(img)
            }
            false -> {
                Glide.with(context!!).load(R.drawable.main_ad_1_img).into(img)
            }
        }

        /*when(position){
            0 -> {
                title.visibility = View.GONE
                des.visibility = View.GONE
            }
            1 -> {
                title.visibility = View.VISIBLE
                title.text = "[망원] 하이팟 아지트"
                des.text = "삼겹살로 서울을 제패한"
                des2.text = "하이팟 아지트에 초대합니다 !"
                des.visibility = View.VISIBLE
            }
        }*/

            if(dataList[position].clickFlag == true){
                img.setOnClickListener {
                    (context as MainActivity).setAddPickAnimPickIcon()
                    showAnimFlag = true
                }
            }else{
                img.setOnClickListener {
                    context.startActivity<AddAdCheckActivity>()
                }
            }

        container.addView(view, 0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}