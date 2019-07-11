package com.android.hipart_android.ui.ad_add

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.android.hipart_android.R
import com.android.hipart_android.util.BaseActivity
import kotlinx.android.synthetic.main.activity_add_ad.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult

class AddAdActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ad)

        configureTitleBar()
        configureMainTab()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(requestCode == 0){
//            if(resultCode == Activity.RESULT_OK)
//                Log.d("AddAdActivity", "onActivityResult")
//                    finish()
//        }
//    }

    private fun configureTitleBar() {
        btn_addad_act_cancle.setOnClickListener {
            finish()
        }
        btn_addad_act_next.setOnClickListener {

            val intent = Intent(this, AddAdCheckActivity::class.java)
            startActivityForResult(intent, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode,data)

        if (resultCode == -101) {
            finish()

        }
    }

    private fun configureMainTab() {

        vp_add_ad_slider.adapter = AddAdSliderPagerAdapter(supportFragmentManager, 3)
        vp_add_ad_slider.offscreenPageLimit = 2
        tl_main_indicator.setupWithViewPager(vp_add_ad_slider)

        val navIndicatorMainLayout: View = (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.navigation_indicator_addad, null, false)
        tl_main_indicator.getTabAt(0)!!.customView = navIndicatorMainLayout.
            findViewById(R.id.img_nav_indicator_main_1) as ImageView
        tl_main_indicator.getTabAt(1)!!.customView = navIndicatorMainLayout.
            findViewById(R.id.img_nav_indicator_main_2) as ImageView
        tl_main_indicator.getTabAt(2)!!.customView = navIndicatorMainLayout.
            findViewById(R.id.img_nav_indicator_main_3) as ImageView

        tl_main_indicator.isEnabled = true

        tl_main_indicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
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
