package com.android.hipart_android.ui.hipat.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.hipart_android.R
import com.android.hipart_android.ui.hipat.adapter.HipatFragPortRecyclerViewAdapter
import com.android.hipart_android.ui.hipat.data.HipatFragPortFolioData
import kotlinx.android.synthetic.main.fragment_hipat_all.*

class AllHipatFragment : Fragment() {

    lateinit var hipatFragPortRecyclerViewAdapter: HipatFragPortRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hipat_all, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureRecyclerView()
    }

    private fun configureRecyclerView() {

        val dataListHipatFrag: ArrayList<HipatFragPortFolioData> = ArrayList()

        dataListHipatFrag.add(
            HipatFragPortFolioData(
                "default","NaDo","크리에이터",true,224,"안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)",3
            )
        )
        dataListHipatFrag.add(
            HipatFragPortFolioData(
                "default","NaDo","에디터",true,224,"안녕 모든 것들을 먹으며 방송하고 있어! 감사 :)",3
            )
        )
        dataListHipatFrag.add(
            HipatFragPortFolioData(
                "default","NaDo","트랜슬레이터",true,224,"안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)",3
            )
        )
        dataListHipatFrag.add(
            HipatFragPortFolioData(
                "default","NaDo","크리에이터",true,224,"안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)",3
            )
        )
        dataListHipatFrag.add(
            HipatFragPortFolioData(
                "default","NaDo","크리에이터",true,224,"안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)",3
            )
        )
        dataListHipatFrag.add(
            HipatFragPortFolioData(
                "default","NaDo","크리에이터",true,224,"안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)",3
            )
        )

        hipatFragPortRecyclerViewAdapter = HipatFragPortRecyclerViewAdapter(context!!, dataListHipatFrag)
        rv_hipat_all_frag.adapter = hipatFragPortRecyclerViewAdapter
        rv_hipat_all_frag.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)


    }


}
