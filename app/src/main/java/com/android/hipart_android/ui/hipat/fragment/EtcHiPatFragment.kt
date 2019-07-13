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
import kotlinx.android.synthetic.main.fragment_hipat_etc.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

         getProfileLookUp(4)
    }

    fun getProfileLookUp(flag : Int){
        var networkService: NetworkService = ApplicationController.instance.networkService

        val getProfileLookUp = networkService.getProfileLookUp(
            SharedPreferenceController.getAuthorization(this@EtcHiPatFragment.context!!),
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
        if(adapterDataList.isEmpty()){
            frag_hipat_etc_no_result.visibility = View.VISIBLE
            rv_hipat_etc_frag.visibility = View.GONE

        }else{
            frag_hipat_etc_no_result.visibility = View.GONE
            rv_hipat_etc_frag.visibility = View.VISIBLE
            portFolioRecyclerViewAdapter.dataList = adapterDataList
            portFolioRecyclerViewAdapter.notifyDataSetChanged()
        }
    }


    fun setAdapterChangeData(nickName: String){
        if (portFolioRecyclerViewAdapter.dataList.find { it.info[0].user_nickname == nickName }?.pickState == 0) {
            portFolioRecyclerViewAdapter.dataList.find { it.info[0].user_nickname == nickName }?.let {
                it.pickState = 1
                it.info[0].pick = it.info[0].pick + 1
            }
        } else {
            portFolioRecyclerViewAdapter.dataList.find { it.info[0].user_nickname == nickName }?.let {
                it.pickState = 0
                it.info[0].pick = it.info[0].pick - 1
            }
        }
        portFolioRecyclerViewAdapter.notifyDataSetChanged()
    }
}
