package com.android.hipart_android.ui.hipart


import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton

import com.android.hipart_android.R
import kotlinx.android.synthetic.main.activity_hipart_detail.view.*
import kotlinx.android.synthetic.main.fragment_contact_dialog.*

class ContactAlertDialog(val ctx: Context) : AlertDialog(ctx) {
    private var bool = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_contact_dialog)

        buttonChange()

    }

    private fun buttonChange() {
        ll_contact_dial_frag_term.setOnClickListener{
            if(btn_contact_dial_term_accept.isChecked){
                btn_contact_dial_term_accept.isChecked = false
            }else {
                btn_contact_dial_term_accept.isChecked = true
            }
        }
        btn_contact_dial_term_accept.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    btn_contact_dial_term_accept.setBackgroundResource(R.drawable.hipat_check_on_img)

                    btn_contact_dial_confirm.apply {
                        setBackgroundResource(R.drawable.bg_contact_alert_after)
                        setOnClickListener {
                            dismiss()

                        }
                    }

                } else {
                    btn_contact_dial_term_accept.setBackgroundResource(R.drawable.hipat_check_off_img)
                    btn_contact_dial_confirm.setBackgroundResource(R.drawable.bg_contact_alert_before)
                }
            }
        })


    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
