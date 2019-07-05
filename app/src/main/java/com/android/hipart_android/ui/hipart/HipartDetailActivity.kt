package com.android.hipart_android.ui.hipart

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.android.hipart_android.R
import kotlinx.android.synthetic.main.activity_hipart_detail.*

class HipartDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hipart_detail)

        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(R.id.fl_hip_detail_act, HipartDetailCpatFragment())
        fragmentTransaction.commit()
    }

}
