package com.android.hipart_android.ui.hipart

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.ui.hipart.get.*
import com.android.hipart_android.util.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class HipartDetailActivity : BaseActivity() {
    private val TAG = "HipartDetailActivity"

    lateinit var userDetailData: UserDetailEEtcData
    lateinit var userDetailTData: UserDetailTData


    private val networkService = ApplicationController.instance.networkService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hipart_detail)

        //여기 숫자에 인텐트로 받아온 유저의 user_nickname, user_type
        filterUser("cuteyang", 1)

    }

    private fun filterUser(nickname: String, type: Int) {
        when (type) {
            1 -> getDetailCResponse(nickname)
            2 -> getDetailEEtcResponse(
                nickname)
            3 -> getDetailTResponse(nickname)
        }
    }

    private fun getDetailCResponse(nickname: String) {
        val getDetailCResponse = networkService.getDetailCResponse(
            "application/json",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6Iuq4sO2DgCIsImlkeCI6NiwidHlwZSI6NCwiaWF0IjoxNTYyNTY2OTgwLCJleHAiOjE1NjM3NzY1ODAsImlzcyI6ImlnIn0.Q2x2Z6OKdAs78ExzZk5zZvRNfsu9lL3Av3WJ05XB74g",
            nickname
        )

        getDetailCResponse.enqueue(object : Callback<GetDetailCResponse> {
            override fun onFailure(call: Call<GetDetailCResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<GetDetailCResponse>, response: Response<GetDetailCResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        Log.d(TAG, response.body()!!.message)
                        if (response.body()?.data != null) {
                            var userData: DetailCData? = response.body()!!.data
                            var userDetailCData : UserDetailCData = userData!!.resData
                            setFragment(HipartDetailCpatFragment(), userDetailCData)

                        }
                    }
                }
            }
        })

    }

    private fun getDetailEEtcResponse(nickname: String) {
        val getDetailEETcResponse = networkService.getDetailEEtcResponse(
            "application/json",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6Iuq4sO2DgCIsImlkeCI6NiwidHlwZSI6NCwiaWF0IjoxNTYyNTY2OTgwLCJleHAiOjE1NjM3NzY1ODAsImlzcyI6ImlnIn0.Q2x2Z6OKdAs78ExzZk5zZvRNfsu9lL3Av3WJ05XB74g",
            nickname
        )

        getDetailEETcResponse.enqueue(object : Callback<GetDetailEEtcResponse> {
            override fun onFailure(call: Call<GetDetailEEtcResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<GetDetailEEtcResponse>, response: Response<GetDetailEEtcResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        Log.d(TAG, response.body()!!.message)
                        if (response.body()?.data != null) {
                            var userData: DetailEEtcData = response.body()!!.data
                            userDetailData = userData.resData
                            Log.d(TAG, userDetailData.user_nickname)

                            setFragment(HipartDetailEEtcFragment(), userDetailData)

                        }
                    }
                }
            }
        })

    }

    private fun getDetailTResponse(nickname: String) {
        val getDetailTResponse = networkService.getDetailTResponse(
            "application/json",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6Iuq4sO2DgCIsImlkeCI6NiwidHlwZSI6NCwiaWF0IjoxNTYyNTY2OTgwLCJleHAiOjE1NjM3NzY1ODAsImlzcyI6ImlnIn0.Q2x2Z6OKdAs78ExzZk5zZvRNfsu9lL3Av3WJ05XB74g",
            nickname
        )

        getDetailTResponse.enqueue(object : Callback<GetDetailTResponse> {
            override fun onFailure(call: Call<GetDetailTResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<GetDetailTResponse>, response: Response<GetDetailTResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        Log.d(TAG, response.body()!!.message)
                        if (response.body()?.data != null) {
                            var userData: DetailTData = response.body()!!.data
                            userDetailTData = userData.resData
                            Log.d(TAG, userDetailTData.user_nickname)

                            setFragment(HipartDetailTpatFragment(), userDetailTData)


                        }
                    }
                }
            }
        })

    }


    private fun setFragment(fragment: Fragment, userData : Any?) {

        val bundle = Bundle()
        bundle.putSerializable("user", userData as Serializable)
        fragment.arguments = bundle

        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(R.id.fl_hip_detail_act, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()


    }


    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count == 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
/*
for(Fragment fragment : fragmentList){
       if(fragment instanceof OnBackPressedListener){
           ((OnBackPressedListener)fragment).onBackPressed();
       }
    }
 */


}
