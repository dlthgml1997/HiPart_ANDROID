package com.android.hipart_android.ui.hipart

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.hipart_android.R
import kotlinx.android.synthetic.main.activity_hipart_detail.*

class HipartDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hipart_detail)

        setClickListeners()
    }

    private fun setClickListeners() {
        btn_hip_detail_act_contact.setOnClickListener {
            val contactDialog: ContactAlertDialog = ContactAlertDialog(this)
            contactDialog.show()
        }
    }
}
