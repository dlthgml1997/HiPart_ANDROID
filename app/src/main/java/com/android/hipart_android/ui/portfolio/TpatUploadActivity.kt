package com.android.hipart_android.ui.portfolio

import android.os.Bundle
import android.util.Log
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.network.NetworkService
import com.android.hipart_android.ui.portfolio.data.PostPortfolioTransRequest
import com.android.hipart_android.ui.portfolio.data.PostPortfolioTransResponse
import com.android.hipart_android.util.BaseActivity
import com.android.hipart_android.util.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_upload_tpat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TpatUploadActivity : BaseActivity() {

    val networkService: NetworkService by lazy{
        ApplicationController.instance.networkService
    }
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
            val before = edt_tpat_upload_act_foreign.text.toString()
            val after: String = edt_tpat_upload_act_korean.text.toString()
            postPortfolioTransResponse(before, after)
            finish()
            /* *
            나중에 통신 연결 하기/
             */
        }
    }

    private fun postPortfolioTransResponse(before: String, after: String) {
        val networkService = ApplicationController.instance.networkService
        val postPortfolioTransResponse = networkService.postPortfolioTransResponse("application/json",
            SharedPreferenceController.getAuthorization(this@TpatUploadActivity),
            PostPortfolioTransRequest(before, after)
        )

        postPortfolioTransResponse.enqueue(object : Callback<PostPortfolioTransResponse> {

            override fun onFailure(call: Call<PostPortfolioTransResponse>, t: Throwable) {
                Log.e("DB 오류", Log.getStackTraceString(t))
            }

            override fun onResponse(call: Call<PostPortfolioTransResponse>, response: Response<PostPortfolioTransResponse>) {
                Log.v("qwejwqljqwil", response.toString())
                Log.v("qwejwqljqwil", response.body().toString())
                Log.v("qwejwqljqwil", response.body()!!.message.toString())


                response?.takeIf { it.isSuccessful }
                    ?.body()?.takeIf { it.message == "작품 등록 성공" }
                    ?.let {



                        //SharedPreferenceController.clearSPC(this@TpatUploadActivity)
                        //SharedPreferenceController.setAuthorization(this@TpatUploadActivity, it.data?.tokens?.token)
                        //SharedPreferenceController.setUserType(this@TpatUploadActivity, it.data.user_type)
                        finish()
                    }
            }
        })
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
