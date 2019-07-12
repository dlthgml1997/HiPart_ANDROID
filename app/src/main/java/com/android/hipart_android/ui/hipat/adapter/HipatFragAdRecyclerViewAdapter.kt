package com.android.hipart_android.ui.hipat.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.ui.ad_add.AddAdActivity
import com.android.hipart_android.ui.hipat.data.HipatFragAdData
import com.android.hipart_android.ui.main.MainActivity
import com.android.hipart_android.ui.main.data.PutClickBannerRequest
import com.android.hipart_android.ui.modifyportfolio.put.PutModifyPortFolioResponse
import com.android.hipart_android.util.SharedPreferenceController
import com.bumptech.glide.Glide
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                putClickBanner(position)
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

    fun putClickBanner(bannerIdx : Int){
        val networkService = ApplicationController.instance.networkService
        val putClickBanner = networkService.putClickBanner("application/json", SharedPreferenceController.getAuthorization(context!!), PutClickBannerRequest(bannerIdx))

        putClickBanner.enqueue(object: Callback<PutModifyPortFolioResponse> {
            override fun onFailure(call: Call<PutModifyPortFolioResponse>, t: Throwable) {
                Log.e("Main Act Err", Log.getStackTraceString(t))
            }

            override fun onResponse(
                call: Call<PutModifyPortFolioResponse>,
                response: Response<PutModifyPortFolioResponse>
            ) {
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.message
                    ?.let {
                        if(it == "배너 포인트 획득"){
                            (context as MainActivity).setAddPickAnimPickIcon()
                        }
                    }
            }
        })
    }
}