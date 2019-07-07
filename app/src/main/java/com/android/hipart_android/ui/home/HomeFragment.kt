package com.android.hipart_android.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import com.android.hipart_android.ui.hipat.HiPatFragment
import com.android.hipart_android.ui.home.adapter.HomeFragAdAdapter
import com.android.hipart_android.ui.home.adapter.HomeFragHipatAdapter
import com.android.hipart_android.ui.home.data.HomeFragAdData
import com.android.hipart_android.ui.home.data.HomeFragHipatData
import com.android.hipart_android.ui.main.MainActivity
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
                (activity as MainActivity).replaceFragmentFromHome(R.id.frame_layout_main_act, HiPatFragment(), 1)
                Log.d("HomeFragment", "replaceFragmentFromHome")
            }

            btn_frag_home_e_pat -> {
                (activity as MainActivity).replaceFragmentFromHome(R.id.frame_layout_main_act, HiPatFragment(), 2)
                Log.d("HomeFragment", "replaceFragmentFromHome")
            }

            btn_frag_home_t_pat -> {
                (activity as MainActivity).replaceFragmentFromHome(R.id.frame_layout_main_act, HiPatFragment(), 3)
                Log.d("HomeFragment", "replaceFragmentFromHome")
            }

            btn_frag_home_etc -> {
                (activity as MainActivity).replaceFragmentFromHome(R.id.frame_layout_main_act, HiPatFragment(), 4)
                Log.d("HomeFragment", "replaceFragmentFromHome")
            }
        }
    }

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

    private fun setOnClickListener() {
        btn_frag_home_alarm.setOnClickListener(this)
        btn_frag_home_search.setOnClickListener(this)
        btn_frag_home_c_pat.setOnClickListener(this)
        btn_frag_home_e_pat.setOnClickListener(this)
        btn_frag_home_t_pat.setOnClickListener(this)
        btn_frag_home_etc.setOnClickListener(this)
    }
}