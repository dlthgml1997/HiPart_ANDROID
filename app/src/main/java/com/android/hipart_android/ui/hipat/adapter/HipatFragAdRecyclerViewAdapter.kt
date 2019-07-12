package com.android.hipart_android.ui.hipat.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.android.hipart_android.R
import com.android.hipart_android.ui.ad_add.AddAdActivity
import com.android.hipart_android.ui.hipat.data.HipatFragAdData
import com.android.hipart_android.ui.main.MainActivity
import com.bumptech.glide.Glide
import org.jetbrains.anko.startActivity

class HipatFragAdRecyclerViewAdapter(private val context: Context, private val dataList: ArrayList<HipatFragAdData>) :
    PagerAdapter() {

    var showAnimFlag = false


    override fun isViewFromObject(view: View, obj: Any): Boolean = view.equals(obj)

    override fun getCount(): Int = 4

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_hipat_frag_advertisement, container, false)

        val image = view.findViewById(R.id.img_rv_item_hipat_frag_ad) as ImageView

        when(position){
            0 -> {
                Glide.with(context).load(R.drawable.main_ad_mini_one_img).into(image)
            }
            1 -> {
                Glide.with(context).load(R.drawable.main_ad_mini_two_img).into(image)
            }
            2 -> {
                Glide.with(context).load(R.drawable.main_ad_mini_thr_img).into(image)
            }
            3 -> {
                Glide.with(context).load(R.drawable.main_ad_mini_fo_img).into(image)
            }
            4 -> {
                Glide.with(context).load(R.drawable.main_ad_mini_fiv_img).into(image)
            }
        }

        if(dataList[position].clickFlag == true){
            image.setOnClickListener {
                (context as MainActivity).setAddPickAnimPickIcon()
                showAnimFlag = true
            }
        }else{
            image.setOnClickListener {
                context!!.startActivity<AddAdActivity>()
            }
        }

        container.addView(view, 0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj : Any) {
        container.removeView(obj as View)
    }
}