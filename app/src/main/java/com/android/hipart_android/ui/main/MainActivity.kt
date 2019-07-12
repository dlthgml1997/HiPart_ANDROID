package com.android.hipart_android.ui.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.network.NetworkService
import com.android.hipart_android.ui.hipat.HiPatFragment
import com.android.hipart_android.ui.hipat.data.GetProfileLookUpResponse
import com.android.hipart_android.ui.hipat.fragment.*
import com.android.hipart_android.ui.home.HomeFragment
import com.android.hipart_android.ui.mypage.fragment.MyPageFragment
import com.android.hipart_android.ui.mypick.data.GetMyPickData
import com.android.hipart_android.ui.portfolio.PortFolioFragment
import com.android.hipart_android.util.BaseActivity
import com.android.hipart_android.util.FragmentKind
import com.android.hipart_android.util.OnSingleClickListener
import com.android.hipart_android.util.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_hipat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 *
 * @author 탁형민
 * @since 2019-07-02 05:46
 **/

class MainActivity : BaseActivity() {

    var profileAllDataList = ArrayList<GetMyPickData>()
    var noFilterProfileAllDataList = ArrayList<GetMyPickData>()

    private var FILTER_ACTIVITY_RESULT = 9
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        attachHomeFragment()
        setOnClickListener()
        Log.e("토큰", SharedPreferenceController.getAuthorization(this@MainActivity))
    }

    override fun onBackPressed() {
        SharedPreferenceController.clearSPC(this)
        super.onBackPressed()

    }


    private fun attachHomeFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_layout_main_act, HomeFragment())
            .commit()
    }

    fun replaceFragment(frameLayoutId: Int, fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(frameLayoutId, fragment)
            .commit()
    }

    private fun addFragment(frameLayoutId: Int, fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(frameLayoutId, fragment)
            .commit()
    }

    fun replaceFragmentFromHome(frameLayoutId: Int, fragment: Fragment, position: Int) {
        val bundle = Bundle()
        bundle.putInt("flag", position)
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(frameLayoutId, fragment)
            .commit()
    }

    private fun setOnClickListener() {
        btn_home.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {
                replaceFragment(R.id.frame_layout_main_act, HomeFragment())
                setBottomIconChanger(FragmentKind.Home)
            }
        })
        btn_hi_part.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {
                replaceFragmentFromHome(R.id.frame_layout_main_act, HiPatFragment(), 0)
                setBottomIconChanger(FragmentKind.Hipat)
                getAllProfileLookUp()
            }
        })
        btn_portfolio.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {
                if (getVisibleFragment() !is PortFolioFragment) {
                    addFragment(R.id.frame_layout_main_act, PortFolioFragment())
                    setBottomIconChanger(FragmentKind.PortFolio)
                }
            }
        }
        )
        btn_mypage.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {
                replaceFragment(R.id.frame_layout_main_act, MyPageFragment())
                setBottomIconChanger(FragmentKind.Mypage)

            }
        })
    }

    private fun setBottomIconChanger(fragKind: FragmentKind) {

        when (fragKind) {
            FragmentKind.Home -> {
                iv_home.setImageResource(R.drawable.main_tb_home_on_icon)
                iv_hi_part.isSelected = false
                iv_portfolio.isSelected = false
                iv_mypage.isSelected = false

                setAllTextToGray()
                tv_home.setTextColor(Color.parseColor("#7947fd"))

            }
            FragmentKind.Hipat -> {
                iv_home.setImageResource(R.drawable.main_tb_home_off_icon)
                iv_hi_part.isSelected = true
                iv_portfolio.isSelected = false
                iv_mypage.isSelected = false

                setAllTextToGray()
                tv_hipart.setTextColor(Color.parseColor("#7947fd"))
            }
            FragmentKind.PortFolio -> {
                iv_home.setImageResource(R.drawable.main_tb_home_off_icon)
                iv_hi_part.isSelected = false
                iv_portfolio.isSelected = true
                iv_mypage.isSelected = false
                setAllTextToGray()
                tv_portfolio.setTextColor(Color.parseColor("#7947fd"))
            }
            FragmentKind.Mypage -> {
                iv_home.setImageResource(R.drawable.main_tb_home_off_icon)
                iv_hi_part.isSelected = false
                iv_portfolio.isSelected = false
                iv_mypage.isSelected = true
                setAllTextToGray()
                tv_mypage.setTextColor(Color.parseColor("#7947fd"))
            }
        }

    }

    private fun setBottomSheetDialog() {
        val view = layoutInflater.inflate(R.layout.fragment_portfolio_bottom_sheet_dialog, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()
    }

    private fun getVisibleFragment(): Fragment? =
        supportFragmentManager.fragments.findLast { it.isVisible }

    // 가려져 있는 프래그먼트 가져오기
    private fun getSecondFragment(): Fragment? =
        supportFragmentManager.fragments.find { it.isVisible }

    private fun setAllTextToGray() {
        tv_home.setTextColor(Color.parseColor("#838383"))
        tv_hipart.setTextColor(Color.parseColor("#838383"))
        tv_portfolio.setTextColor(Color.parseColor("#838383"))
        tv_mypage.setTextColor(Color.parseColor("#838383"))
    }

    fun removeFragment() {
        supportFragmentManager
            .beginTransaction()
            .remove(getVisibleFragment()!!)
            .commit()
    }

    fun setAnimPickIcon() {
        val interpolator = MyBounceInterpolator(0.2, 20.0)
        val anim: Animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.expand_anim)
        anim.interpolator = interpolator

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                rl_main_act_anim.visibility = View.GONE
            }

            override fun onAnimationStart(animation: Animation?) {
                rl_main_act_anim.visibility = View.VISIBLE
            }
        })
        rl_main_act_anim.startAnimation(anim)
    }

    internal inner class MyBounceInterpolator(amplitude: Double, frequency: Double) :
        android.view.animation.Interpolator {
        private var mAmplitude = 1.0
        private var mFrequency = 10.0

        init {
            mAmplitude = amplitude
            mFrequency = frequency
        }

        override fun getInterpolation(time: Float): Float {
            return (-1.0 * Math.pow(Math.E, -time / mAmplitude) *
                    Math.cos(mFrequency * time) + 1).toFloat()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILTER_ACTIVITY_RESULT) {
            Log.v("이놈!", "잘들어오는중")
            val filterFlag: Int = data!!.getIntExtra("filterFlag", 100)

            if (filterFlag == 100) {
                // 아무일도,, 아무것도,,
            } else {

                profileAllDataList = noFilterProfileAllDataList
                setFilterData(filterFlag)

                setIndividualPartAdapterData()


//                val hipatFragment = supportFragmentManager.findFragmentById(R.id.frame_layout_main_act) as HiPatFragment
//
//                setFilterData(filterFlag)
////            Log.e(object{}::class.java.enclosingMethod.name,frag)
//
//                (0..4).forEach {
//
//                    val frag =
//                        hipatFragment.vp_hipat_frag_nav.adapter?.instantiateItem(hipatFragment.vp_hipat_frag_nav, it)
//
//
////                    when (frag) {
////                        is AllHipatFragment -> frag.setAdapterData(filterFlag)
////                        is CPatHiPatFragment -> frag.setAdapterData(filterFlag)
////                        is EPatHiPatFragment -> frag.setFilterData(filterFlag)
////                        is TPatHiPatFragment -> frag.setFilterData(filterFlag)
////                        is EtcHiPatFragment -> frag.setFilterData(filterFlag)
////                    }
//
//                }
            }
        }
    }

// TODO: 아무것도안돼있으면 ffilterflag 를 0 으로
    private fun getAllProfileLookUp() {
        var networkService: NetworkService = ApplicationController.instance.networkService

        val getProfileLookUp = networkService.getProfileLookUp(
            SharedPreferenceController.getAuthorization(this@MainActivity),
            0
        )

        getProfileLookUp.enqueue(object : Callback<GetProfileLookUpResponse> {
            override fun onFailure(call: Call<GetProfileLookUpResponse>, t: Throwable) {
                Log.e("All HighPat Frag Err", Log.getStackTraceString(t))
            }

            override fun onResponse(
                call: Call<GetProfileLookUpResponse>,
                response: Response<GetProfileLookUpResponse>
            ) {
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.takeIf { it.message == "조회 성공" }
                    ?.data
                    ?.let {
                        noFilterProfileAllDataList = it
                        profileAllDataList = it
                        setIndividualPartAdapterData()
                    }
            }
        })
    }

    fun setFilterData(flag: Int) {
        when (flag) {
            0 -> {
                // 초기화 로직
                profileAllDataList = noFilterProfileAllDataList
            }
            1, 2, 3, 4, 5, 6, 7 -> {
                profileAllDataList = ArrayList(profileAllDataList.filter { it.info[0].concept == flag })
            }
            8, 9 -> {
                profileAllDataList = ArrayList(profileAllDataList.filter { it.info[0].pd == flag - 7 })
            }
            10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 -> {
                profileAllDataList = ArrayList(profileAllDataList.filter { it.info[0].lang == flag - 9 })
            }
            21, 22, 23, 24, 25, 26 -> {
                profileAllDataList = ArrayList(profileAllDataList.filter { it.info[0].etc == flag - 20 })
            }
        }
    }

    fun setIndividualPartAdapterData() {
        val hipatFragment = supportFragmentManager.findFragmentById(R.id.frame_layout_main_act) as HiPatFragment

        (0..4).forEach {

            val frag =
                hipatFragment.vp_hipat_frag_nav.adapter?.instantiateItem(hipatFragment.vp_hipat_frag_nav, it)

            when (frag) {
                is AllHipatFragment -> frag.setAdapterData(profileAllDataList)
                is CPatHiPatFragment -> frag.setAdapterData(profileAllDataList.filter { it.info[0].user_type == 1 })
                is EPatHiPatFragment -> frag.setAdapterData(profileAllDataList.filter { it.info[0].user_type == 2 })
                is TPatHiPatFragment -> frag.setAdapterData(profileAllDataList.filter { it.info[0].user_type == 3 })
                is EtcHiPatFragment -> frag.setAdapterData(profileAllDataList.filter { it.info[0].user_type == 4 })
            }
        }

    }

}
