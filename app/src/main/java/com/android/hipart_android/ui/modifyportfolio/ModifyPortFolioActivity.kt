package com.android.hipart_android.ui.modifyportfolio

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
import com.android.hipart_android.ui.modifyportfolio.adapter.CpatWorkRVAdapter
import com.android.hipart_android.ui.modifyportfolio.adapter.EpatWorkRVAdapter
import com.android.hipart_android.ui.modifyportfolio.data.FilterData
import com.android.hipart_android.ui.modifyportfolio.data.TransWorkData
import com.android.hipart_android.ui.modifyportfolio.get.*
import com.android.hipart_android.ui.portfolio.dialog.FilterDialog
import com.android.hipart_android.util.BaseActivity
import com.android.hipart_android.util.SharedPreferenceController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_modify_port_folio.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    lateinit var dataListCpat: GetModifyPortFolioDataCpat

    lateinit var dataListEpat: GetModifyPortFolioDataEpat

    private val filterRVAdapter by lazy {
        FilterRVAdapter(this@ModifyPortFolioActivity, filterDataList)
    }

    private val cpatWorkRVAdapter by lazy {
        CpatWorkRVAdapter(this@ModifyPortFolioActivity, dataListCpat)
    }
    private val epatWorkRVAdapter by lazy {
        EpatWorkRVAdapter(this@ModifyPortFolioActivity, dataListEpat)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_port_folio)

        initView()
    }

    private fun initView() {
        setFilterRVAdapter()
        getModifyPortFolioResponse()
        //setTransWorkRVAdapter()
        setOnClickListener()
        //setCpatWorkRVAdapter()
    }

    private fun getModifyPortFolioResponse() {
        val userType = SharedPreferenceController.getUserType(this@ModifyPortFolioActivity)
        Log.v("TAGGGG", userType.toString())
        when (2) {
            //C-PAT
            1 -> {
                getModifyPortFolioResponseCpat()
            }
            //E-PAT
            2 -> {
                getModifyPortFolioResponseEpat()
            }
            //T-PAT
            3 -> {

            }
            //ETC.
            4 -> {

            }
        }
    }

    //E-PAT 통신 - 에디터
    private fun getModifyPortFolioResponseEpat() {
        val getModifyPortFolioResponseEpat = networkService.getModifyPortFolioResponseEpat(
            "application/json","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6IuyXkOuUlO2EsCIsImlkeCI6NSwidHlwZSI6MiwiaWF0IjoxNTYyNTY2OTM5LCJleHAiOjE1NjM3NzY1MzksImlzcyI6ImlnIn0.C4c6ibbr_QtAi2vk_S3ZftqmxJ9X0-EK7s8pNieLI_E"
            //SharedPreferenceController.getAuthorization(this)
        )
        getModifyPortFolioResponseEpat.enqueue(object : Callback<GetModifyPortFolioResponseEpat> {
            override fun onFailure(call: Call<GetModifyPortFolioResponseEpat>, t: Throwable) {
                Log.e("ModifyPortFolioAct Fail", t.toString())
            }

            override fun onResponse(
                call: Call<GetModifyPortFolioResponseEpat>,
                response: Response<GetModifyPortFolioResponseEpat>
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

                        //유저 타입
                        tv_modify_port_folio_act_job.text = "에디터"

                        //하이파이브
                        edt_modify_port_act_hifive.hint = data.hifive.toString()

                        //소개
                        if (data.detailOneline != null)
                            edt_modify_port_act_detail_oneline.hint = data.detailOneline

                        //원해요
                        if (data.detailWant != null)
                            edt_modify_port_act_want.hint = data.detailWant

                        // 학력/자격증/수상/경력
                        ll_modify_port_appeal.visibility = View.VISIBLE
                        if(data.detailAppeal != null)
                            edt_modify_port_act_detail_oneline.hint = data.detailAppeal

                        //작품 리사이클러뷰 설정
                        val tmp: GetModifyPortFolioDataEpat? = response.body()!!.data
                        if (data.thumbnail != null) {
                            Log.v("dataList size", data.thumbnail.size.toString())
                            setEpatWorkRVAdapter(tmp!!)
                        }
                    }
                }
            }
        })
    }

    //C-PAT 통신 = 크리에이터
    private fun getModifyPortFolioResponseCpat() {
        val getModifyPortFolioResponseCpat = networkService.getModifyPortFolioResponseCpat(
            "application/json", SharedPreferenceController.getAuthorization(this)
        )
        getModifyPortFolioResponseCpat.enqueue(object : Callback<GetModifyPortFolioResponseCpat> {
            override fun onFailure(call: Call<GetModifyPortFolioResponseCpat>, t: Throwable) {
                Log.e("ModifyPortFolioAct Fail", t.toString())
            }

            override fun onResponse(
                call: Call<GetModifyPortFolioResponseCpat>,
                response: Response<GetModifyPortFolioResponseCpat>
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
                        if (data.detailOneline != null)
                            edt_modify_port_act_detail_oneline.hint = data.detailOneline

                        //원해요
                        if (data.detailWant != null)
                            edt_modify_port_act_want.hint = data.detailWant

                        //유저 타입
                        tv_modify_port_folio_act_job.text = "크리에이터"

                        //구독자
                        ll_modify_port_act_subscriber.visibility = View.VISIBLE
                        if (data.detailSubscriber != null)
                            edt_modify_port_act_subscriber.hint = data.detailSubscriber
                        else
                            edt_modify_port_act_subscriber.hint = "0"

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
                        val tmp: GetModifyPortFolioDataCpat? = response.body()!!.data
                        if (data.thumbnail != null) {
                            Log.v("dataListCpat size", data.thumbnail.size.toString())
                            setCpatWorkRVAdapter(tmp!!)
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

    //작품 리사이클러뷰 - 에디터
    private fun setEpatWorkRVAdapter(tmp: GetModifyPortFolioDataEpat) {
        dataListEpat = tmp
        if(dataListEpat.thumbnail.isNotEmpty()){
            rl_modify_port_folio_act_no_work.visibility = View.GONE
        }

        rv_modify_port_folio_act_work.adapter = epatWorkRVAdapter
        rv_modify_port_folio_act_work.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        epatWorkRVAdapter.dataList = tmp
        epatWorkRVAdapter.notifyDataSetChanged()

    }

    //작품 리사이클러뷰 - 크리에이터
    private fun setCpatWorkRVAdapter(tmp: GetModifyPortFolioDataCpat) {
        dataListCpat = tmp
        if (dataListCpat.thumbnail.isNotEmpty()) {
            rl_modify_port_folio_act_no_work.visibility = View.GONE
        }
        rv_modify_port_folio_act_work.adapter = cpatWorkRVAdapter
        rv_modify_port_folio_act_work.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        cpatWorkRVAdapter.dataList = tmp
        cpatWorkRVAdapter.notifyDataSetChanged()
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