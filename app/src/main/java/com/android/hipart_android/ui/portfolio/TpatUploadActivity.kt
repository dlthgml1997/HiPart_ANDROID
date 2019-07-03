package com.android.hipart_android.ui.portfolio

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.hipart_android.R
import kotlinx.android.synthetic.main.activity_upload_tpat.*

class TpatUploadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_tpat)

            setOnBtnClickListener()
            setOnFocusChangeListener()

    }

    private fun setOnBtnClickListener(){
        btn_tpat_upload_act_back.setOnClickListener {
            finish()
        }
        btn_tpat_upload_act_submit.setOnClickListener {
            finish()
            /* *
            나중에 통신 연결 하기/
             */
        }
    }

    private fun setOnFocusChangeListener(){
        edt_tpat_upload_act_korean.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                ll_tpat_upload_act_korean.setBackgroundResource(R.drawable.upload_tpat_port_border_purple)
            }else if(!hasFocus && edt_tpat_upload_act_korean.text.isEmpty()){
                ll_tpat_upload_act_korean.setBackgroundResource(R.drawable.upload_tpat_port_border_gray)
            }
        }

        edt_tpat_upload_act_foreign.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                ll_tpat_upload_act_foreign.setBackgroundResource(R.drawable.upload_tpat_port_border_purple)
            }else if(!hasFocus && edt_tpat_upload_act_foreign.text.isEmpty()){
                ll_tpat_upload_act_foreign.setBackgroundResource(R.drawable.upload_tpat_port_border_gray)
            }
        }
    }
}
