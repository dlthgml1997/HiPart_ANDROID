package com.android.hipart_android.ui.modifyportfolio

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.network.NetworkService
import com.android.hipart_android.ui.modifyportfolio.adapter.FilterRVAdapter
import com.android.hipart_android.ui.modifyportfolio.adapter.TransWorkRVAdapter
import com.android.hipart_android.ui.modifyportfolio.adapter.WorkRVAdapter
import com.android.hipart_android.ui.modifyportfolio.data.FilterData
import com.android.hipart_android.ui.modifyportfolio.data.TransWorkData
import com.android.hipart_android.ui.modifyportfolio.data.WorkData
import com.android.hipart_android.ui.modifyportfolio.get.GetModifyPortFolioData
import com.android.hipart_android.ui.modifyportfolio.get.GetModifyPortFolioResponse
import com.android.hipart_android.ui.portfolio.dialog.FilterDialog
import com.android.hipart_android.util.BaseActivity
import com.android.hipart_android.util.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_modify_port_folio.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class ModifyPortFolioActivity : BaseActivity(), View.OnClickListener {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun onClick(v: View?) {
        when (v) {

            btn_modify_port_folio_back -> {
                finish()
            }

            btn_youtube -> {
                btn_youtube.isSelected = !btn_youtube.isSelected
            }

            btn_afreeca -> {
                btn_afreeca.isSelected = !btn_afreeca.isSelected
            }

            btn_twich -> {
                btn_twich.isSelected = !btn_twich.isSelected
            }

            // 필터 수정하기
            btn_modify_port_folio_act_modify_filter -> {
                filterDialog.show(supportFragmentManager, "filter dialog")
            }

            // 작품 올리기
            btn_modify_port_folio_act_work_add_work -> {

            }

            // 취소하기
            btn_modify_port_folio_cancel -> {
                finish()
            }

            // 수정 완료
            btn_modify_port_folio_confirm -> {

            }
        }
    }

    private var filterDataList = ArrayList<FilterData>()

    private val filterRVAdapter by lazy {
        FilterRVAdapter(this@ModifyPortFolioActivity, filterDataList)
    }

    private val workDateList by lazy {
        ArrayList<WorkData>()
    }

    private val workRVAdapter by lazy {
        WorkRVAdapter(this@ModifyPortFolioActivity, workDateList)
    }

    private val transWorkDateList by lazy {
        ArrayList<TransWorkData>()
    }

    private val transWorkRVAdapter by lazy {
        TransWorkRVAdapter(this@ModifyPortFolioActivity, transWorkDateList)
    }

    private val filterDialog by lazy {
        FilterDialog()
    }

    lateinit var getModifyPortFolioData: GetModifyPortFolioData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_port_folio)

        initView()
    }

    private fun initView() {
        getModifyPortFolioResponse()
        setFilterRVAdapter()
        setTransWorkRVAdapter()
        setOnClickListener()
        // setWorkRVAdapter()
    }

    private fun getModifyPortFolioResponse() {
        val getModifyPortFolioResponse = networkService.getModifyPortFolioResponse("application/json","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6IuuLieuEpOyehCIsImlkeCI6MTIsInR5cGUiOjEsImlhdCI6MTU2MjUyMDUyNSwiZXhwIjoxNTYzNzMwMTI1LCJpc3MiOiJpZyJ9.jzDgQsmhkbl0fSSam8uOMfGhXVFN0B4eSU3-CZoHW_U")
        getModifyPortFolioResponse.enqueue(object : Callback<GetModifyPortFolioResponse> {
            override fun onFailure(call: Call<GetModifyPortFolioResponse>, t: Throwable) {
                Log.e("ModifyPortFolioAct Fail", t.toString())
            }

            override fun onResponse(
                call: Call<GetModifyPortFolioResponse>,
                response: Response<GetModifyPortFolioResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.message == "조회 성공") {
                        Log.v("usertype TAGGG",response.body()!!.data.userType.toString())
                        when(response.body()!!.data.userType)
                        {
                            //C-PAT
                            1-> {

                            }
                            //E-PAT
                            2-> {

                            }
                            //T-PAT
                            3->{

                            }
                            //ETC.
                            4->{

                            }
                        }
                    }
                }
            }
        })
    }

    private fun setFilterRVAdapter() {

        rv_modify_port_folio_act_filter.adapter = filterRVAdapter
        rv_modify_port_folio_act_filter.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }


    private fun setWorkRVAdapter() {
        workDateList.add(
            WorkData(
                "https://i.pinimg.com/236x/ae/c9/ea/aec9eadd89aa51a9b753b221f3bcce12.jpg"
                , "벤쯔의 먹방"
                , "많이먹어요~"
            )
        )

        workDateList.add(
            WorkData(
                "https://i.pinimg.com/236x/ae/c9/ea/aec9eadd89aa51a9b753b221f3bcce12.jpg"
                , "벤쯔의 먹방"
                , "많이먹어요~"
            )
        )

        workDateList.add(
            WorkData(
                "https://i.pinimg.com/236x/ae/c9/ea/aec9eadd89aa51a9b753b221f3bcce12.jpg"
                , "벤쯔의 먹방"
                , "많이먹어요~"
            )
        )

        workDateList.add(
            WorkData(
                "https://i.pinimg.com/236x/ae/c9/ea/aec9eadd89aa51a9b753b221f3bcce12.jpg"
                , "벤쯔의 먹방"
                , "많이먹어요~"
            )
        )

        workDateList.add(
            WorkData(
                "https://i.pinimg.com/236x/ae/c9/ea/aec9eadd89aa51a9b753b221f3bcce12.jpg"
                , "벤쯔의 먹방"
                , "많이먹어요~"
            )
        )

        workDateList.add(
            WorkData(
                "https://i.pinimg.com/236x/ae/c9/ea/aec9eadd89aa51a9b753b221f3bcce12.jpg"
                , "벤쯔의 먹방"
                , "많이먹어요~"
            )
        )

        workDateList.add(
            WorkData(
                "https://i.pinimg.com/236x/ae/c9/ea/aec9eadd89aa51a9b753b221f3bcce12.jpg"
                , "벤쯔의 먹방"
                , "많이먹어요~"
            )
        )

        rv_modify_port_folio_act_work.adapter = workRVAdapter
        rv_modify_port_folio_act_work.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setTransWorkRVAdapter() {
        transWorkDateList.add(
            TransWorkData("바뀌기 전 텍스트 입니당~", "바뀐후 텍스트 입니당")
        )
        transWorkDateList.add(
            TransWorkData("바뀌기 전 텍스트 입니당~", "바뀐후 텍스트 입니당")
        )
        transWorkDateList.add(
            TransWorkData("바뀌기 전 텍스트 입니당~", "바뀐후 텍스트 입니당")
        )
        transWorkDateList.add(
            TransWorkData("바뀌기 전 텍스트 입니당~", "바뀐후 텍스트 입니당")
        )
        transWorkDateList.add(
            TransWorkData("바뀌기 전 텍스트 입니당~", "바뀐후 텍스트 입니당")
        )

        rv_modify_port_folio_act_work.adapter = transWorkRVAdapter
        rv_modify_port_folio_act_work.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setOnClickListener() {
        btn_modify_port_folio_back.setOnClickListener(this)

        btn_youtube.setOnClickListener(this)

        btn_afreeca.setOnClickListener(this)

        btn_twich.setOnClickListener(this)

        // 필터 수정하기
        btn_modify_port_folio_act_modify_filter.setOnClickListener(this)

        // 작품 올리기
        btn_modify_port_folio_act_work_add_work.setOnClickListener(this)

        // 취소하기
        btn_modify_port_folio_cancel.setOnClickListener(this)

        // 수정 완료
        btn_modify_port_folio_confirm.setOnClickListener(this)

    }

    fun setFilterData(arrayList: ArrayList<FilterData>) {
        filterDataList = arrayList

        filterRVAdapter.changeDataList(filterDataList)

        filterRVAdapter.notifyDataSetChanged()

        Log.v("TAGGGGG", filterDataList.toString())
    }
}