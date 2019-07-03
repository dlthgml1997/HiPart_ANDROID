package com.android.hipart_android.ui.hipat

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import com.android.hipart_android.ui.hipat.adapter.HipatFragAdRecyclerViewAdapter
import com.android.hipart_android.ui.hipat.adapter.HipatFragPortViewPagerAdapter
import com.android.hipart_android.ui.hipat.data.HipatFragAdData
import kotlinx.android.synthetic.main.fragment_hipat.*


class HiPatFragment : Fragment() {

    private val dataList by lazy {
        ArrayList<HipatFragAdData>()
    }
    private val hipatFragAdRecyclerViewAdapter by lazy {
        HipatFragAdRecyclerViewAdapter(context!!, dataList)
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
        //setOnTabSelectedListener()
    }

    private fun configureAdViewPager() {
        vp_hipat_frag_ad.adapter = hipatFragAdRecyclerViewAdapter
    }

    private fun configureMainTab() {
        vp_hipat_frag_nav.adapter = HipatFragPortViewPagerAdapter(fragmentManager!!, 5)
        tl_hipat_frag_pat_nav.setupWithViewPager(vp_hipat_frag_nav)

        tl_hipat_frag_pat_nav.getTabAt(0)!!.text = "전체"
        tl_hipat_frag_pat_nav.getTabAt(1)!!.text = "C-PAT"
        tl_hipat_frag_pat_nav.getTabAt(2)!!.text = "E-PAT"
        tl_hipat_frag_pat_nav.getTabAt(3)!!.text = "T-PAT"
        tl_hipat_frag_pat_nav.getTabAt(4)!!.text = "ETC."


    }
}