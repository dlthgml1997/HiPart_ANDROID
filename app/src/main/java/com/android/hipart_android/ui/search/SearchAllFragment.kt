package com.android.hipart_android.ui.search


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.android.hipart_android.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchAllFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_all, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setViewPager()


    }


    private fun setViewPager() {
        vp_search_act.adapter = SearchFragmentPagerAdapter(5, childFragmentManager)
        tl_search_act.setupWithViewPager(vp_search_act)

        val tabSearch = this.layoutInflater.inflate(R.layout.tab_search_frag, null, false)

        tl_search_act.getTabAt(0)!!.customView = tabSearch.findViewById(R.id.iv_tab_search_frag_all) as ImageView
        tl_search_act.getTabAt(1)!!.customView = tabSearch.findViewById(R.id.iv_tab_search_frag_c) as ImageView
        tl_search_act.getTabAt(2)!!.customView = tabSearch.findViewById(R.id.iv_tab_search_frag_e) as ImageView
        tl_search_act.getTabAt(3)!!.customView = tabSearch.findViewById(R.id.iv_tab_search_frag_t) as ImageView
        tl_search_act.getTabAt(4)!!.customView = tabSearch.findViewById(R.id.iv_tab_search_frag_etc) as ImageView


    }


}
