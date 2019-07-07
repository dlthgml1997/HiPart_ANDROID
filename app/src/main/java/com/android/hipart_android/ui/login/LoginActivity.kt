package com.android.hipart_android.ui.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.network.NetworkService
import com.android.hipart_android.ui.login.data.PostLoginRequest
import com.android.hipart_android.ui.login.data.PostLoginResponse
import com.android.hipart_android.ui.main.MainActivity
import com.android.hipart_android.ui.signup.SignupActivity
import com.android.hipart_android.util.SharedPreferenceController
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val REQUEST_CODE_LOGIN_ACTIVITY = 1000

    val networkService: NetworkService by lazy{
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setOnFocusChangeListener()
        setTextChangedListenerEmail()
        setTextChangedListenerPassword()
        setOnBtnClickListener()
    }

    private fun setOnFocusChangeListener() {
        edt_login_act_email.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                rl_login_act_email.setBackgroundResource(R.drawable.primary_border)
                img_login_act_email_off.setImageResource(R.drawable.login_id_on_icon)
            } else if(!hasFocus && edt_login_act_email.text.isEmpty()){
                rl_login_act_email.setBackgroundResource(R.drawable.gray_border)
                img_login_act_email_off.setImageResource(R.drawable.login_id_off_icon)
            }
        }

        edt_login_act_password.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                rl_login_act_password.setBackgroundResource(R.drawable.primary_border)
                img_login_act_password_off.setImageResource(R.drawable.login_password_on_icon)
            } else if(!hasFocus && edt_login_act_email.text.isEmpty()){
                rl_login_act_password.setBackgroundResource(R.drawable.gray_border)
                img_login_act_password_off.setImageResource(R.drawable.login_password_off_icon)
            }
        }
    }

    private fun setOnBtnClickListener() {
        btn_login_act_submit.setOnClickListener {
            val user_email = edt_login_act_email.text.toString()
            val user_pw: String = edt_login_act_password.text.toString()
            if (isValid(user_email, user_pw)) {
                postLoginResponse(user_email, user_pw)
            }
        }
        txt_login_act_signup.setOnClickListener {
            startActivity<SignupActivity>()
        }

    }

    private fun postLoginResponse(user_email: String, user_pw: String) {

        val postLoginResponse: Call<PostLoginResponse> =
            networkService.postLoginResponse("application/json", PostLoginRequest(user_email, user_pw))
        postLoginResponse.enqueue(object: Callback<PostLoginResponse> {
            override fun onFailure(call: Call<PostLoginResponse>, t: Throwable){
                Log.e("Login failed", t.toString())
            }

            override fun onResponse(call: Call<PostLoginResponse>, response: Response<PostLoginResponse>) {
                // TODO : 로그인 실패 분기 안함
                response?.takeIf { it.isSuccessful }
                    ?.body()?.takeIf { it.status == 200 }
                    ?.let{
                        SharedPreferenceController.setAuthorization(this@LoginActivity, it.data?.token?: " ")
                        startActivity<MainActivity>()
                        finish()
                    }
//                if(response.isSuccessful){
//                    if(response.body()!!.status == 200){
//
//                        SharedPreferenceController.setAuthorization(applicationContext, response.body()!!.data!!.token)
//                        startActivity<MainActivity>()
//                    }
//                }
            }
        })

    }

    private fun isValid(u_id: String, u_pw: String): Boolean {
        if (u_id == "") {
            rl_login_act_email.requestFocus()
            img_login_act_email_off.requestFocus()
        } else if (u_pw == "") {
            rl_login_act_password.requestFocus()
            img_login_act_password_off.requestFocus()
        } else return true
        return false
    }

    private fun setTextChangedListenerEmail() {
        edt_login_act_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.length != 0) {
                    rl_login_act_email.setBackgroundResource(R.drawable.primary_border)
                    img_login_act_email_off.setImageResource(R.drawable.login_id_on_icon)
                } else {
                    rl_login_act_email.setBackgroundResource(R.drawable.gray_border)
                    img_login_act_email_off.setImageResource(R.drawable.login_id_off_icon)
                }
            }
        })
    }

    private fun setTextChangedListenerPassword() {
        edt_login_act_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.length != 0) {
                    rl_login_act_password.setBackgroundResource(R.drawable.primary_border)
                    img_login_act_password_off.setImageResource(R.drawable.login_password_on_icon)
                } else {
                    rl_login_act_password.setBackgroundResource(R.drawable.gray_border)
                    img_login_act_password_off.setImageResource(R.drawable.login_password_off_icon)
                }
            }
        })
    }

}

