package com.android.hipart_android.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.android.hipart_android.R
import com.android.hipart_android.ui.home.adapter.HomeFragAdAdapter
import com.android.hipart_android.ui.home.adapter.HomeFragHipatAdapter
import com.android.hipart_android.ui.home.data.HomeFragAdData
import com.android.hipart_android.ui.home.data.HomeFragHipatData
import com.android.hipart_android.ui.notification.NotificationActivity
import com.android.hipart_android.ui.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.startActivity

class HomeFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v) {

            btn_frag_home_alarm -> {
                startActivity<NotificationActivity>()
            }

            btn_frag_home_search -> {
                startActivity<SearchActivity>()
            }

            btn_frag_home_c_pat -> {

                // (context as MainActivity).replaceFragment(R.id.frame_layout_main_act, HiPatFragment())

            }

            btn_frag_home_e_pat -> {

            }

            btn_frag_home_t_pat -> {

            }

            btn_frag_home_etc -> {

            }
        }
    }

    private val hipartDataList by lazy {
        ArrayList<HomeFragHipatData>()
    }

    private val homeFragHipatAdapter by lazy {
        HomeFragHipatAdapter(hipartDataList, this@HomeFragment.context, picAnimListener)
    }

    private val adDataList by lazy {
        ArrayList<HomeFragAdData>()
    }

    private val homeFragAdAdapter by lazy {
        HomeFragAdAdapter(adDataList, this@HomeFragment.context)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setVpAdapter()
        setOnClickListener()
    }

    private fun setVpAdapter() {
        setPatVpAdapter()
        setAdVpAdapter()

    }

    private fun setPatVpAdapter() {
        hipartDataList.add(
            HomeFragHipatData(
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa"
            )
        )

        hipartDataList.add(
            HomeFragHipatData(
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa"
            )
        )

        hipartDataList.add(
            HomeFragHipatData(
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa"
            )
        )

        hipartDataList.add(
            HomeFragHipatData(
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa"
            )
        )

        hipartDataList.add(
            HomeFragHipatData(
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa"
            )
        )

        hipartDataList.add(
            HomeFragHipatData(
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa",
                "aa"
            )
        )

        vp_frag_home_recommend_hipat.adapter = homeFragHipatAdapter
    }

    private fun setAdVpAdapter() {
        adDataList.add(
            HomeFragAdData(
                "",
                "",
                ""
            )
        )

        adDataList.add(
            HomeFragAdData(
                "",
                "",
                ""
            )
        )

        adDataList.add(
            HomeFragAdData(
                "",
                "",
                ""
            )
        )

        adDataList.add(
            HomeFragAdData(
                "",
                "",
                ""
            )
        )

        adDataList.add(
            HomeFragAdData(
                "",
                "",
                ""
            )
        )

        adDataList.add(
            HomeFragAdData(
                "",
                "",
                ""
            )
        )

        rv_frag_home_advertising.adapter = homeFragAdAdapter
    }

    fun setAnimPickIcon() {
        val interpolator = MyBounceInterpolator(0.2, 20.0)
        val anim: Animation = AnimationUtils.loadAnimation(this@HomeFragment.context, R.anim.expand_anim)
        anim.interpolator = interpolator

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                rv_main_act_pick.visibility = View.GONE
            }

            override fun onAnimationStart(animation: Animation?) {
                rv_main_act_pick.visibility = View.VISIBLE
            }
        })
        rv_main_act_pick.startAnimation(anim)
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

    private val picAnimListener = View.OnClickListener { setAnimPickIcon() }

    private fun setOnClickListener() {
        btn_frag_home_alarm.setOnClickListener(this)
        btn_frag_home_search.setOnClickListener(this)
        btn_frag_home_c_pat.setOnClickListener(this)
        btn_frag_home_e_pat.setOnClickListener(this)
        btn_frag_home_t_pat.setOnClickListener(this)
        btn_frag_home_etc.setOnClickListener(this)
    }
}