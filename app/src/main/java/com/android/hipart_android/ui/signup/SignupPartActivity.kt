package com.android.hipart_android.ui.signup

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.hipart_android.R
import com.android.hipart_android.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_signup_part.*
import org.jetbrains.anko.startActivity

class SignupPartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_part)

        setOnClickListener()

    }
    
    fun setOnClickListener(){
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
        iv_signup_part_act_finish.setOnClickListener {
            startActivity<LoginActivity>()
        }
    }
}
