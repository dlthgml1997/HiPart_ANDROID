package com.android.hipart_android.ui.signup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.android.hipart_android.R
import kotlinx.android.synthetic.main.activity_signup.*


class SignupActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        edt_Signup_Email.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                ll_Signup_Email.setBackgroundResource(R.drawable.signup_purple_border)
                edt_Signup_Email_img.setImageResource(R.drawable.login_id_on_icon)
            } else {
                ll_Signup_Email.setBackgroundResource(R.drawable.signup_gray_border)
                edt_Signup_Email_img.setImageResource(R.drawable.login_id_off_icon)

                if (edt_Signup_Email.getText().toString().isEmpty()) {
                    ll_Signup_Email.setBackgroundResource(R.drawable.signup_gray_border)
                    edt_Signup_Email_img.setImageResource(R.drawable.login_id_off_icon)
                } else {
                    ll_Signup_Email.setBackgroundResource(R.drawable.signup_purple_border)
                    edt_Signup_Email_img.setImageResource(R.drawable.login_id_on_icon)
                    if (isValidEmail(edt_Signup_Email.text.toString())) {
                        txt_Signup_Wrong_Email.visibility = View.INVISIBLE
                    } else {
                        txt_Signup_Wrong_Email.visibility = View.VISIBLE
                    }
                }
            }
        }


        edt_Signup_Password.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                ll_Signup_Password.setBackgroundResource(R.drawable.signup_purple_border)
                edt_Signup_Password_img.setImageResource(R.drawable.login_password_on_icon)
            } else {
                ll_Signup_Password.setBackgroundResource(R.drawable.signup_gray_border)
                edt_Signup_Password_img.setImageResource(R.drawable.login_password_off_icon)
                if (edt_Signup_Password.getText().toString().length == 0) {
                    ll_Signup_Password.setBackgroundResource(R.drawable.signup_gray_border)
                    edt_Signup_Password_img.setImageResource(R.drawable.login_password_off_icon)
                } else {
                    ll_Signup_Password.setBackgroundResource(R.drawable.signup_purple_border)
                    edt_Signup_Password_img.setImageResource(R.drawable.login_password_on_icon)
                }
            }
        }
        edt_Signup_PasswordCheck.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                ll_Signup_Password_Check.setBackgroundResource(R.drawable.signup_purple_border)
                edt_Signup_PasswordCheck_img.setImageResource(R.drawable.login_password_on_icon)
            } else {
                ll_Signup_Password_Check.setBackgroundResource(R.drawable.signup_gray_border)
                edt_Signup_PasswordCheck_img.setImageResource(R.drawable.login_password_off_icon)
                if (edt_Signup_PasswordCheck.getText().toString().length == 0) {
                    ll_Signup_Password_Check.setBackgroundResource(R.drawable.signup_gray_border)
                    edt_Signup_PasswordCheck_img.setImageResource(R.drawable.login_password_off_icon)
                } else {
                    ll_Signup_Password_Check.setBackgroundResource(R.drawable.signup_purple_border)
                    edt_Signup_PasswordCheck_img.setImageResource(R.drawable.login_password_on_icon)
                }
            }
        }
        edt_Signup_Nickname.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                ll_Signup_Nickname.setBackgroundResource(R.drawable.signup_purple_border)
                edt_Signup_Nickname_img.setImageResource(R.drawable.login_nickname_on_icon)
            } else {
                ll_Signup_Nickname.setBackgroundResource(R.drawable.signup_gray_border)
                edt_Signup_Nickname_img.setImageResource(R.drawable.login_nickname_off_icon)
                if (edt_Signup_Nickname.getText().toString().length == 0) {
                    ll_Signup_Nickname.setBackgroundResource(R.drawable.signup_gray_border)
                    edt_Signup_Nickname_img.setImageResource(R.drawable.login_nickname_off_icon)
                } else {
                    ll_Signup_Nickname.setBackgroundResource(R.drawable.signup_purple_border)
                    edt_Signup_Nickname_img.setImageResource(R.drawable.login_nickname_on_icon)
                }
            }
        }
        edt_Signup_Phonenumber.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                ll_Signup_Phonenumber.setBackgroundResource(R.drawable.signup_purple_border)
                edt_Signup_Phonenumber_img.setImageResource(R.drawable.login_phone_on_icon)
            } else {
                ll_Signup_Phonenumber.setBackgroundResource(R.drawable.signup_gray_border)
                edt_Signup_Phonenumber_img.setImageResource(R.drawable.login_phone_off_icon)
                if (edt_Signup_Phonenumber.getText().toString().length == 0) {
                    ll_Signup_Phonenumber.setBackgroundResource(R.drawable.signup_gray_border)
                    edt_Signup_Phonenumber_img.setImageResource(R.drawable.login_phone_off_icon)
                } else {
                    ll_Signup_Phonenumber.setBackgroundResource(R.drawable.signup_purple_border)
                    edt_Signup_Phonenumber_img.setImageResource(R.drawable.login_phone_on_icon)
                }
            }

        }
        btn_signup_nextstep.setOnClickListener {
            val intent = Intent(this, SignupPartActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

//    private fun String.isValidEmails(): Boolean = this.isNotEmpty() &&
//            Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
