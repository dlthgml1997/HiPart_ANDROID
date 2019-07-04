package com.android.hipart_android.ui.mypage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.android.hipart_android.R
import com.android.hipart_android.ui.hipat.adapter.PortFolioRecyclerViewAdapter
import com.android.hipart_android.ui.hipat.data.PortFolioData
import kotlinx.android.synthetic.main.activity_mypick.*

class MyPickActivity : AppCompatActivity() {

    val dataList by lazy {
        ArrayList<PortFolioData>()
    }

    lateinit var portFolioRecyclerViewAdapter: PortFolioRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypick)

        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        dataList.add(
            PortFolioData(
                "default", "NaDo", "크리에이터", true, 224, "안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)", 3
            )
        )
        dataList.add(
            PortFolioData(
                "default", "NaDo", "에디터", true, 224, "안녕 모든 것들을 먹으며 방송하고 있어! 감사 :)", 3
            )
        )
        dataList.add(
            PortFolioData(
                "default", "NaDo", "트랜슬레이터", true, 224, "안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)", 3
            )
        )
        dataList.add(
            PortFolioData(
                "default", "NaDo", "크리에이터", true, 224, "안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)", 3
            )
        )
        dataList.add(
            PortFolioData(
                "default", "NaDo", "크리에이터", true, 224, "안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)", 3
            )
        )
        dataList.add(
            PortFolioData(
                "default", "NaDo", "크리에이터", true, 224, "안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)", 3
            )
        )

        portFolioRecyclerViewAdapter= PortFolioRecyclerViewAdapter(this,dataList)
        rv_mypick_act_port.adapter = portFolioRecyclerViewAdapter
        rv_mypick_act_port.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
    }
}
