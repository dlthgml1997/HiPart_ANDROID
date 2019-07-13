package com.android.hipart_android.ui.hipart

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.ui.hipart.fragment.HipartDetailCpatFragment
import com.android.hipart_android.ui.hipart.fragment.HipartDetailEEtcFragment
import com.android.hipart_android.ui.hipart.fragment.HipartDetailEtcFragment
import com.android.hipart_android.ui.hipart.fragment.HipartDetailTpatFragment
import com.android.hipart_android.ui.hipart.get.*
import com.android.hipart_android.util.BaseActivity
import com.android.hipart_android.util.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_hipart_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class HipartDetailActivity : BaseActivity() {

    companion object {
        var hifiveStatus = 0
    }

    private val TAG = "HipartDetailActivity"

    lateinit var userDetailData: UserDetailEEtcData
    lateinit var userDetailTData: UserDetailTData

    private val userID = ""
    private val userNickName = ""

    private val networkService = ApplicationController.instance.networkService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hipart_detail)

        //여기 숫자에 인텐트로 받아온 유저의 user_nickname, user_type
        filterUser(intent.getStringExtra("user_nickname"), intent.getIntExtra("user_type", 0))

    }

    private fun filterUser(nickname: String, type: Int) {
        when (type) {
            1 -> getDetailCResponse(nickname)
            2 -> getDetailEEtcResponse(nickname)
            3 -> getDetailTResponse(nickname)
            4 -> getDetailEtcResponse(nickname)
        }
    }

    private fun getDetailCResponse(nickname: String) {
        val getDetailCResponse = networkService.getDetailCResponse(
            "application/json",
            SharedPreferenceController.getAuthorization(this),
            nickname
        )

        getDetailCResponse.enqueue(object : Callback<GetDetailCResponse> {
            override fun onFailure(call: Call<GetDetailCResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<GetDetailCResponse>, response: Response<GetDetailCResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        if (response.body()?.data != null) {
                            var userData: DetailCData? = response.body()!!.data
                            hifiveStatus = userData!!.hifiveState

                            var userDetailCData: UserDetailCData = userData!!.resData
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
            SharedPreferenceController.getAuthorization(this),
            nickname
        )

        getDetailEETcResponse.enqueue(object : Callback<GetDetailEEtcResponse> {
            override fun onFailure(call: Call<GetDetailEEtcResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<GetDetailEEtcResponse>, response: Response<GetDetailEEtcResponse>) {
                Log.v("rrraerae", response.body().toString())
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.data
                    ?.let {
                        var userData: DetailEEtcData = response.body()!!.data
                        hifiveStatus = userData!!.hifiveState
                        userDetailData = userData.resData
                        setFragment(HipartDetailEEtcFragment(), userDetailData)
                    }
//                if (response.isSuccessful) {
//                    if (response.body()!!.status == 200) {
//                        if (response.body()?.data != null) {
//                            var userData: DetailEEtcData = response.body()!!.data
//                            hifiveStatus = userData!!.hifiveState
//                            userDetailData = userData.resData
//                            setFragment(HipartDetailEEtcFragment(), userDetailData)
//                        }
//                    }
//                }
            }
        })
    }

    private fun getDetailEtcResponse(nickname: String) {
        val getDetailETcResponse = networkService.getDetailEtcResponse(
            "application/json",
            SharedPreferenceController.getAuthorization(this),
            nickname
        )

        getDetailETcResponse.enqueue(object : Callback<GetDetailEEtcResponse> {
            override fun onFailure(call: Call<GetDetailEEtcResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<GetDetailEEtcResponse>, response: Response<GetDetailEEtcResponse>) {
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.data
                    ?.let {
                        var userData: DetailEEtcData = response.body()!!.data
                        hifiveStatus = userData!!.hifiveState
                        userDetailData = userData.resData
                        setFragment(HipartDetailEtcFragment(), userDetailData)
                    }
//                if (response.isSuccessful) {
//                    if (response.body()!!.status == 200) {
//                        if (response.body()?.data != null) {
//                            var userData: DetailEEtcData = response.body()!!.data
//                            hifiveStatus = userData!!.hifiveState
//                            userDetailData = userData.resData
//                            setFragment(HipartDetailEEtcFragment(), userDetailData)
//                        }
//                    }
//                }
            }
        })
    }

    private fun getDetailTResponse(nickname: String) {
        val getDetailTResponse = networkService.getDetailTResponse(
            "application/json",
            SharedPreferenceController.getAuthorization(this),
            nickname
        )

        getDetailTResponse.enqueue(object : Callback<GetDetailTResponse> {
            override fun onFailure(call: Call<GetDetailTResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<GetDetailTResponse>, response: Response<GetDetailTResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        if (response.body()?.data != null) {
                            var userData: DetailTData = response.body()!!.data
                            hifiveStatus = userData!!.hifiveState

                            userDetailTData = userData.resData
                            Log.d(TAG, userDetailTData.user_nickname)

                            setFragment(HipartDetailTpatFragment(), userDetailTData)
                        }
                    }
                }
            }
        })

    }


    private fun setFragment(fragment: Fragment, userData: Any?) {

        val bundle = Bundle()
        bundle.putSerializable("user", userData as Serializable)
        fragment.arguments = bundle

        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(R.id.fl_hip_detail_act, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()


    }

    fun addFragment(frameLayoutId: Int, fragment: Fragment, nickname: String, type: Int) {

        val bundle = Bundle()
        bundle.putString("nickname", nickname)
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .add(frameLayoutId, fragment)
            .commit()
    }


    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count == 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    fun setAnimPickIcon() {
        val interpolator = MyBounceInterpolator(0.2, 20.0)
        val anim: Animation = AnimationUtils.loadAnimation(this@HipartDetailActivity, R.anim.expand_anim)
        anim.interpolator = interpolator

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                rl_hipart_detail_act_anim.visibility = View.GONE
            }

            override fun onAnimationStart(animation: Animation?) {
                rl_hipart_detail_act_anim.visibility = View.VISIBLE
            }
        })
        rl_hipart_detail_act_anim.startAnimation(anim)
    }

    internal inner class MyBounceInterpolator(amplitude: Double, frequency: Double) :
        android.view.animation.Interpolator {
        private var mAmplitude = 1.0
        private var mFrequency = 10.0

        init {
            mAmplitude = amplitude
            mFrequency = frequency
        }

        override fun getInterpolation(time: Float): Float {
            return (-1.0 * Math.pow(Math.E, -time / mAmplitude) *
                    Math.cos(mFrequency * time) + 1).toFloat()
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
