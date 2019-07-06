package com.android.hipart_android.ui.hipat

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import com.android.hipart_android.ui.hipat.adapter.HipatFragAdRecyclerViewAdapter
import com.android.hipart_android.ui.hipat.adapter.HipatFragPortViewPagerAdapter
import com.android.hipart_android.ui.hipat.data.HipatFragAdData
import kotlinx.android.synthetic.main.fragment_hipat.*
import kotlinx.android.synthetic.main.navigation_category_hipat_port.*
import org.jetbrains.anko.textColor


class HiPatFragment : Fragment() {

    private val dataList by lazy {
        ArrayList<HipatFragAdData>()
    }
    private val hipatFragAdRecyclerViewAdapter by lazy {
        HipatFragAdRecyclerViewAdapter(this@HiPatFragment.context!!, dataList)
    }

    lateinit var navHipatFragLayout: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_hipat, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureAdViewPager()
        configureMainTab()
    }

    private fun configureAdViewPager() {
        vp_hipat_frag_ad.adapter = hipatFragAdRecyclerViewAdapter
    }

    private fun configureMainTab() {
        vp_hipat_frag_nav.adapter = HipatFragPortViewPagerAdapter(childFragmentManager!!, 5)
        tl_hipat_frag_pat_nav.setupWithViewPager(vp_hipat_frag_nav)

        setCustomTabView()
        setHipatFragTabPagerListener()
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
}