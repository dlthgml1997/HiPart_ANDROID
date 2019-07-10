package com.android.hipart_android.ui.mypick

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.network.NetworkService
import com.android.hipart_android.ui.hipat.adapter.PortFolioRecyclerViewAdapter
import com.android.hipart_android.ui.mypick.data.GetMyPickData
import com.android.hipart_android.ui.mypick.data.GetMyPickResponse
import com.android.hipart_android.util.BaseActivity
import com.android.hipart_android.util.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_mypick.*
import kotlinx.android.synthetic.main.toolbar_mypick.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPickActivity : BaseActivity(), View.OnClickListener {

    private val networkService:NetworkService by lazy{
        ApplicationController.instance.networkService
    }

    private val getTokenResponse by lazy{
        SharedPreferenceController.getAuthorization(this@MyPickActivity)
    }
    override fun onClick(v: View?) {
        when(v){
            btn_my_pick_back -> {
                finish()
            }
        }
    }

    val dataList by lazy {
        ArrayList<GetMyPickData>()
    }
    //var dataList: ArrayList<PortFolioData> = ArrayList()
    lateinit var portFolioRecyclerViewAdapter: PortFolioRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypick)
        getMyPickResponse()
        configureRecyclerView()
        setOnClickListener()
    }

    private fun configureRecyclerView() {
        portFolioRecyclerViewAdapter= PortFolioRecyclerViewAdapter(this, dataList , false)
        rv_mypick_act_port.adapter = portFolioRecyclerViewAdapter
        rv_mypick_act_port.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)

    }
    fun getMyPickResponse(){
        val getMyPickResponse = networkService.getMyPickResponse("getTokenResponse")
        getMyPickResponse.enqueue(object: Callback<GetMyPickResponse>{
            override fun onFailure(call: Call<GetMyPickResponse>, t: Throwable) {
                Log.e("MyPickAct Err", Log.getStackTraceString(t))
            }

            override fun onResponse(call: Call<GetMyPickResponse>, response: Response<GetMyPickResponse>) {
                 response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        portFolioRecyclerViewAdapter.dataList = it.data
                        portFolioRecyclerViewAdapter.notifyDataSetChanged()
                    }
            }
        })

    }
    fun setAnimPickIcon() {
        val interpolator = MyBounceInterpolator(0.2, 20.0)
        val anim: Animation = AnimationUtils.loadAnimation(this@MyPickActivity, R.anim.expand_anim)
        anim.interpolator = interpolator

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                rl_mypick_act_anim.visibility = View.GONE
            }

            override fun onAnimationStart(animation: Animation?) {
                rl_mypick_act_anim.visibility = View.VISIBLE
            }
        })
        rl_mypick_act_anim.startAnimation(anim)
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

    private fun setOnClickListener() {
        btn_my_pick_back.setOnClickListener(this)
    }
}
