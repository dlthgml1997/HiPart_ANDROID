package com.android.hipart_android.ui.hipat.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.network.NetworkService
import com.android.hipart_android.ui.hipat.adapter.PortFolioRecyclerViewAdapter
import com.android.hipart_android.ui.hipat.data.GetProfileLookUpResponse
import com.android.hipart_android.ui.mypick.data.GetMyPickData
import com.android.hipart_android.util.SharedPreferenceController
import kotlinx.android.synthetic.main.fragment_hipat_tpat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TPatHiPatFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_hipat_tpat, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        portFolioRecyclerViewAdapter = PortFolioRecyclerViewAdapter(context!!, dataList,true)
        rv_hipat_tpat_frag.adapter = portFolioRecyclerViewAdapter
        rv_hipat_tpat_frag.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
         getProfileLookUp(3)
    }

    fun getProfileLookUp(flag : Int){
        var networkService: NetworkService = ApplicationController.instance.networkService

        val getProfileLookUp = networkService.getProfileLookUp(
            SharedPreferenceController.getAuthorization(this@TPatHiPatFragment.context!!),
            flag
        )

        getProfileLookUp.enqueue(object: Callback<GetProfileLookUpResponse> {
            override fun onFailure(call: Call<GetProfileLookUpResponse>, t: Throwable) {
                Log.e("All HighPat Frag Err", Log.getStackTraceString(t))
            }

            override fun onResponse(
                call: Call<GetProfileLookUpResponse>,
                response: Response<GetProfileLookUpResponse>
            ) {
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.takeIf { it.message == "조회 성공" }
                    ?.data
                    ?.let {
                        portFolioRecyclerViewAdapter.dataList = it
                        portFolioRecyclerViewAdapter.notifyDataSetChanged()
                    }
            }
        })
    }
//    fun setFilterData(flag: Int){
//        when(flag){
//            1,2,3,4,5,6,7 -> {
//                portFolioRecyclerViewAdapter.dataList.filter { it.info[0].concept == flag }
//                portFolioRecyclerViewAdapter.notifyDataSetChanged()
//            }
//            8, 9 -> {
//                portFolioRecyclerViewAdapter.dataList.filter { it.info[0].pd == flag - 7 }
//                portFolioRecyclerViewAdapter.notifyDataSetChanged()
//            }
//            10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 -> {
//                portFolioRecyclerViewAdapter.dataList.filter { it.info[0].lang == flag - 9 }
//                portFolioRecyclerViewAdapter.notifyDataSetChanged()
//            }
//            21, 22, 23, 24, 25, 26 -> {
//                portFolioRecyclerViewAdapter.dataList.filter { it.info[0].etc == flag - 20 }
//                portFolioRecyclerViewAdapter.notifyDataSetChanged()
//            }
//        }
//    }
    fun setAdapterData(adapterDataList: List<GetMyPickData>) {
        portFolioRecyclerViewAdapter.dataList = adapterDataList
        portFolioRecyclerViewAdapter.notifyDataSetChanged()
    }
}
