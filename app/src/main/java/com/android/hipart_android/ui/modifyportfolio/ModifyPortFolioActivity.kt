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
import com.bumptech.glide.Glide
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

    lateinit var workDateList : ArrayList<WorkData>

    private val workRVAdapter by lazy {
        WorkRVAdapter(this@ModifyPortFolioActivity, getModifyPortFolioDataList)
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

    lateinit var getModifyPortFolioDataList : ArrayList<GetModifyPortFolioData>

    var dataList: ArrayList<GetModifyPortFolioData> = ArrayList()

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
        val getModifyPortFolioResponse = networkService.getModifyPortFolioResponse(
            "application/json",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6ImN1dGV5YW5nIiwiaWR4IjozLCJ0eXBlIjoxLCJpYXQiOjE1NjI1NjcyNTgsImV4cCI6MTU2Mzc3Njg1OCwiaXNzIjoiaWcifQ.WHzr5l6RfzF3Uw88qUeuJe9rpLD4RHlsCB9pto-4MbM"
        )
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

                        val data = response.body()!!.data

                        Log.v("usertype TAGGG", data.userType.toString())

                        //유저 이미지
                        Glide.with(this@ModifyPortFolioActivity)
                            .load(data.userImg)
                            .into(iv_act_modify_port_folio_user)

                        //유저 이름
                        tv_modify_port_act_user_name.text = data.userNickname

                        //하이파이브
                        edt_modify_port_act_hifive.hint = data.hifive.toString()

                        //소개
                        edt_modify_port_act_detail_oneline.hint = data.detailOneline

                        //원해요
                        edt_modify_port_act_want.hint = data.detailWant

                        when (response.body()!!.data.userType) {
                            //C-PAT
                            1 -> {
                                //유저 타입
                                tv_modify_port_folio_act_job.text = "크리에이터"

                                //구독자
                                edt_modify_port_act_subscriber.hint = data.detailSubscriber.toString()

                                //플랫폼 초기 설정
                                rl_modify_port_act_pick_platform.visibility = View.VISIBLE
                                when (data.detailPlatform) {
                                    1 -> {
                                        btn_youtube.setImageResource(R.drawable.pofol_youtube_white_img)
                                        btn_afreeca.setImageResource(R.drawable.pofol_afreeca_white_off_img)
                                        btn_twich.setImageResource(R.drawable.pofol_twitch_white_off_img)
                                    }
                                    2 -> {
                                        btn_youtube.setImageResource(R.drawable.pofol_youtube_off_img)
                                        btn_afreeca.setImageResource(R.drawable.pofol_afreeca_white_img)
                                        btn_twich.setImageResource(R.drawable.pofol_twitch_white_off_img)
                                    }
                                    3 -> {
                                        btn_youtube.setImageResource(R.drawable.pofol_youtube_off_img)
                                        btn_afreeca.setImageResource(R.drawable.pofol_afreeca_white_off_img)
                                        btn_twich.setImageResource(R.drawable.pofol_twitch_white_img)
                                    }
                                }

                                //작품 리사이클러뷰 설정

                                setWorkRVAdapter(getModifyPortFolioDataList)
                            }
                            //E-PAT, ETC
                            2, 4 -> {
                                //유저 타입
                                tv_modify_port_folio_act_job.text = "편집자"
                            }
                            //T-PAT
                            3 -> {
                                //유저 타입
                                tv_modify_port_folio_act_job.text = "번역가"
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


    private fun setWorkRVAdapter(tmp : ArrayList<GetModifyPortFolioData>) {
        WorkRVAdapter.dataList = tmp
        productOverviewRecyclerViewAdapter.notifyDataSetChanged()

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