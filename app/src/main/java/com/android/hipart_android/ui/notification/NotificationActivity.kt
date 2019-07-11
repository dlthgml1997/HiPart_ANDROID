package com.android.hipart_android.ui.notification

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
import kotlinx.android.synthetic.main.activity_notification.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationActivity : BaseActivity() {
    lateinit var notificationOverviewRecyclerViewAdapter: NotificationOverviewRecyclerViewAdapter
    val dataList: ArrayList<MyNotificationData> by lazy {
        ArrayList<MyNotificationData>()
    }
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        getNotificationResponse()

        configureTitleBar()
    }

    private fun setRecyclerView(dataList : ArrayList<MyNotificationData>) {
        notificationOverviewRecyclerViewAdapter = NotificationOverviewRecyclerViewAdapter(this, dataList)
        rv_notification_overview_list.adapter = notificationOverviewRecyclerViewAdapter
        rv_notification_overview_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    private fun getNotificationResponse() {
        val getNotificationResponse = networkService.getNotificationResponse(
            "application/json", SharedPreferenceController.getAuthorization(this@NotificationActivity)
        )
        getNotificationResponse.enqueue(object : Callback<GetNotificationResponse> {

            override fun onFailure(call: Call<GetNotificationResponse>, t: Throwable) {
                Log.e("MyPageFragment Fail", t.toString())
            }

            override fun onResponse(call: Call<GetNotificationResponse>, response: Response<GetNotificationResponse>) {

                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.data
                    ?.let {
                        if (it.isNullOrEmpty()) {
                            layout_notification_overview_no_result.visibility = View.VISIBLE
                            rv_notification_overview_list.visibility = View.GONE
                        } else {
                            setRecyclerView(it)
                        }
                    }
            }
//                if (response.isSuccessful) {
//                    if (response.body()!!.message == "조회 성공") {
//                        if (response.body()!!.data[0].type == 0)
//                        {txt_notification_nomessage.visibility = View.VISIBLE}
//                        else {
//                            val tmp: ArrayList<MyNotificationData> = response.body()!!.data
//                            notificationOverviewRecyclerViewAdapter.dataList.addAll(tmp)
//                            notificationOverviewRecyclerViewAdapter.notifyDataSetChanged()
//                        }
//                    }
//                }
        })
    }

    private fun configureTitleBar() {
        btn_toolbar_notification_back.setOnClickListener {
            finish()
        }
    }
}
