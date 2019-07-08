package com.android.hipart_android.ui.notification

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.android.hipart_android.R
import com.android.hipart_android.util.BaseActivity
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : BaseActivity() {

    lateinit var notificationOverviewRecyclerViewAdapter:NotificationOverviewRecyclerViewAdapter
    var product_id: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        configureTitleBar()
        configureRecyclerView()
    }

    private fun configureTitleBar(){
        btn_toolbar_notification_back.setOnClickListener {
            finish()
        }
    }

    //통신과 연결할 부분!
    private fun configureRecyclerView() {
        var dataList: ArrayList<NotificationOverviewData> = ArrayList()
        dataList.add(
            NotificationOverviewData(
                product_id, "누군가가 하이파이브를 했습니다!", "19/07/05 15:35:45"))
        dataList.add(
            NotificationOverviewData(
                product_id, "하이팟 출시된 지 한 달 되었습니다! 출석 시 30코인을 제공합니다~",  "19/07/03 15:25:15"))

        notificationOverviewRecyclerViewAdapter = NotificationOverviewRecyclerViewAdapter(this, dataList)
        rv_notification_overview_list.adapter = notificationOverviewRecyclerViewAdapter
        rv_notification_overview_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}
