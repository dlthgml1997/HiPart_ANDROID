package com.android.hipart_android.ui.search

import android.graphics.Color
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.android.hipart_android.R
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.tab_search_act.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSearch()

        setViewPager()

        setTablayout()


    }

    private fun setTablayout() {
        vp_search_act.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                tl_search_act.getTabAt(p0)?.select()
//                when(p0) {
//                    0 -> {
//                        tl_search_act.getTabAt(0)!!(Color.parseColor("#7947fd"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                    }
//                    1 -> {
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#7947fd"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                    }
//                    2 -> {
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#7947fd"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                    }
//                    3 -> {
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#7947fd"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                    }
//                    4 -> {
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#7947fd"))
//                    }
//
//                }
            }
        })
    }

    private fun setSearch() {


        et_search_act_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s.toString().length>0) {
                    iv_search_act_search_text.setImageResource(R.drawable.search_x_big_icon)
                    ll_search_act_recent_search.visibility = View.GONE
                    rl_search_act_search_result.visibility = View.VISIBLE


                }else {
                    iv_search_act_search_text.setImageResource(R.drawable.search_search_icon)
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

        val tabSearch : View = (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.tab_search_act, null, false)

        tl_search_act.getTabAt(0)!!.customView = tabSearch.findViewById(R.id.rl_tab_search_frag_all) as RelativeLayout
        tl_search_act.getTabAt(1)!!.customView = tabSearch.findViewById(R.id.rl_tab_search_frag_c) as RelativeLayout
        tl_search_act.getTabAt(2)!!.customView = tabSearch.findViewById(R.id.rl_tab_search_frag_e) as RelativeLayout
        tl_search_act.getTabAt(3)!!.customView = tabSearch.findViewById(R.id.rl_tab_search_frag_t) as RelativeLayout
        tl_search_act.getTabAt(4)!!.customView = tabSearch.findViewById(R.id.rl_tab_search_frag_etc) as RelativeLayout



//        tl_search_act.getTabAt(0)!!.customView = tabSearch.findViewById(R.id.rl_nav_category_main_all) as RelativeLayout
//        tl_search_act.getTabAt(1)!!.customView = tabSearch.findViewById(R.id.rl_nav_category_main_new) as RelativeLayout
//        tl_search_act.getTabAt(2)!!.customView = tabSearch.findViewById(R.id.rl_nav_category_main_end) as RelativeLayout

    }
}
