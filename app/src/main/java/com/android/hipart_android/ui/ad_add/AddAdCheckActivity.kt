package com.android.hipart_android.ui.ad_add

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import com.android.hipart_android.R
import com.android.hipart_android.util.BaseActivity
import kotlinx.android.synthetic.main.activity_add_ad_check.*

class AddAdCheckActivity : BaseActivity() {

    private val addAdConfirmDialog by lazy {
        AddAdConfirmDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ad_check)

        buttonChange()
        setOnBtnClickListener()
        configureMainTab()
    }

    fun dismissFinish() {

        setResult(Activity.RESULT_OK, intent)

        Log.d("AddAdCheckActivity", "dismissfinish")
    }


    private fun setOnBtnClickListener() {
        btn_toolbar_add_ad_back.setOnClickListener {
            finish()
        }
    }

    private fun buttonChange() {
        ll_addad_act_term.setOnClickListener {
            if (btn_addad_act_term_accept.isChecked) {
                btn_addad_act_term_accept.isChecked = false
            } else {
                btn_addad_act_term_accept.isChecked = true
            }
        }
        btn_addad_act_term_accept.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    btn_addad_act_term_accept.setBackgroundResource(R.drawable.hipat_check_on_img)

                    btn_addad_act_confirm.apply {
                        setBackgroundResource(R.drawable.bg_contact_alert_after)
                        setOnClickListener {
                            if (btn_addad_act_term_accept.isChecked) {
                                addAdConfirmDialog.show(supportFragmentManager, "addadconfirm dialog")

                            }
                            // 통신
//                            postHifiveResponse(nickname, type)
//
//                            Log.d(TAG, "connect Server from Dialog")
                        }
                    }
                } else {
                    btn_addad_act_term_accept.setBackgroundResource(R.drawable.hipat_check_off_img)
                    btn_addad_act_confirm.setBackgroundResource(R.drawable.bg_contact_alert_before)
                }
            }
        })
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
    }

    private fun configureMainTab() {

        vp_add_ad_slider.adapter = AddAdSliderPagerAdapter(supportFragmentManager, 3)
        vp_add_ad_slider.offscreenPageLimit = 2
        tl_main_indicator.setupWithViewPager(vp_add_ad_slider)

        val navIndicatorMainLayout: View =
            (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                .inflate(R.layout.navigation_indicator_addad, null, false)
        tl_main_indicator.getTabAt(0)!!.customView =
            navIndicatorMainLayout.findViewById(R.id.img_nav_indicator_main_1) as ImageView
        tl_main_indicator.getTabAt(1)!!.customView =
            navIndicatorMainLayout.findViewById(R.id.img_nav_indicator_main_2) as ImageView
        tl_main_indicator.getTabAt(2)!!.customView =
            navIndicatorMainLayout.findViewById(R.id.img_nav_indicator_main_3) as ImageView

        tl_main_indicator.isEnabled = true

        tl_main_indicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                p0!!.customView!!.setBackgroundColor(resources.getColor(R.color.colorGray))
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                p0!!.customView!!.setBackgroundColor(resources.getColor(R.color.colorWhite))
            }
        })
    }
}
