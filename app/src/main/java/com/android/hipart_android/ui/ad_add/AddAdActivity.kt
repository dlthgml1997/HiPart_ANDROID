package com.android.hipart_android.ui.ad_add

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.hipart_android.R
import kotlinx.android.synthetic.main.activity_add_ad.*

class AddAdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ad)

        configureTitleBar()
    }

    private fun configureTitleBar() {
        btn_toolbar_add_ad_back.setOnClickListener{
            finish()
        }
    }
}
