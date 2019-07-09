package com.android.hipart_android.ui.home.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.ui.hipart.HipartDetailActivity
import com.android.hipart_android.ui.home.data.HomeFragHipatData
import com.android.hipart_android.ui.home.data.post.PickDTO
import com.android.hipart_android.ui.home.data.post.PickResponse
import com.android.hipart_android.ui.main.MainActivity
import com.android.hipart_android.util.SharedPreferenceController
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by TakHyeongMin on 2019-07-03.
 */
class HomeFragHipatAdapter(private val dataList: List<HomeFragHipatData>, private val context: Context?) :
    PagerAdapter() {

    override fun getCount(): Int = dataList.size


    override fun isViewFromObject(view: View, obj: Any): Boolean = view.equals(obj)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context)!!.inflate(R.layout.vp_item_home_hipat, container, false)

        val userImg = view.findViewById<ImageView>(R.id.iv_rv_home_hipat_user)

        val userName = view.findViewById<TextView>(R.id.tv_rv_home_hipat_user)

        val job = view.findViewById<TextView>(R.id.tv_rv_home_hipat_job)

        val firstTheme = view.findViewById<TextView>(R.id.tv_rv_home_hipat_theme_first)

        val secondTheme = view.findViewById<TextView>(R.id.tv_rv_home_hipat_theme_second)

        val thirdTheme = view.findViewById<TextView>(R.id.tv_rv_home_hipat_theme_third)

        val des = view.findViewById<TextView>(R.id.tv_rv_home_hipat_description)

        val pickNum = view.findViewById<TextView>(R.id.tv_rv_home_hipat_pick_num)

        val btnPick = view.findViewById<LinearLayout>(R.id.btn_rv_home_hipat_pick)

        val root = view.findViewById<RelativeLayout>(R.id.root_hipat_frag)

        container.addView(view, 0)

        btnPick.setOnClickListener {
            addPick("taktak")
        }

        root.setOnClickListener {
            deletePick("taktak")
            context!!.startActivity<HipartDetailActivity>()
        }

        return view
    }


    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    private fun addPick(nickName: String) {

        Log.v("요놈", SharedPreferenceController.getAuthorization(context!!))
        val networkService = ApplicationController.instance.networkService
        val addPick = networkService.addPick(SharedPreferenceController.getAuthorization(context!!), PickDTO(nickName))
        addPick.enqueue(object : Callback<PickResponse> {
            override fun onFailure(call: Call<PickResponse>, t: Throwable) {
                Log.e("Add Pick Error", Log.getStackTraceString(t))
            }

            override fun onResponse(call: Call<PickResponse>, response: Response<PickResponse>) {
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        when(it?.message ?: " "){
                            "픽 성공" -> {
                                Log.v("태그", it.message)
                                (context as MainActivity).setAnimPickIcon()
                            }
                            " " -> {
                                Log.v("태그", "message가 널인데 ?")
                            }
                            "닉네임을 가진 유저가 없습니다." -> {
                                Log.v("태그", "닉네임을 가진 유저가 없다.")
                            }
                            // TODO : 픽리스트 없음
                            else ->  {
                                Log.v("태그", it.message)
                            }
                        }
                    }
            }
        })
    }

    private fun deletePick(nickName: String) {

        val networkService = ApplicationController.instance.networkService
        val deletePick = networkService.deletePick(SharedPreferenceController.getAuthorization(context!!), PickDTO(nickName))
        deletePick.enqueue(object : Callback<PickResponse> {
            override fun onFailure(call: Call<PickResponse>, t: Throwable) {
                Log.e("Delete Pick Error", Log.getStackTraceString(t))
            }

            override fun onResponse(call: Call<PickResponse>, response: Response<PickResponse>) {
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        when(it?.message ?: " "){

                            "픽 취소 성공" -> {
                                Log.v("태그", it.message)
                            }
                            " " -> {
                                Log.v("태그", "message가 널인데 ?")
                            }
                            "닉네임을 가진 유저가 없습니다." -> {
                                Log.v("태그", "닉네임을 가진 유저가 없다.")
                            }
                            // TODO : 픽리스트 없음
                            else ->  {
                                Log.v("태그", it.message)
                            }
                        }
                    }
            }
        })
    }
}