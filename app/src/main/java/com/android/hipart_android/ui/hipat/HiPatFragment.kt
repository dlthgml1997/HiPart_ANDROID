package com.android.hipart_android.ui.hipat

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.network.NetworkService
import com.android.hipart_android.ui.hipart_filter.HipatFilterActivity
import com.android.hipart_android.ui.hipat.adapter.HipatFragAdRecyclerViewAdapter
import com.android.hipart_android.ui.hipat.adapter.HipatFragPortViewPagerAdapter
import com.android.hipart_android.ui.hipat.data.GetProfileLookUpResponse
import com.android.hipart_android.ui.hipat.data.HipatFragAdData
import com.android.hipart_android.ui.mypick.data.GetMyPickData
import com.android.hipart_android.ui.search.SearchActivity
import com.android.hipart_android.util.SharedPreferenceController
import kotlinx.android.synthetic.main.fragment_hipat.*
import kotlinx.android.synthetic.main.navigation_category_hipat_port.*
import kotlinx.android.synthetic.main.toolbar_hipat.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.textColor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HiPatFragment : Fragment(), View.OnClickListener {

    private var flag = 0

    override fun onClick(v: View?) {
        when(v) {
            btn_frag_hipat_filter -> {
                activity!!.startActivityForResult<HipatFilterActivity>(9)
            }
            btn_hipat_frag_search -> {
                activity!!.startActivity<SearchActivity>()
            }
        }
    }

    private val allDataList by lazy {
        ArrayList<GetMyPickData>()
    }

    private val dataList by lazy {
        ArrayList<HipatFragAdData>()
    }
    private val hipatFragAdRecyclerViewAdapter by lazy {
        HipatFragAdRecyclerViewAdapter(this@HiPatFragment.context!!, dataList)
    }

    lateinit var navHipatFragLayout: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_hipat, container, false)


        flag = arguments!!.getInt("flag")
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureAdViewPager()
        configureMainTab()
        setOnClickListener()
        setInitialViewPager()

//        txt_hipat_nav_all.setOnClickListener {
//            object : OnSingleClickListener(){
//                override fun onSingleClick(v: View) {
//
//                }
//            }
//            Log.v("ㄷㄱㄷㄱㄷ","되는것?")
//        }
//        txt_hipat_nav_cpat.setOnClickListener {
//
//        }
    }

    private fun setInitialViewPager() {
        vp_hipat_frag_nav.setCurrentItem(flag)
    }


    private fun configureAdViewPager() {
        dataList.add(HipatFragAdData(
            "",
            false
        ))

        dataList.add(HipatFragAdData(
            "",
            true
        ))

        dataList.add(HipatFragAdData(
            "",
            true
        ))
        dataList.add(HipatFragAdData(
            "",
            true
        ))

        dataList.add(HipatFragAdData(
            "",
            true
        ))

        vp_hipat_frag_ad.adapter = hipatFragAdRecyclerViewAdapter
    }

    private fun configureMainTab() {
        vp_hipat_frag_nav.adapter = HipatFragPortViewPagerAdapter(childFragmentManager!!, 5)
        tl_hipat_frag_pat_nav.setupWithViewPager(vp_hipat_frag_nav)

        setCustomTabView()
        setHipatFragTabPagerListener()
        vp_hipat_frag_nav.offscreenPageLimit = 5
    }

    private fun setCustomTabView() {
        navHipatFragLayout = (activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                as LayoutInflater).inflate(R.layout.navigation_category_hipat_port, null, false)

        tl_hipat_frag_pat_nav.getTabAt(0)!!.customView = navHipatFragLayout
            .findViewById(R.id.rl_hipat_nav_all)
        tl_hipat_frag_pat_nav.getTabAt(1)!!.customView = navHipatFragLayout
            .findViewById(R.id.rl_hipat_nav_cpat)
        tl_hipat_frag_pat_nav.getTabAt(2)!!.customView = navHipatFragLayout
            .findViewById(R.id.rl_hipat_nav_epat)
        tl_hipat_frag_pat_nav.getTabAt(3)!!.customView = navHipatFragLayout
            .findViewById(R.id.rl_hipat_nav_tpat)
        tl_hipat_frag_pat_nav.getTabAt(4)!!.customView = navHipatFragLayout
            .findViewById(R.id.rl_hipat_nav_etc)
    }

    private fun setHipatFragTabPagerListener() {
        vp_hipat_frag_nav.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                when (p0) {
                    0 -> {
                        txt_hipat_nav_all.textColor = Color.parseColor("#7947fd")
                        txt_hipat_nav_cpat.textColor = Color.parseColor("#434343")
                        txt_hipat_nav_epat.textColor = Color.parseColor("#434343")
                        txt_hipat_nav_tpat.textColor = Color.parseColor("#434343")
                        txt_hipat_nav_etc.textColor = Color.parseColor("#434343") }
                    1 -> {
                        txt_hipat_nav_all.textColor = Color.parseColor("#434343")
                        txt_hipat_nav_cpat.textColor = Color.parseColor("#7947fd")
                        txt_hipat_nav_epat.textColor = Color.parseColor("#434343")
                        txt_hipat_nav_tpat.textColor = Color.parseColor("#434343")
                        txt_hipat_nav_etc.textColor = Color.parseColor("#434343") }
                    2 -> {
                        txt_hipat_nav_all.textColor = Color.parseColor("#434343")
                        txt_hipat_nav_cpat.textColor = Color.parseColor("#434343")
                        txt_hipat_nav_epat.textColor = Color.parseColor("#7947fd")
                        txt_hipat_nav_tpat.textColor = Color.parseColor("#434343")
                        txt_hipat_nav_etc.textColor = Color.parseColor("#434343") }
                    3 -> {
                        txt_hipat_nav_all.textColor = Color.parseColor("#434343")
                        txt_hipat_nav_cpat.textColor = Color.parseColor("#434343")
                        txt_hipat_nav_epat.textColor = Color.parseColor("#434343")
                        txt_hipat_nav_tpat.textColor = Color.parseColor("#7947fd")
                        txt_hipat_nav_etc.textColor = Color.parseColor("#434343") }
                    4 -> {
                        txt_hipat_nav_all.textColor = Color.parseColor("#434343")
                        txt_hipat_nav_cpat.textColor = Color.parseColor("#434343")
                        txt_hipat_nav_epat.textColor = Color.parseColor("#434343")
                        txt_hipat_nav_tpat.textColor = Color.parseColor("#434343")
                        txt_hipat_nav_etc.textColor = Color.parseColor("#7947fd") }
                }
            }

            override fun onPageSelected(p0: Int) {

            }

        })
    }

    private fun setOnClickListener() {
        btn_frag_hipat_filter.setOnClickListener(this)
        btn_hipat_frag_search.setOnClickListener(this)
    }

    fun getProfileLookUp(flag : Int){
        var networkService: NetworkService = ApplicationController.instance.networkService

        val getProfileLookUp = networkService.getProfileLookUp(
            SharedPreferenceController.getAuthorization(this@HiPatFragment.context!!),
            flag
        )

        getProfileLookUp.enqueue(object: Callback<GetProfileLookUpResponse> {
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
                    }
            }
        })
    }
}