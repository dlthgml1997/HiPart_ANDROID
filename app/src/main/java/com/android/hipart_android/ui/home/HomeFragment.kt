package com.android.hipart_android.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import com.android.hipart_android.ui.home.adapter.HomeFragAdAdapter
import com.android.hipart_android.ui.home.adapter.HomeFragHipatAdapter
import com.android.hipart_android.ui.home.data.HomeFragAdData
import com.android.hipart_android.ui.home.data.HomeFragHipatData
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {


    private val hipartDataList by lazy {
        ArrayList<HomeFragHipatData>()
    }

    private val homeFragHipatAdapter by lazy {
        HomeFragHipatAdapter(hipartDataList, this@HomeFragment.context)
    }

    private val adDataList by lazy {
        ArrayList<HomeFragAdData>()
    }

    private val homeFragAdAdapter by lazy {
        HomeFragAdAdapter(adDataList, this@HomeFragment.context)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setVpAdapter()
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
}