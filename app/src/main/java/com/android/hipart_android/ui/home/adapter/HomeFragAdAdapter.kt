package com.android.hipart_android.ui.home.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.android.hipart_android.BuildConfig
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.ui.ad_add.AddAdActivity
import com.android.hipart_android.ui.home.data.HomeFragAdData
import com.android.hipart_android.ui.main.MainActivity
import com.android.hipart_android.ui.main.data.PutClickBannerRequest
import com.android.hipart_android.ui.modifyportfolio.put.PutModifyPortFolioResponse
import com.android.hipart_android.util.SharedPreferenceController
import com.bumptech.glide.Glide
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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


        when (position) {
            0 -> {
                Glide.with(context!!).load(R.drawable.main_ad_one_img).into(img)
            }
            1 -> {
                Glide.with(context!!).load(R.drawable.main_ad_fiv_img).into(img)
            }
            2 -> {
                Glide.with(context!!).load(R.drawable.main_ad_fo_img).into(img)
            }
            3 -> {
                Glide.with(context!!).load(R.drawable.main_ad_thr_img).into(img)
            }
            4 -> {
                Glide.with(context!!).load(R.drawable.main_ad_two_img).into(img)
            }
        }

        if (dataList[position].clickFlag == true) {
            img.setOnClickListener {
                putClickBanner(position)
                showAnimFlag = true
            }
        } else {
            if (SharedPreferenceController.getNickName(context!!) == BuildConfig.TEST_USER_NCIKNAME) {
            }else{
                img.setOnClickListener {
                    context!!.startActivity<AddAdActivity>()
                }
            }
        }

        container.addView(view, 0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    fun putClickBanner(bannerIdx: Int) {
        val networkService = ApplicationController.instance.networkService
        val putClickBanner = networkService.putClickBanner(
            "application/json",
            SharedPreferenceController.getAuthorization(context!!),
            PutClickBannerRequest(bannerIdx)
        )

        putClickBanner.enqueue(object : Callback<PutModifyPortFolioResponse> {
            override fun onFailure(call: Call<PutModifyPortFolioResponse>, t: Throwable) {
                Log.e("Main Act Err", Log.getStackTraceString(t))
            }

            override fun onResponse(
                call: Call<PutModifyPortFolioResponse>,
                response: Response<PutModifyPortFolioResponse>
            ) {
                Log.v("현재 잘나옴?", "현재잘나옴")
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.message
                        ?.let {
                        (context as MainActivity).setAddPickAnimPickIcon()
                    }
            }
        })
    }
}