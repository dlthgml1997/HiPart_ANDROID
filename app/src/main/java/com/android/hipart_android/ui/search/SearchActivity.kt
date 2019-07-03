package com.android.hipart_android.ui.search

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.android.hipart_android.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSearch()

        setViewPager()
    }

    private fun setSearch() {
        et_search_act_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s.toString().length>0) {
                    ll_search_act_recent_search.visibility = View.GONE
                    rl_search_act_search_result.visibility = View.VISIBLE

                }else {
                    ll_search_act_recent_search.visibility = View.VISIBLE
                    rl_search_act_search_result.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun setViewPager() {
        vp_search_act.adapter = SearchFragmentPagerAdapter(5, supportFragmentManager)
        tl_search_act.setupWithViewPager(vp_search_act)

        val tabSearch = (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.tab_search_frag, null, false)

        tl_search_act.getTabAt(0)!!.customView = tabSearch.findViewById(R.id.iv_tab_search_frag_all) as RelativeLayout
        tl_search_act.getTabAt(1)!!.customView = tabSearch.findViewById(R.id.iv_tab_search_frag_c) as RelativeLayout
        tl_search_act.getTabAt(2)!!.customView = tabSearch.findViewById(R.id.iv_tab_search_frag_e) as RelativeLayout
        tl_search_act.getTabAt(3)!!.customView = tabSearch.findViewById(R.id.iv_tab_search_frag_t) as RelativeLayout
        tl_search_act.getTabAt(4)!!.customView = tabSearch.findViewById(R.id.iv_tab_search_frag_etc) as RelativeLayout


    }
}
