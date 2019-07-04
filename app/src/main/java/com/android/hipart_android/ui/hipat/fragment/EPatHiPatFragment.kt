package com.android.hipart_android.ui.hipat.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.hipart_android.R
import com.android.hipart_android.ui.hipat.adapter.PortFolioRecyclerViewAdapter
import com.android.hipart_android.ui.hipat.data.PortFolioData
import kotlinx.android.synthetic.main.fragment_hipat_epat.*

class EPatHiPatFragment : Fragment() {

    lateinit var portFolioRecyclerViewAdapter: PortFolioRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hipat_epat, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureRecyclerView()
    }

    private fun configureRecyclerView() {

        val dataList: ArrayList<PortFolioData> = ArrayList()

        dataList.add(
            PortFolioData(
                "default", "NaDo", "에디터", true, 224, "안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)", 3
            )
        )
        dataList.add(
            PortFolioData(
                "default", "NaDo", "에디터", true, 224, "안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)", 3
            )
        )
        dataList.add(
            PortFolioData(
                "default", "NaDo", "에디터", true, 224, "안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)", 3
            )
        )
        dataList.add(
            PortFolioData(
                "default", "NaDo", "에디터", true, 224, "안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)", 3
            )
        )
        dataList.add(
            PortFolioData(
                "default", "NaDo", "에디터", true, 224, "안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)", 3
            )
        )
        dataList.add(
            PortFolioData(
                "default", "NaDo", "에디터", true, 224, "안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)", 3
            )
        )

        portFolioRecyclerViewAdapter = PortFolioRecyclerViewAdapter(context!!, dataList)
        rv_hipat_epat_frag.adapter = portFolioRecyclerViewAdapter
        rv_hipat_epat_frag.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
    }
}
