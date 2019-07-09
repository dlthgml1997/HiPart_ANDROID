package com.android.hipart_android.ui.notification

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.network.NetworkService
import com.android.hipart_android.ui.notification.get.GetNotificationResponse
import com.android.hipart_android.ui.notification.get.MyNotificationData
import com.android.hipart_android.util.BaseActivity
import com.android.hipart_android.util.SharedPreferenceController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_notification.*
import org.jetbrains.anko.support.v4.ctx
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationActivity : BaseActivity() {
    lateinit var notificationOverviewRecyclerViewAdapter:NotificationOverviewRecyclerViewAdapter
    val dataList: ArrayList<MyNotificationData> by lazy {
        ArrayList<MyNotificationData>()
    }
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        Log.v("성청하11", "성청하11")
        setRecyclerView()
        Log.v("성청하22", "성청하22")
        getNotificationResponse()
        Log.v("성청하33", "성청하33")
        configureTitleBar()
    }

    private fun setRecyclerView() {
        notificationOverviewRecyclerViewAdapter = NotificationOverviewRecyclerViewAdapter(this, dataList)
        rv_notification_overview_list.adapter = notificationOverviewRecyclerViewAdapter
        rv_notification_overview_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    private fun getNotificationResponse() {
        val getNotificationResponse = networkService.getNotificationResponse(
            "application/json", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6ImN1dGV5YW5nIiwiaWR4IjozLCJ0eXBlIjoxLCJpYXQiOjE1NjI2NjUwNTgsImV4cCI6MTU2Mzg3NDY1OCwiaXNzIjoiaWcifQ.KYoHfxadB0ZgXXjTctehDYvUwtWSnezZ3rK30Fu7ZOA")
            //SharedPreferenceController.getAuthorization(this@NotificationActivity))
        getNotificationResponse.enqueue(object : Callback<GetNotificationResponse> {

            // 통신 실패 시
            override fun onFailure(call: Call<GetNotificationResponse>, t: Throwable) {
                Log.e("MyPageFragment Fail", t.toString())
            }

            // 통신 성공 시
            override fun onResponse(call: Call<GetNotificationResponse>, response: Response<GetNotificationResponse>) {
                if (response.isSuccessful) {
                    Log.v("성청하", response.body()!!.toString())
                    if (response.body()!!.message == "조회 성공") {
                        Log.v("성청하1", response.body()!!.toString())
                        if (response.body()!!.data[0].type == 0)
                        {txt_notification_nomessage.visibility = View.VISIBLE}
                        else {
                            Log.v("성청하", response.body()!!.toString())
                            val tmp: ArrayList<MyNotificationData> = response.body()!!.data
                            Log.v("성청하", response.body()!!.toString())

                            notificationOverviewRecyclerViewAdapter.dataList.addAll(tmp)
                            Log.v("성청하", response.body()!!.toString())

                            notificationOverviewRecyclerViewAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }

    private fun configureTitleBar(){
        btn_toolbar_notification_back.setOnClickListener {
            finish()
        }
    }
}
