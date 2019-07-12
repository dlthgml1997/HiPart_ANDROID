package com.android.hipart_android.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.network.NetworkService
import com.android.hipart_android.ui.login.data.PostLoginRequest
import com.android.hipart_android.ui.login.data.PostLoginResponse
import com.android.hipart_android.ui.login.data.RefreshToken
import com.android.hipart_android.ui.login.data.RefreshTokenResponse
import com.android.hipart_android.ui.main.MainActivity
import com.android.hipart_android.ui.signup.SignupActivity
import com.android.hipart_android.util.BaseActivity
import com.android.hipart_android.util.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_login.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity(), KeyboardVisibilityEventListener {
    override fun onVisibilityChanged(isOpen: Boolean) {
        if(isOpen) {
            ll_login_act_sign_in.visibility = View.GONE
            sv_login_act.scrollTo(0, sv_login_act.bottom)
        }else{

            ll_login_act_sign_in.visibility = View.VISIBLE
            sv_login_act.scrollTo(0, sv_login_act.top)
        }
    }

    val REQUEST_CODE_LOGIN_ACTIVITY = 1000

    val networkService: NetworkService by lazy{
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        KeyboardVisibilityEvent.setEventListener(this, this)

        setAutoLogin()
        setOnFocusChangeListener()
        setTextChangedListenerEmail()
        setTextChangedListenerPassword()
        setOnBtnClickListener()
        refreshToken("chI2McxQf89Y9krDcXCKBkEnAkzQ1Yc7L56voJc7pa20vC8LDnbNZymPsSfex6zYtnSfc93GKCNqbXjDujkU5cvaAJRAoUwR6Gzr62XRxEQZ6A5NAlxEpWN4sRFpn5VlMWD3zAC7HJStjXtLjhPr8GOfW77W4KiyRiHGU13CWurDWHHPeaIzbd9Wqzg1pqFq2uwNkqF99smCCuIgVkC1lSNeMNBQhPG6jCEjXRh0uIUcLdSOSHXYqp4ni5SeoTWM")
    }

    private fun setAutoLogin() {
        if (SharedPreferenceController.getAuthorization(this).isNotEmpty()) {
            startActivity<MainActivity>()
            finish()
        }
    }

    // edt 클릭 시 포커스 주기
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
        // 로그인 버튼 클릭 시
        btn_login_act_submit.setOnClickListener {
            val user_email = edt_login_act_email.text.toString()
            val user_pw: String = edt_login_act_password.text.toString()
            // 유효성 검사 후 로그인 통신
            if (isValid(user_email, user_pw)) {
                postLoginResponse(user_email, user_pw)
            }
        }
        // 회원가입 클릭 시
        txt_login_act_signup.setOnClickListener {
            startActivity<SignupActivity>()
        }
    }

    //리프레시 토큰
    private fun refreshToken(refreshToken : String){
        val getRefreshToken: Call<RefreshTokenResponse> =
            networkService.getRefreshToken("application/json",
                SharedPreferenceController.getAuthorization((this@LoginActivity)))
        getRefreshToken.enqueue(object: Callback<RefreshTokenResponse>{
            override fun onFailure(call: Call<RefreshTokenResponse>, t: Throwable) {
                Log.e("Refresh token failed", t.toString())

            }

            override fun onResponse(
                call: Call<RefreshTokenResponse>, response: Response<RefreshTokenResponse>) {
                Log.v("LoginActivity", response.toString())

                Log.v("LoginActivity", response!!.body()!!.toString())

                Log.v("LoginActivity", response!!.body()!!.message)

                if(response.isSuccessful){
                    if(response.body()!!.status == 200){
                        val tmp: RefreshToken = response!!.body()!!.data
                    }
                }
            }
        })
    }
    private fun postLoginResponse(user_email: String, user_pw: String) {

        val postLoginResponse: Call<PostLoginResponse> =
            networkService.postLoginResponse("application/json", PostLoginRequest(user_email, user_pw))
        postLoginResponse.enqueue(object: Callback<PostLoginResponse> {
            override fun onFailure(call: Call<PostLoginResponse>, t: Throwable){
                Log.e("Login failed", t.toString())
            }

            override fun onResponse(call: Call<PostLoginResponse>, response: Response<PostLoginResponse>) {
                // 로그인 성공
                response?.takeIf { it.isSuccessful }
                    ?.body()?.takeIf { it.message == "로그인 성공" }
                    ?.let {
                        SharedPreferenceController.clearSPC(this@LoginActivity)
                        SharedPreferenceController.setAuthorization(this@LoginActivity, it.data?.tokens?.token)
                        SharedPreferenceController.setUserType(this@LoginActivity, it.data.user_type)
                        startActivity<MainActivity>()
                        finish()
                    }

                // 로그인 실패
                response?.takeIf { it.isSuccessful }
                    ?.body()?.takeIf { it.message == "ID 혹은 비밀번호가 일치하지 않습니다" }
                    ?.let {
                        toast("E-mail 혹은 비밀번호가 일치하지 않습니다")
                    }
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

