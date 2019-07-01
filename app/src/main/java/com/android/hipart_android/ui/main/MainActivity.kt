package com.android.hipart_android.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.android.hipart_android.R
import com.android.hipart_android.ui.adapter.MainBottomTabAdapter
import kotlinx.android.synthetic.main.activity_main.*
/**
*
* @author 탁형민
* @since 2019-07-02 05:46
**/

class MainActivity : AppCompatActivity() {

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
    }


    private fun setTabIcon() {
        val bottomTabView: View = (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.bottom_tab_bar, null, false)

        main_bottom_tab_layout.getTabAt(0)!!.customView = bottomTabView.findViewById(R.id.btn_home) as RelativeLayout
        main_bottom_tab_layout.getTabAt(1)!!.customView = bottomTabView.findViewById(R.id.btn_hi_part) as RelativeLayout
        main_bottom_tab_layout.getTabAt(2)!!.customView = bottomTabView.findViewById(R.id.btn_portfolio) as RelativeLayout
        main_bottom_tab_layout.getTabAt(3)!!.customView = bottomTabView.findViewById(R.id.btn_mypage) as RelativeLayout
    }
}
