package com.android.hipart_android.ui.ad_manage_admin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.android.hipart_android.R
import com.android.hipart_android.ui.ad_add.AddAdActivity
import com.android.hipart_android.util.BaseActivity
import kotlinx.android.synthetic.main.activity_manage_ad.*
import kotlinx.android.synthetic.main.rv_item_manage_ad_act_advertisement.*
import org.jetbrains.anko.startActivity

class ManageAdActivity : BaseActivity() {

    lateinit var manageAdRecyclerViewAdapter:ManageAdRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_ad)

        configureTitleBar()
        configureRecyclerView()
    }

    private fun configureTitleBar(){
        // 광고 관리 툴바 뒤로 가기 버튼
        btn_toolbar_manage_ad_back.setOnClickListener {
            finish()
        }
        // 광고 관리 툴바 추가 버튼
        btn_toolbar_manage_ad_add.setOnClickListener {
            startActivity<AddAdActivity>()
        }

    }

    //통신과 연결할 부분!
    private fun configureRecyclerView() {
        var dataList: ArrayList<ManageAdData> = ArrayList()
        dataList.add(
            ManageAdData(
                "http://www.reputation.kr/news/photo/201804/111_232_1532.jpg", "롯데리아 더블 시리즈", "먹방 크리에이터를 위한 롯데리아"))
        dataList.add(
            ManageAdData(
                "http://nimage.globaleconomic.co.kr/phpwas/restmb_allidxmake.php?idx=5&simg=2019061115360708474aca1a8c21a11221414971.jpg", "롯데리아 더블 시리즈", "먹방 크리에이터를 위한 롯데리아"))
        dataList.add(
            ManageAdData(
                "http://www.reputation.kr/news/photo/201804/111_232_1532.jpg", "롯데리아 더블 시리즈", "먹방 크리에이터를 위한 롯데리아"))
        dataList.add(
            ManageAdData(
                "http://www.reputation.kr/news/photo/201804/111_232_1532.jpg", "롯데리아 더블 시리즈", "먹방 크리에이터를 위한 롯데리아"))
        dataList.add(
            ManageAdData(
                "http://www.reputation.kr/news/photo/201804/111_232_1532.jpg", "롯데리아 더블 시리즈", "먹방 크리에이터를 위한 롯데리아"))
        dataList.add(
            ManageAdData(
                "https://blogholic.com/wp-content/uploads/2018/05/%EB%A1%AF%EB%8D%B0%EB%A6%AC%EC%95%8402.png", "롯데리아 더블 시리즈", "먹방 크리에이터를 위한 롯데리아"))

        manageAdRecyclerViewAdapter = ManageAdRecyclerViewAdapter(this, dataList)
        rv_manage_ad_overview_list.adapter = manageAdRecyclerViewAdapter
        rv_manage_ad_overview_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}
