package com.android.hipart_android.ui.hipat.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.hipart_android.R
import com.android.hipart_android.ui.hipat.adapter.PortFolioRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_hipat_etc.*
import com.android.hipart_android.ui.mypick.data.GetMyPickData

class EtcHiPatFragment : Fragment() {

    lateinit var portFolioRecyclerViewAdapter: PortFolioRecyclerViewAdapter

    val dataList by lazy {
        //portfoliodata
        ArrayList<GetMyPickData>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hipat_etc, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureRecyclerView()
    }

    private fun configureRecyclerView() {

        portFolioRecyclerViewAdapter = PortFolioRecyclerViewAdapter(context!!, dataList, true)
        rv_hipat_etc_frag.adapter = portFolioRecyclerViewAdapter
        rv_hipat_etc_frag.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)


    }


}
