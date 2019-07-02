package com.android.hipart_android.ui.main

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.android.hipart_android.R
import com.android.hipart_android.ui.adapter.MainBottomTabAdapter
import com.android.hipart_android.util.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_tab_bar.*
import org.jetbrains.anko.textColor

/**
*
* @author 탁형민
* @since 2019-07-02 05:46
**/

class MainActivity : BaseActivity() {

    private val mainBottomTabAdapter : MainBottomTabAdapter by lazy {
        MainBottomTabAdapter(4, supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureMainTabMenu()
    }

    // TODO
    // FIXME
    private fun configureMainTabMenu() {
        main_fragment_pager.adapter = mainBottomTabAdapter
        main_fragment_pager.offscreenPageLimit = 4
        main_bottom_tab_layout.setupWithViewPager(main_fragment_pager)
        setTabIcon()
        setMainFragPagerListener()
    }


    private fun setTabIcon() {
        val bottomTabView: View = (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.bottom_tab_bar, null, false)

        main_bottom_tab_layout.getTabAt(0)!!.customView = bottomTabView.findViewById(R.id.btn_home) as RelativeLayout
        main_bottom_tab_layout.getTabAt(1)!!.customView = bottomTabView.findViewById(R.id.btn_hi_part) as RelativeLayout
        main_bottom_tab_layout.getTabAt(2)!!.customView = bottomTabView.findViewById(R.id.btn_portfolio) as RelativeLayout
        main_bottom_tab_layout.getTabAt(3)!!.customView = bottomTabView.findViewById(R.id.btn_mypage) as RelativeLayout
    }

    private fun setMainFragPagerListener(){
        main_fragment_pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                when (p0) {
                    0 -> {
                        iv_home.setImageResource(R.drawable.main_tb_home_on_icon)
                        tv_home.textColor = Color.parseColor("#7947fd")

                        iv_hi_part.setImageResource(R.drawable.main_tb_hipot_off_icon)
                        tv_hipart.textColor = Color.parseColor("#838383")

                        iv_portfolio.setImageResource(R.drawable.main_tb_pofol_off_icon)
                        tv_portfolio.textColor = Color.parseColor("#838383")

                        iv_mypage.setImageResource(R.drawable.main_tb_mypg_off_icon)
                        tv_mypage.textColor = Color.parseColor("#838383")
                    }
                    1 -> {
                        iv_home.setImageResource(R.drawable.main_tb_home_on_icon)
                        tv_home.textColor = Color.parseColor("#838383")

                        iv_hi_part.setImageResource(R.drawable.main_tb_hipot_off_icon)
                        tv_hipart.textColor = Color.parseColor("#7947fd")

                        iv_portfolio.setImageResource(R.drawable.main_tb_pofol_off_icon)
                        tv_portfolio.textColor = Color.parseColor("#838383")

                        iv_mypage.setImageResource(R.drawable.main_tb_mypg_off_icon)
                        tv_mypage.textColor = Color.parseColor("#838383")

                    }
                    2 -> {

                        iv_home.setImageResource(R.drawable.main_tb_home_on_icon)
                        tv_home.textColor = Color.parseColor("#838383")

                        iv_hi_part.setImageResource(R.drawable.main_tb_hipot_off_icon)
                        tv_hipart.textColor = Color.parseColor("#838383")

                        iv_portfolio.setImageResource(R.drawable.main_tb_pofol_off_icon)
                        tv_portfolio.textColor = Color.parseColor("#7947fd")

                        iv_mypage.setImageResource(R.drawable.main_tb_mypg_off_icon)
                        tv_mypage.textColor = Color.parseColor("#838383")
                    }
                    3 -> {
                        iv_home.setImageResource(R.drawable.main_tb_home_on_icon)
                        tv_home.textColor = Color.parseColor("#838383")

                        iv_hi_part.setImageResource(R.drawable.main_tb_hipot_off_icon)
                        tv_hipart.textColor = Color.parseColor("#838383")

                        iv_portfolio.setImageResource(R.drawable.main_tb_pofol_off_icon)
                        tv_portfolio.textColor = Color.parseColor("#838383")

                        iv_mypage.setImageResource(R.drawable.main_tb_mypg_off_icon)
                        tv_mypage.textColor = Color.parseColor("#7947fd")
                    }
                }
            }

            override fun onPageSelected(p0: Int) {
            }
        })
    }
}
