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
import com.android.hipart_android.ui.modifyportfolio.adapter.EpatAndEtcWorkRVAdapter
import com.android.hipart_android.ui.modifyportfolio.data.FilterData
import com.android.hipart_android.ui.modifyportfolio.data.ModifyList
import com.android.hipart_android.ui.modifyportfolio.data.WorkIndex
import com.android.hipart_android.ui.modifyportfolio.delete.DeleteModifyPortFolioResponse
import com.android.hipart_android.ui.modifyportfolio.get.*
import com.android.hipart_android.ui.modifyportfolio.put.PutModifyPortFolioResponse
import com.android.hipart_android.ui.portfolio.dialog.FilterDialog
import com.android.hipart_android.util.BaseActivity
import com.android.hipart_android.util.SharedPreferenceController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_modify_port_folio.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

/**
 * userType
 * 1: 크리에이터
 * 2: 에디터
 * 3: 트랜슬레이터
 * 4: 기타
 */

/**
 * 토큰 다 SharedPreferenceController로 바꿔야함
 */
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
                //put
                putModifyPortFolioResponse()
                //delete
                deleteModifyPortResponse()
            }
        }
    }
    lateinit var modifyList: ModifyList

    private var filterDataList = ArrayList<FilterData>()

    private var removeIndexList = ArrayList<Int>()

    lateinit var dataListCpat: GetModifyPortFolioDataCpat

    lateinit var dataListEpatAndEtc: GetModifyPortFolioDataEpatAndEtc

    lateinit var dataListTpat: GetModifyPortFolioDataTpat


    private val filterRVAdapter by lazy {
        FilterRVAdapter(this@ModifyPortFolioActivity, filterDataList)
    }

    private val cpatWorkRVAdapter by lazy {
        CpatWorkRVAdapter(this@ModifyPortFolioActivity, dataListCpat)
    }
    private val epatWorkRVAdapter by lazy {
        EpatAndEtcWorkRVAdapter(this@ModifyPortFolioActivity, dataListEpatAndEtc)
    }
    private val transWorkRVAdapter by lazy {
        TransWorkRVAdapter(this@ModifyPortFolioActivity, dataListTpat)
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
        setOnClickListener()
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

    //통신
    private fun getModifyPortFolioResponse() {
        val userType = SharedPreferenceController.getUserType(this@ModifyPortFolioActivity)
        Log.v("TAGGGG", userType.toString())
        when (1) {
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
                getModifyPortFolioResponseTpat()
            }
            //ETC.
            4 -> {
                getModifyPortFolioResponseEtc()
            }
        }
    }

    //C-PAT 통신 = 크리에이터
    private fun getModifyPortFolioResponseCpat() {
        val getModifyPortFolioResponseCpat = networkService.getModifyPortFolioResponseCpat(
            "application/json",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6ImN1dGV5YW5nIiwiaWR4IjozLCJ0eXBlIjoxLCJpYXQiOjE1NjI1NjcyNTgsImV4cCI6MTU2Mzc3Njg1OCwiaXNzIjoiaWcifQ.WHzr5l6RfzF3Uw88qUeuJe9rpLD4RHlsCB9pto-4MbM"
            //SharedPreferenceController.getAuthorization(this)
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
                        tv_modify_port_folio_act_job.text = "creator"

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
                            Log.v("dataListCpat size", data.workIdx.toString())
                            setCpatWorkRVAdapter(tmp!!)
                        } else {
                            rl_modify_port_folio_act_no_work.visibility = View.VISIBLE
                        }
                    }
                }
            }
        })
    }

    //E-PAT 통신 - 에디터
    private fun getModifyPortFolioResponseEpat() {
        val getModifyPortFolioResponseEpat = networkService.getModifyPortFolioResponseEpat(
            "application/json",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6IuyXkOuUlO2EsCIsImlkeCI6NSwidHlwZSI6MiwiaWF0IjoxNTYyNTY2OTM5LCJleHAiOjE1NjM3NzY1MzksImlzcyI6ImlnIn0.C4c6ibbr_QtAi2vk_S3ZftqmxJ9X0-EK7s8pNieLI_E"
            //SharedPreferenceController.getAuthorization(this)
        )
        getModifyPortFolioResponseEpat.enqueue(object : Callback<GetModifyPortFolioResponseEpatAndEtc> {
            override fun onFailure(call: Call<GetModifyPortFolioResponseEpatAndEtc>, t: Throwable) {
                Log.e("ModifyPortFolioAct Fail", t.toString())
            }

            override fun onResponse(
                call: Call<GetModifyPortFolioResponseEpatAndEtc>,
                response: Response<GetModifyPortFolioResponseEpatAndEtc>
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
                        tv_modify_port_folio_act_job.text = "editor"

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
                        if (data.detailAppeal != null)
                            edt_modify_port_act_appeal.hint = data.detailAppeal

                        //작품 리사이클러뷰 설정
                        val tmp: GetModifyPortFolioDataEpatAndEtc? = response.body()!!.data
                        if (data.thumbnail != null) {
                            Log.v("dataList size", data.thumbnail.size.toString())
                            setEpatAndEtcWorkRVAdapter(tmp!!)
                        }
                    }
                }
            }
        })
    }

    //T-PAT 통신 - 트랜슬레이터
    private fun getModifyPortFolioResponseTpat() {
        val getModifyPortFolioResponseTpat = networkService.getModifyPortFolioResponseTpat(
            "application/json",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6InRyYW5zbWFuIiwiaWR4Ijo0LCJ0eXBlIjozLCJpYXQiOjE1NjI2NzU2MzIsImV4cCI6MTU2Mzg4NTIzMiwiaXNzIjoiaWcifQ.onbsHzwqnyFmOPcpiuKxt2KxQS0c1pc1FHYnGsR1Flo"
        )
        getModifyPortFolioResponseTpat.enqueue(object : Callback<GetModifyPortFolioResponseTpat> {
            override fun onFailure(call: Call<GetModifyPortFolioResponseTpat>, t: Throwable) {
                Log.e("TpatModify fail", t.toString())
            }

            override fun onResponse(
                call: Call<GetModifyPortFolioResponseTpat>,
                response: Response<GetModifyPortFolioResponseTpat>
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
                        tv_modify_port_folio_act_job.text = "translator"

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
                        if (data.detailAppeal != null)
                            edt_modify_port_act_appeal.hint = data.detailAppeal

                        //작품 리사이클러뷰 설정
                        val tmp: GetModifyPortFolioDataTpat? = response.body()!!.data
                        if (data.before != null) {
                            Log.v("dataList size", data.before.size.toString())
                            setTpatWorkRVAdapter(tmp!!)
                        }
                    }
                }
            }
        })
    }

    //ETC. 통신 - 기타
    private fun getModifyPortFolioResponseEtc() {
        val getModifyPortFolioResponseEtc = networkService.getModifyPortFolioResponseEtc(
            "application/json",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6Iuq4sO2DgCIsImlkeCI6NiwidHlwZSI6NCwiaWF0IjoxNTYyNTY2OTgwLCJleHAiOjE1NjM3NzY1ODAsImlzcyI6ImlnIn0.Q2x2Z6OKdAs78ExzZk5zZvRNfsu9lL3Av3WJ05XB74g"
            //SharedPreferenceController.getAuthorization(this)
        )
        getModifyPortFolioResponseEtc.enqueue(object : Callback<GetModifyPortFolioResponseEpatAndEtc> {
            override fun onFailure(call: Call<GetModifyPortFolioResponseEpatAndEtc>, t: Throwable) {
                Log.e("ModifyPortFolioAct Fail", t.toString())
            }

            override fun onResponse(
                call: Call<GetModifyPortFolioResponseEpatAndEtc>,
                response: Response<GetModifyPortFolioResponseEpatAndEtc>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.message == "조회 성공") {
                        val data = response.body()!!.data
                        Log.v("etcModify TAGGG", data.userType.toString())

                        //유저 이미지
                        Glide.with(this@ModifyPortFolioActivity)
                            .load(data.userImg)
                            .into(iv_act_modify_port_folio_user)

                        //유저 이름
                        tv_modify_port_act_user_name.text = data.userNickname

                        //유저 타입
                        tv_modify_port_folio_act_job.text = "etc"

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
                        if (data.detailAppeal != null)
                            edt_modify_port_act_appeal.hint = data.detailAppeal

                        //작품 리사이클러뷰 설정
                        val tmp: GetModifyPortFolioDataEpatAndEtc? = response.body()!!.data
                        if (data.thumbnail != null) {
                            Log.v("dataList size", data.thumbnail.size.toString())
                            setEpatAndEtcWorkRVAdapter(tmp!!)
                        }
                    }
                }
            }
        })
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
        Log.v("TAGGG remove", removeIndexList.toString())
        cpatWorkRVAdapter.notifyDataSetChanged()

    }

    //작품 리사이클러뷰 - 에디터/기타
    private fun setEpatAndEtcWorkRVAdapter(tmp: GetModifyPortFolioDataEpatAndEtc) {
        dataListEpatAndEtc = tmp
        if (dataListEpatAndEtc.thumbnail.isNotEmpty()) {
            rl_modify_port_folio_act_no_work.visibility = View.GONE
        }

        rv_modify_port_folio_act_work.adapter = epatWorkRVAdapter
        rv_modify_port_folio_act_work.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        epatWorkRVAdapter.dataList = tmp
        epatWorkRVAdapter.notifyDataSetChanged()

    }

    //작품 리사이클러 뷰 - 트랜슬레이터
    private fun setTpatWorkRVAdapter(tmp: GetModifyPortFolioDataTpat) {
        dataListTpat = tmp
        if (dataListTpat.after.isNotEmpty()) {
            rl_modify_port_folio_act_no_work.visibility = View.GONE
        }
        rv_modify_port_folio_act_work.adapter = transWorkRVAdapter
        rv_modify_port_folio_act_work.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        transWorkRVAdapter.dataList = tmp
        transWorkRVAdapter.notifyDataSetChanged()
    }

    //작품 삭제
    private fun deleteModifyPortResponse() {
        if (removeIndexList.isNotEmpty()) {
            //SharedPreferenceController.getUserType(this@ModifyPortFolioActivity)
            when (1) {
                //크리에이터
                1 -> deleteModifyPortResponseCpat(removeIndexList)
                //에디터
                2 -> deleteModifyPortFolioResponseEpat(removeIndexList)
                //트랜슬레이터
                3 -> deleteModifyPortFolioResponseTpat(removeIndexList)
                //기타
                4 -> deleteModifyPortFolioResponseEtc(removeIndexList)
            }
        }
    }

    // 작품 삭제 - CPAT
    private fun deleteModifyPortResponseCpat(workIndex: ArrayList<Int>) {
        val deleteModifyPortResponseCpat = networkService.deleteModifyPortFolioResponseCpat(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6ImN1dGV5YW5nIiwiaWR4IjozLCJ0eXBlIjoxLCJpYXQiOjE1NjI1NjcyNTgsImV4cCI6MTU2Mzc3Njg1OCwiaXNzIjoiaWcifQ.WHzr5l6RfzF3Uw88qUeuJe9rpLD4RHlsCB9pto-4MbM"
            , WorkIndex(workIndex)
        )
        deleteModifyPortResponseCpat.enqueue(object : Callback<DeleteModifyPortFolioResponse> {
            override fun onFailure(call: Call<DeleteModifyPortFolioResponse>, t: Throwable) {
                Log.e("TAGGG deleteWork Error", t.toString())
            }

            override fun onResponse(
                call: Call<DeleteModifyPortFolioResponse>,
                response: Response<DeleteModifyPortFolioResponse>
            ) {
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        when (it?.message ?: " ") {
                            "작품 삭제 성공" -> {
                                Log.v("TAGGG", it.message)
                            }
                            " " -> {
                                Log.v("TAGGG", "message가 널")
                            }
                            "작품이 존재 하지 않습니다" -> {
                                Log.v("TAGGG", "작품이 존재 하지 않습니다")
                            }
                            else -> {
                                Log.v("TAGGG", it.message)
                            }
                        }
                    }
            }
        })
    }

    // 작품 삭제 - EPAT
    private fun deleteModifyPortFolioResponseEpat(workIndex: ArrayList<Int>) {
        val deleteModifyPortFolioResponseEpat = networkService.deleteModifyPortFolioResponseEpat(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6IuyXkOuUlO2EsCIsImlkeCI6NSwidHlwZSI6MiwiaWF0IjoxNTYyNTY2OTM5LCJleHAiOjE1NjM3NzY1MzksImlzcyI6ImlnIn0.C4c6ibbr_QtAi2vk_S3ZftqmxJ9X0-EK7s8pNieLI_E"
            , WorkIndex(workIndex)
        )
        deleteModifyPortFolioResponseEpat.enqueue(object : Callback<DeleteModifyPortFolioResponse> {
            override fun onFailure(call: Call<DeleteModifyPortFolioResponse>, t: Throwable) {
                Log.e("TAGGG deleteWork Error", t.toString())
            }

            override fun onResponse(
                call: Call<DeleteModifyPortFolioResponse>,
                response: Response<DeleteModifyPortFolioResponse>
            ) {
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        when (it?.message ?: " ") {
                            "작품 삭제 성공" -> {
                                Log.v("TAGGG", it.message)
                            }
                            " " -> {
                                Log.v("TAGGG", "message가 널")
                            }
                            "작품이 존재 하지 않습니다" -> {
                                Log.v("TAGGG", "작품이 존재 하지 않습니다")
                            }
                            else -> {
                                Log.v("TAGGG", it.message)
                            }
                        }
                    }
            }
        })
    }

    //작품 삭제 - TPAT
    private fun deleteModifyPortFolioResponseTpat(workIndex: ArrayList<Int>) {
        val deleteModifyPortFolioResponseTpat = networkService.deleteModifyPortFolioResponseTpat(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6InRyYW5zbWFuIiwiaWR4Ijo0LCJ0eXBlIjozLCJpYXQiOjE1NjI2NzU2MzIsImV4cCI6MTU2Mzg4NTIzMiwiaXNzIjoiaWcifQ.onbsHzwqnyFmOPcpiuKxt2KxQS0c1pc1FHYnGsR1Flo"
            , WorkIndex(workIndex)
        )
        deleteModifyPortFolioResponseTpat.enqueue(object : Callback<DeleteModifyPortFolioResponse> {
            override fun onFailure(call: Call<DeleteModifyPortFolioResponse>, t: Throwable) {
                Log.e("TAGGG deleteWork Error", t.toString())
            }

            override fun onResponse(
                call: Call<DeleteModifyPortFolioResponse>,
                response: Response<DeleteModifyPortFolioResponse>
            ) {
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        when (it?.message ?: " ") {
                            "작품 삭제 성공" -> {
                                Log.v("TAGGG", it.message)
                            }
                            " " -> {
                                Log.v("TAGGG", "message가 널")
                            }
                            "작품이 존재 하지 않습니다" -> {
                                Log.v("TAGGG", "작품이 존재 하지 않습니다")
                            }
                            else -> {
                                Log.v("TAGGG", it.message)
                            }
                        }
                    }
            }
        })

    }

    //작품 삭제 - ETC
    private fun deleteModifyPortFolioResponseEtc(workIndex: ArrayList<Int>) {
        val deleteModifyPortFolioResponseEtc = networkService.deleteModifyPortFolioResponseEtc(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6Iuq4sO2DgCIsImlkeCI6NiwidHlwZSI6NCwiaWF0IjoxNTYyNTY2OTgwLCJleHAiOjE1NjM3NzY1ODAsImlzcyI6ImlnIn0.Q2x2Z6OKdAs78ExzZk5zZvRNfsu9lL3Av3WJ05XB74g"
            , WorkIndex(workIndex)
        )
        deleteModifyPortFolioResponseEtc.enqueue(object : Callback<DeleteModifyPortFolioResponse> {
            override fun onFailure(call: Call<DeleteModifyPortFolioResponse>, t: Throwable) {
                Log.e("TAGGG deleteWork Error", t.toString())
            }

            override fun onResponse(
                call: Call<DeleteModifyPortFolioResponse>,
                response: Response<DeleteModifyPortFolioResponse>
            ) {
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        when (it?.message ?: " ") {
                            "작품 삭제 성공" -> {
                                Log.v("TAGGG", it.message)
                            }
                            " " -> {
                                Log.v("TAGGG", "message가 널")
                            }
                            "작품이 존재 하지 않습니다" -> {
                                Log.v("TAGGG", "작품이 존재 하지 않습니다")
                            }
                            else -> {
                                Log.v("TAGGG", it.message)
                            }
                        }
                    }
            }
        })
    }

    //포트폴리오 수정
    private fun putModifyPortFolioResponse() {
        when (1) {
            //크리에이터
            1 -> {
                //구독자
                var subscriber = String()
                if (edt_modify_port_act_subscriber.text.isNotEmpty())
                    subscriber = edt_modify_port_act_subscriber.text.toString()
                else subscriber = edt_modify_port_act_subscriber.hint.toString()
                //플랫폼
                val platform = 1
                //소개
                var oneline = String()
                if (edt_modify_port_act_detail_oneline.text.isNotEmpty())
                    oneline = edt_modify_port_act_detail_oneline.text.toString()
                else oneline = edt_modify_port_act_detail_oneline.hint.toString()
                //원해요
                var want = String()
                if (edt_modify_port_act_want.text.isNotEmpty())
                    want = edt_modify_port_act_want.text.toString()
                else want = edt_modify_port_act_want.hint.toString()
                //수상경력 = null

                //필터
                val concept = 1
                val lang = 2
                val pd = 2
                val etc = 3

                modifyList = ModifyList(
                    platform,
                    subscriber,
                    oneline,
                    want,
                    "",
                    concept,
                    lang,
                    pd,
                    etc
                )
                val putModifyPortFolioResponse = networkService.putModifyPortFolioResponse(
                    "application/json",
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6ImN1dGV5YW5nIiwiaWR4IjozLCJ0eXBlIjoxLCJpYXQiOjE1NjI1NjcyNTgsImV4cCI6MTU2Mzc3Njg1OCwiaXNzIjoiaWcifQ.WHzr5l6RfzF3Uw88qUeuJe9rpLD4RHlsCB9pto-4MbM",
                    modifyList
                )
                putModifyPortFolioResponse.enqueue(object : Callback<PutModifyPortFolioResponse> {
                    override fun onFailure(call: Call<PutModifyPortFolioResponse>, t: Throwable) {
                        Log.e("TAGGG putC Error", t.toString())
                    }

                    override fun onResponse(
                        call: Call<PutModifyPortFolioResponse>,
                        response: Response<PutModifyPortFolioResponse>
                    ) {
                        response
                            ?.takeIf { it.isSuccessful }
                            ?.body()
                            ?.let {
                                when (it?.message ?: " ") {
                                    "성공" -> {
                                        Log.v("TAGGG", it.message)
                                    }
                                    else -> {
                                        Log.v("TAGGG", it.message)
                                    }
                                }
                            }
                    }
                })
            }
            //에디터
            2 -> {
                //구독자
                val subscriber = 0
                //플랫폼 -나중에 수정
                val platform = 1
                //소개
                var oneline = String()
                if (edt_modify_port_act_detail_oneline.text.isNotEmpty())
                    oneline = edt_modify_port_act_detail_oneline.text.toString()
                else oneline = edt_modify_port_act_detail_oneline.hint.toString()
                //원해요
                var want = String()
                if (edt_modify_port_act_want.text.isNotEmpty())
                    want = edt_modify_port_act_want.text.toString()
                else want = edt_modify_port_act_want.hint.toString()
                //수상경력
                var appeal = String()
                if (edt_modify_port_act_appeal.text.isNotEmpty())
                    appeal = edt_modify_port_act_appeal.text.toString()
                else appeal = edt_modify_port_act_appeal.hint.toString()
                //필터 - 나중에 수정
                val concept = 1
                val lang = 2
                val pd = 2
                val etc = 3

                modifyList = ModifyList(
                    platform,
                    subscriber.toString(),
                    oneline,
                    want.toString(),
                    appeal,
                    concept,
                    lang,
                    pd,
                    etc
                )
                val putModifyPortFolioResponse = networkService.putModifyPortFolioResponse(
                    "application/json",
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6IuyXkOuUlO2EsCIsImlkeCI6NSwidHlwZSI6MiwiaWF0IjoxNTYyNTY2OTM5LCJleHAiOjE1NjM3NzY1MzksImlzcyI6ImlnIn0.C4c6ibbr_QtAi2vk_S3ZftqmxJ9X0-EK7s8pNieLI_E",
                    modifyList
                )
                putModifyPortFolioResponse.enqueue(object : Callback<PutModifyPortFolioResponse> {
                    override fun onFailure(call: Call<PutModifyPortFolioResponse>, t: Throwable) {
                        Log.e("TAGGG putC Error", t.toString())
                    }

                    override fun onResponse(
                        call: Call<PutModifyPortFolioResponse>,
                        response: Response<PutModifyPortFolioResponse>
                    ) {
                        response
                            ?.takeIf { it.isSuccessful }
                            ?.body()
                            ?.let {
                                when (it?.message ?: " ") {
                                    "성공" -> {
                                        Log.v("TAGGG", it.message)
                                    }
                                    else -> {
                                        Log.v("TAGGG", it.message)
                                    }
                                }
                            }
                    }
                })
            }
            //트랜슬레이터
            3 -> {
                //구독자
                val subscriber = 0
                //플랫폼 -나중에 수정
                val platform = 2
                //소개
                var oneline = String()
                if (edt_modify_port_act_detail_oneline.text.isNotEmpty())
                    oneline = edt_modify_port_act_detail_oneline.text.toString()
                else oneline = edt_modify_port_act_detail_oneline.hint.toString()
                //원해요
                var want = String()
                if (edt_modify_port_act_want.text.isNotEmpty())
                    want = edt_modify_port_act_want.text.toString()
                else want = edt_modify_port_act_want.hint.toString()
                //수상경력
                var appeal = String()
                if (edt_modify_port_act_appeal.text.isNotEmpty())
                    appeal = edt_modify_port_act_appeal.text.toString()
                else appeal = edt_modify_port_act_appeal.hint.toString()
                val concept = 1
                val lang = 2
                val pd = 2
                val etc = 3

                modifyList = ModifyList(
                    platform,
                    subscriber.toString(),
                    oneline,
                    want.toString(),
                    appeal,
                    concept,
                    lang,
                    pd,
                    etc
                )
                val putModifyPortFolioResponse = networkService.putModifyPortFolioResponse(
                    "application/json",
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6InRyYW5zbWFuIiwiaWR4Ijo0LCJ0eXBlIjozLCJpYXQiOjE1NjI2NzU2MzIsImV4cCI6MTU2Mzg4NTIzMiwiaXNzIjoiaWcifQ.onbsHzwqnyFmOPcpiuKxt2KxQS0c1pc1FHYnGsR1Flo"
                    , modifyList
                )
                putModifyPortFolioResponse.enqueue(object : Callback<PutModifyPortFolioResponse> {
                    override fun onFailure(call: Call<PutModifyPortFolioResponse>, t: Throwable) {
                        Log.e("TAGGG putC Error", t.toString())
                    }

                    override fun onResponse(
                        call: Call<PutModifyPortFolioResponse>,
                        response: Response<PutModifyPortFolioResponse>
                    ) {
                        response
                            ?.takeIf { it.isSuccessful }
                            ?.body()
                            ?.let {
                                when (it?.message ?: " ") {
                                    "성공" -> {
                                        Log.v("TAGGG", it.message)
                                    }
                                    else -> {
                                        Log.v("TAGGG", it.message)
                                    }
                                }
                            }
                    }
                })
            }
            //기타
            4 -> {
                //구독자
                val subscriber = 0
                //플랫폼 -나중에 수정
                val platform = 1
                //소개
                var oneline = String()
                if (edt_modify_port_act_detail_oneline.text.isNotEmpty())
                    oneline = edt_modify_port_act_detail_oneline.text.toString()
                else oneline = edt_modify_port_act_detail_oneline.hint.toString()
                //원해요
                var want = String()
                if (edt_modify_port_act_want.text.isNotEmpty())
                    want = edt_modify_port_act_want.text.toString()
                else want = edt_modify_port_act_want.hint.toString()
                //수상경력
                var appeal = String()
                if (edt_modify_port_act_appeal.text.isNotEmpty())
                    appeal = edt_modify_port_act_appeal.text.toString()
                else appeal = edt_modify_port_act_appeal.hint.toString()
                //필터 - 나중에 수정
                val concept = 1
                val lang = 2
                val pd = 2
                val etc = 3

                modifyList = ModifyList(
                    platform,
                    subscriber.toString(),
                    oneline,
                    want.toString(),
                    appeal,
                    concept,
                    lang,
                    pd,
                    etc
                )
                val putModifyPortFolioResponse = networkService.putModifyPortFolioResponse(
                    "application/json",
                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6Iuq4sO2DgCIsImlkeCI6NiwidHlwZSI6NCwiaWF0IjoxNTYyNTY2OTgwLCJleHAiOjE1NjM3NzY1ODAsImlzcyI6ImlnIn0.Q2x2Z6OKdAs78ExzZk5zZvRNfsu9lL3Av3WJ05XB74g"
                    , modifyList
                )
                putModifyPortFolioResponse.enqueue(object : Callback<PutModifyPortFolioResponse> {
                    override fun onFailure(call: Call<PutModifyPortFolioResponse>, t: Throwable) {
                        Log.e("TAGGG putC Error", t.toString())
                    }

                    override fun onResponse(
                        call: Call<PutModifyPortFolioResponse>,
                        response: Response<PutModifyPortFolioResponse>
                    ) {
                        response
                            ?.takeIf { it.isSuccessful }
                            ?.body()
                            ?.let {
                                when (it?.message ?: " ") {
                                    "성공" -> {
                                        Log.v("TAGGG", it.message)
                                    }
                                    else -> {
                                        Log.v("TAGGG", it.message)
                                    }
                                }
                            }
                    }
                })
            }
        }
    }

    //필터 리사이클러 뷰
    private fun setFilterRVAdapter() {
        rv_modify_port_folio_act_filter.adapter = filterRVAdapter
        rv_modify_port_folio_act_filter.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    fun setRemoveIndexList(list: ArrayList<Int>, flag: Int) {
        if (flag == 0)
            rl_modify_port_folio_act_no_work.visibility = View.VISIBLE
        removeIndexList = list
        Log.v("TAGGG", "removeIndex in Act: " + removeIndexList.toString())
    }

    fun setFilterData(arrayList: ArrayList<FilterData>) {
        filterDataList = arrayList

        filterRVAdapter.changeDataList(filterDataList)

        filterRVAdapter.notifyDataSetChanged()

        Log.v("TAGGGGG", filterDataList.toString())
    }

}