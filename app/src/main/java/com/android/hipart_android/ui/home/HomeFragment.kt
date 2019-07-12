package com.android.hipart_android.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.android.hipart_android.BuildConfig
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.ui.hipat.HiPatFragment
import com.android.hipart_android.ui.home.adapter.HomeFragAdAdapter
import com.android.hipart_android.ui.home.adapter.HomeFragHipatAdapter
import com.android.hipart_android.ui.home.data.HomeFragAdData
import com.android.hipart_android.ui.home.data.get.GetCustomRecommendResponse
import com.android.hipart_android.ui.home.data.get.GetNotificationFlagResponse
import com.android.hipart_android.ui.home.data.get.ResData
import com.android.hipart_android.ui.login.data.get.GetMyInfoResponse
import com.android.hipart_android.ui.main.MainActivity
import com.android.hipart_android.ui.notification.NotificationActivity
import com.android.hipart_android.ui.search.SearchActivity
import com.android.hipart_android.util.FragmentKind
import com.android.hipart_android.util.OnSingleClickListener
import com.android.hipart_android.util.SharedPreferenceController
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(){

    private var customRecommedNetworkFlag = false
    private var getNotiNetworkFlag = false

    lateinit var hipartDataList : ArrayList<ResData>

    private val homeFragHipatAdapter by lazy {
        HomeFragHipatAdapter(hipartDataList, this@HomeFragment.context)
    }

    private val adDataList by lazy {
        ArrayList<HomeFragAdData>()
    }

    private val homeFragAdAdapter by lazy {
        HomeFragAdAdapter(adDataList, this@HomeFragment.context)
    }

    private val iv by lazy {
        view!!.findViewById<ImageView>(R.id.iv_frag_home_alarm)
    }

    lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        getMyInfo()
        viewPager = vp_frag_home_recommend_hipat
        super.onActivityCreated(savedInstanceState)
        hipartDataList = ArrayList<ResData>()
        // 하는 중
        if(customRecommedNetworkFlag == false){
            getCustomRecommend()
        }

        if(getNotiNetworkFlag == false)
            getNotificationFlag()


        setVpAdapter()
        setOnClickListener()
    }

    private fun setVpAdapter() {
        setAdVpAdapter()

    }

    private fun setPatVpAdapter() {
        viewPager.adapter = homeFragHipatAdapter
        customRecommedNetworkFlag = false
    }

    private fun setAdVpAdapter() {
        adDataList.add(
            HomeFragAdData(
                "",
                "",
                "",
                false
            )
        )

        adDataList.add(
            HomeFragAdData(
                "",
                "",
                "",
                true
            )
        )

        adDataList.add(
            HomeFragAdData(
                "",
                "",
                "",
                true
            )
        )

        adDataList.add(
            HomeFragAdData(
                "",
                "",
                "",
                true
            )
        )

        adDataList.add(
            HomeFragAdData(
                "",
                "",
                "",
                true
            )
        )

        rv_frag_home_advertising.adapter = homeFragAdAdapter
    }

    private fun setOnClickListener() {
        btn_frag_home_alarm.setOnClickListener(object : OnSingleClickListener(){
            override fun onSingleClick(v: View) {
                if(SharedPreferenceController.getNickName(this@HomeFragment.context!!) == BuildConfig.TEST_USER_NCIKNAME){
                    (context as MainActivity).showGoToLoginDialog()
                }else{
                    startActivity<NotificationActivity>()
                }
            }
        })
        btn_frag_home_search.setOnClickListener(object : OnSingleClickListener(){
            override fun onSingleClick(v: View) {
                startActivity<SearchActivity>()
            }
        })
        btn_frag_home_c_pat.setOnClickListener(object : OnSingleClickListener(){
            override fun onSingleClick(v: View) {
                (activity as MainActivity).replaceFragmentFromHome(R.id.frame_layout_main_act, HiPatFragment(), 1)
                (activity as MainActivity).setBottomIconChanger(FragmentKind.Hipat)
                Log.d("HomeFragment", "replaceFragmentFromHome")
            }
        })
        btn_frag_home_e_pat.setOnClickListener(object : OnSingleClickListener(){
            override fun onSingleClick(v: View) {
                (activity as MainActivity).replaceFragmentFromHome(R.id.frame_layout_main_act, HiPatFragment(), 2)
                (activity as MainActivity).setBottomIconChanger(FragmentKind.Hipat)
                Log.d("HomeFragment", "replaceFragmentFromHome")
            }
        })
        btn_frag_home_t_pat.setOnClickListener(object : OnSingleClickListener(){
            override fun onSingleClick(v: View) {
                (activity as MainActivity).replaceFragmentFromHome(R.id.frame_layout_main_act, HiPatFragment(), 3)
                (activity as MainActivity).setBottomIconChanger(FragmentKind.Hipat)
                Log.d("HomeFragment", "replaceFragmentFromHome")
            }
        })
        btn_frag_home_etc.setOnClickListener(object : OnSingleClickListener(){
            override fun onSingleClick(v: View) {
                (activity as MainActivity).replaceFragmentFromHome(R.id.frame_layout_main_act, HiPatFragment(), 4)
                (activity as MainActivity).setBottomIconChanger(FragmentKind.Hipat)
                Log.d("HomeFragment", "replaceFragmentFromHome")
            }
        })
    }

    private fun getCustomRecommend(){
        customRecommedNetworkFlag = true
        val networkService = ApplicationController.instance.networkService

        val getCustomRecommend = networkService.customRecommend(
            SharedPreferenceController.getAuthorization(this@HomeFragment.context!!)
        )

        getCustomRecommend.enqueue(object: Callback<GetCustomRecommendResponse>{
            override fun onFailure(call: Call<GetCustomRecommendResponse>, t: Throwable) {
                Log.e("Home Frag Err", Log.getStackTraceString(t))
                customRecommedNetworkFlag = false
            }

            override fun onResponse(
                call: Call<GetCustomRecommendResponse>,
                response: Response<GetCustomRecommendResponse>
            ) {

                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.data
                    ?.let{
                        hipartDataList = it.resData
                        setPatVpAdapter()
                    }
            }
        })
    }

    private fun getNotificationFlag(){
        getNotiNetworkFlag = true
        val networkService = ApplicationController.instance.networkService

        val getNotificationFlag = networkService.getNotificationFlag(
            SharedPreferenceController.getAuthorization(this@HomeFragment.context!!)
        )

        getNotificationFlag.enqueue(object: Callback<GetNotificationFlagResponse>{
            override fun onFailure(call: Call<GetNotificationFlagResponse>, t: Throwable) {
                Log.e("Home Frag Err", Log.getStackTraceString(t))
                getNotiNetworkFlag = false
            }

            override fun onResponse(
                call: Call<GetNotificationFlagResponse>,
                response: Response<GetNotificationFlagResponse>
            ) {
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.data
                    ?.let{
                        iv.isSelected = it == 1
                    }
                getNotiNetworkFlag = false
            }
        })
    }

    private fun getMyInfo(){
        val networkService = ApplicationController.instance.networkService
        val getMyInfo = networkService.getMyInfo(
            SharedPreferenceController.getAuthorization(this@HomeFragment.context!!)
        )

        getMyInfo.enqueue(object : Callback<GetMyInfoResponse>{
            override fun onFailure(call: Call<GetMyInfoResponse>, t: Throwable) {
                Log.e("Login Act Err", Log.getStackTraceString(t))
            }

            override fun onResponse(call: Call<GetMyInfoResponse>, response: Response<GetMyInfoResponse>) {
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.data
                    ?.let{
                        SharedPreferenceController.setNickName(this@HomeFragment.context!!, it[0].user_nickname)
                        if(it[0].user_nickname == BuildConfig.TEST_USER_NCIKNAME){
                            setNickName(true, "")
                        }else {
                            setNickName(false, it[0].user_nickname)
                        }

                    }
            }
        })
    }

    private fun setNickName(testLoginFlag : Boolean, nickname: String){
        if(testLoginFlag == true){
            tv_frag_home_user_name.text = "반가워요!"
            tv_frag_home_user_nim.visibility = View.GONE
        }else{
            tv_frag_home_user_name.text = nickname
            tv_frag_home_user_nim.visibility = View.VISIBLE
        }
    }
}