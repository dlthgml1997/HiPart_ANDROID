package com.android.hipart_android.ui.main

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v4.app.Fragment
import android.view.View
import com.android.hipart_android.R
import com.android.hipart_android.ui.hipat.HiPatFragment
import com.android.hipart_android.ui.home.HomeFragment
import com.android.hipart_android.ui.mypage.fragment.MyPageFragment
import com.android.hipart_android.ui.portfolio.PortFolioFragment
import com.android.hipart_android.util.BaseActivity
import com.android.hipart_android.util.FragmentKind
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 * @author 탁형민
 * @since 2019-07-02 05:46
 **/

class MainActivity : BaseActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v) {
            btn_home -> {
                replaceFragment(R.id.frame_layout_main_act, HomeFragment())
                setBottomIconChanger(FragmentKind.Home)
            }

            btn_hi_part -> {
                replaceFragment(R.id.frame_layout_main_act, HiPatFragment())
                setBottomIconChanger(FragmentKind.Hipat)
            }

            btn_portfolio -> {
                addFragment(R.id.frame_layout_main_act, PortFolioFragment())
                setBottomIconChanger(FragmentKind.PortFolio)
            }

            btn_mypage -> {
                replaceFragment(R.id.frame_layout_main_act, MyPageFragment())
                setBottomIconChanger(FragmentKind.Mypage)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        attachHomeFragment()
        setOnClickListener()
    }


    private fun attachHomeFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_layout_main_act, HomeFragment())
            .commit()
    }

    private fun replaceFragment(frameLayoutId: Int, fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(frameLayoutId, fragment)
            .commit()
    }

    private fun addFragment(frameLayoutId: Int, fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(frameLayoutId, fragment)
            .commit()
    }

    private fun setOnClickListener() {
        btn_home.setOnClickListener(this)
        btn_hi_part.setOnClickListener(this)
        btn_portfolio.setOnClickListener(this)
        btn_mypage.setOnClickListener(this)
    }

    private fun setBottomIconChanger(fragKind: FragmentKind) {

        when (fragKind) {
            FragmentKind.Home -> {
                iv_home.setImageResource(R.drawable.main_tb_home_on_icon)
                iv_hi_part.isSelected = false
                iv_portfolio.isSelected = false
                iv_mypage.isSelected = false

                tv_home.setTextColor(Color.parseColor("#7947fd"))
                tv_hipart.setTextColor(Color.parseColor("#838383"))
                tv_portfolio.setTextColor(Color.parseColor("#838383"))
                tv_mypage.setTextColor(Color.parseColor("#838383"))
            }
            FragmentKind.Hipat -> {
                iv_home.setImageResource(R.drawable.main_tb_home_off_icon)
                iv_hi_part.isSelected = true
                iv_portfolio.isSelected = false
                iv_mypage.isSelected = false

                tv_home.setTextColor(Color.parseColor("#838383"))
                tv_hipart.setTextColor(Color.parseColor("#7947fd"))
                tv_portfolio.setTextColor(Color.parseColor("#838383"))
                tv_mypage.setTextColor(Color.parseColor("#838383"))
            }
            FragmentKind.PortFolio -> {
                iv_home.setImageResource(R.drawable.main_tb_home_off_icon)
                iv_hi_part.isSelected = false
                iv_portfolio.isSelected = true
                iv_mypage.isSelected = false

                tv_home.setTextColor(Color.parseColor("#838383"))
                tv_hipart.setTextColor(Color.parseColor("#838383"))
                tv_portfolio.setTextColor(Color.parseColor("#7947fd"))
                tv_mypage.setTextColor(Color.parseColor("#838383"))
            }
            FragmentKind.Mypage -> {
                iv_home.setImageResource(R.drawable.main_tb_home_off_icon)
                iv_hi_part.isSelected = false
                iv_portfolio.isSelected = false
                iv_mypage.isSelected = true

                tv_home.setTextColor(Color.parseColor("#838383"))
                tv_hipart.setTextColor(Color.parseColor("#838383"))
                tv_portfolio.setTextColor(Color.parseColor("#838383"))
                tv_mypage.setTextColor(Color.parseColor("#7947fd"))
            }
        }

    }

    private fun setBottomSheetDialog() {
        val view = layoutInflater.inflate(R.layout.fragment_portfolio_bottom_sheet_dialog, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()
    }
}
