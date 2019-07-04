package com.android.hipart_android.ui.signup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.hipart_android.R
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.backgroundDrawable

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        edt_Signup_Email.setOnFocusChangeListener{v, hasFocus ->
            if(hasFocus) {
                ll_Signup_Email.setBackgroundResource(R.drawable.signup_purple_border)
                edt_Signup_Email_img.setImageResource(R.drawable.login_id_on_icon)
            }
            else {
                ll_Signup_Email.setBackgroundResource(R.drawable.signup_gray_border)
                edt_Signup_Email_img.setImageResource(R.drawable.login_id_off_icon)
            }
        }
        edt_Signup_Password.setOnFocusChangeListener{v, hasFocus ->
            if(hasFocus) {
                ll_Signup_Password.setBackgroundResource(R.drawable.signup_purple_border)
                edt_Signup_Password_img.setImageResource(R.drawable.login_password_on_icon)
            }
            else{
                ll_Signup_Password.setBackgroundResource(R.drawable.signup_gray_border)
                edt_Signup_Password_img.setImageResource(R.drawable.login_password_off_icon)
            }
        }
        edt_Signup_PasswordCheck.setOnFocusChangeListener{v, hasFocus ->
            if(hasFocus) {
                ll_Signup_Password_Check.setBackgroundResource(R.drawable.signup_purple_border)
                edt_Signup_PasswordCheck_img.setImageResource(R.drawable.login_password_on_icon)
            }
            else{
                ll_Signup_Password_Check.setBackgroundResource(R.drawable.signup_gray_border)
                edt_Signup_PasswordCheck_img.setImageResource(R.drawable.login_password_off_icon)
            }
        }
        edt_Signup_Nickname.setOnFocusChangeListener{v, hasFocus ->
            if(hasFocus) {
                ll_Signup_Nickname.setBackgroundResource(R.drawable.signup_purple_border)
                edt_Signup_Nickname_img.setImageResource(R.drawable.login_nickname_on_icon)
            }
            else{
                ll_Signup_Nickname.setBackgroundResource(R.drawable.signup_gray_border)
                edt_Signup_Nickname_img.setImageResource(R.drawable.login_nickname_off_icon)
            }
        }
        edt_Signup_Phonenumber.setOnFocusChangeListener{v, hasFocus ->
            if(hasFocus) {
                ll_Signup_Phonenumber.setBackgroundResource(R.drawable.signup_purple_border)
                edt_Signup_Phonenumber_img.setImageResource(R.drawable.login_nickname_on_icon)
            }
            else{
                ll_Signup_Phonenumber.setBackgroundResource(R.drawable.signup_gray_border)
                edt_Signup_Phonenumber_img.setImageResource(R.drawable.login_nickname_off_icon)
            }
        }

    }
}
