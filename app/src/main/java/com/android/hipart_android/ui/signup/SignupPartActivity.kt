package com.android.hipart_android.ui.signup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.hipart_android.R
import kotlinx.android.synthetic.main.activity_signup_part.*
class SignupPartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_part)
        img_Signup_cpat.setOnClickListener {
            if (img_Signup_cpat.isSelected == true) {
                img_Signup_cpat.isSelected = false
            } else {
                img_Signup_cpat.isSelected = true
                img_Signup_epat.isSelected = false
                img_Signup_tpat.isSelected = false
                img_Signup_etc.isSelected = false
            }
        }
        img_Signup_epat.setOnClickListener {
            if (img_Signup_epat.isSelected == true) {
                img_Signup_epat.isSelected = false
            } else {
                img_Signup_cpat.isSelected = false
                img_Signup_epat.isSelected = true
                img_Signup_tpat.isSelected = false
                img_Signup_etc.isSelected = false
            }
        }
        img_Signup_tpat.setOnClickListener {
            if (img_Signup_tpat.isSelected == true) {
                img_Signup_tpat.isSelected = false
            } else {
                img_Signup_cpat.isSelected = false
                img_Signup_epat.isSelected = false
                img_Signup_tpat.isSelected = true
                img_Signup_etc.isSelected = false
            }
            img_Signup_etc.setOnClickListener {
                if (img_Signup_etc.isSelected == true) {
                    img_Signup_etc.isSelected = false
                } else {
                    img_Signup_cpat.isSelected = false
                    img_Signup_epat.isSelected = false
                    img_Signup_tpat.isSelected = false
                    img_Signup_etc.isSelected = true
                }
            }
        }
    }
}
