package com.android.hipart_android.ui.modifyportfolio

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.ImageView
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
import com.android.hipart_android.ui.modifyportfolio.dialog.ModifyPortGoToUploadDialog
import com.android.hipart_android.ui.modifyportfolio.dialog.PofolModifySuccessDialog
import com.android.hipart_android.ui.modifyportfolio.get.*
import com.android.hipart_android.ui.modifyportfolio.put.PutModifyPortFolioResponse
import com.android.hipart_android.ui.portfolio.dialog.FilterDialog
import com.android.hipart_android.util.BaseActivity
import com.android.hipart_android.util.SharedPreferenceController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_modify_port_folio.*
import org.jetbrains.anko.toast
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

class ModifyPortFolioActivity : BaseActivity(), View.OnClickListener {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    private val userType by lazy {
        SharedPreferenceController.getUserType(this@ModifyPortFolioActivity)
    }

    private val userToken by lazy {
        SharedPreferenceController.getAuthorization(this@ModifyPortFolioActivity)
    }

    private val pofolModifySuccessDialog by lazy {
        PofolModifySuccessDialog()
    }

    private val modifyPortGoToUploadDialog by lazy {
        ModifyPortGoToUploadDialog()
    }

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

    private var filterDataList = ArrayList<FilterData>()

    private var removeIndexList = ArrayList<Int>()

    lateinit var modifyList: ModifyList

    lateinit var dataListCpat: GetModifyPortFolioDataCpat

    lateinit var dataListEpatAndEtc: GetModifyPortFolioDataEpatAndEtc

    lateinit var dataListTpat: GetModifyPortFolioDataTpat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_port_folio)

        initView()
    }

    override fun onClick(v: View?) {
        when (v) {

            btn_modify_port_folio_back -> {
                finish()
            }

            //플랫폼 단일 선택
            btn_youtube -> {
                if (!btn_youtube.isSelected) {
                    reverseBtn(btn_youtube)
                }
                // game이 눌려있을 경우 눌린 플로우
                else {
                    btn_youtube.isSelected = false
                    btn_youtube.setImageResource(R.drawable.pofol_youtube_off_img)
                }
            }
            btn_afreeca -> {
                if (!btn_afreeca.isSelected) {
                    reverseBtn(btn_afreeca)
                }
                // game이 눌려있을 경우 눌린 플로우
                else {
                    btn_afreeca.isSelected = false
                    btn_afreeca.setImageResource(R.drawable.pofol_afreeca_white_off_img)
                }
            }
            btn_twich -> {
                if (!btn_twich.isSelected) {
                    reverseBtn(btn_twich)
                }
                // game이 눌려있을 경우 눌린 플로우
                else {
                    btn_twich.isSelected = false
                    btn_twich.setImageResource(R.drawable.pofol_twitch_white_off_img)
                }
            }

            // 필터 수정하기
            btn_modify_port_folio_act_modify_filter -> {
                filterDialog.show(supportFragmentManager, "filter dialog")
            }

            // 작품 올리기
            btn_modify_port_folio_act_work_add_work -> {
                showGoToUploadDialog()
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
        Log.v("TAGGGG", userType.toString())
        when (userType) {
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
            userToken
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

                        //필터
                        getFilterCpat(data)

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
                        tv_modify_port_folio_act_job.text = "Creator"

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

    //필터 로딩하기
    private fun getFilterCpat(data: GetModifyPortFolioDataCpat) {
        when (data.concept) {
            //방송 컨셉 - 크리에이터
            1 -> filterDataList.add(FilterData("게임", true, 1, "C"))
            2 -> filterDataList.add(FilterData("ASMR", true, 2, "C"))
            3 -> filterDataList.add(FilterData("Prank", true, 3, "C"))
            4 -> filterDataList.add(FilterData("스포츠", true, 4, "C"))
            5 -> filterDataList.add(FilterData("쿡/먹방", true, 5, "C"))
            6 -> filterDataList.add(FilterData("영화/음악", true, 6, "C"))
            7 -> filterDataList.add(FilterData("교육/정보", true, 7, "C"))
        }
        when (data.pd) {
            //PD - 에디터
            1 -> filterDataList.add(FilterData("편집", false, 1, "E"))
            2 -> filterDataList.add(FilterData("기획", false, 2, "E"))
        }
        //언어 - 트랜슬레이터
        when (data.lang) {
            1 -> filterDataList.add(FilterData("영어", false, 1, "T"))
            2 -> filterDataList.add(FilterData("일본어", false, 2, "T"))
            3 -> filterDataList.add(FilterData("중국어", false, 3, "T"))
            4 -> filterDataList.add(FilterData("독일어", false, 4, "T"))
            5 -> filterDataList.add(FilterData("인도어", false, 5, "T"))
            6 -> filterDataList.add(FilterData("러시아어", false, 6, "T"))
            7 -> filterDataList.add(FilterData("인도네시아어", false, 7, "T"))
            8 -> filterDataList.add(FilterData("베트남어", false, 8, "T"))
            9 -> filterDataList.add(FilterData("이탈리아어", false, 9, "T"))
            10 -> filterDataList.add(FilterData("프랑스어", false, 10, "T"))
            11 -> filterDataList.add(FilterData("스페인어", false, 11, "T"))
        }
        //기타 - 기타
        when (data.etc) {
            1 -> filterDataList.add(FilterData("소품", false, 1, "ETC"))
            2 -> filterDataList.add(FilterData("코디", false, 2, "ETC"))
            3 -> filterDataList.add(FilterData("조명", false, 3, "ETC"))
            4 -> filterDataList.add(FilterData("촬영", false, 4, "ETC"))
            5 -> filterDataList.add(FilterData("매니저", false, 5, "ETC"))
            6 -> filterDataList.add(FilterData("썸네일", false, 6, "ETC"))
        }
        setFilterData(filterDataList)
    }

    private fun getFilterEpat(data: GetModifyPortFolioDataEpatAndEtc) {
        when (data.concept) {
            //방송 컨셉 - 크리에이터
            1 -> filterDataList.add(FilterData("게임", false, 1, "C"))
            2 -> filterDataList.add(FilterData("ASMR", false, 2, "C"))
            3 -> filterDataList.add(FilterData("Prank", false, 3, "C"))
            4 -> filterDataList.add(FilterData("스포츠", false, 4, "C"))
            5 -> filterDataList.add(FilterData("쿡/먹방", false, 5, "C"))
            6 -> filterDataList.add(FilterData("영화/음악", false, 6, "C"))
            7 -> filterDataList.add(FilterData("교육/정보", false, 7, "C"))
        }
        when (data.pd) {
            //PD - 에디터
            1 -> filterDataList.add(FilterData("편집", true, 1, "E"))
            2 -> filterDataList.add(FilterData("기획", true, 2, "E"))
        }
        //언어 - 트랜슬레이터
        when (data.lang) {
            1 -> filterDataList.add(FilterData("영어", false, 1, "T"))
            2 -> filterDataList.add(FilterData("일본어", false, 2, "T"))
            3 -> filterDataList.add(FilterData("중국어", false, 3, "T"))
            4 -> filterDataList.add(FilterData("독일어", false, 4, "T"))
            5 -> filterDataList.add(FilterData("인도어", false, 5, "T"))
            6 -> filterDataList.add(FilterData("러시아어", false, 6, "T"))
            7 -> filterDataList.add(FilterData("인도네시아어", false, 7, "T"))
            8 -> filterDataList.add(FilterData("베트남어", false, 8, "T"))
            9 -> filterDataList.add(FilterData("이탈리아어", false, 9, "T"))
            10 -> filterDataList.add(FilterData("프랑스어", false, 10, "T"))
            11 -> filterDataList.add(FilterData("스페인어", false, 11, "T"))
        }
        //기타 - 기타
        when (data.etc) {
            1 -> filterDataList.add(FilterData("소품", false, 1, "ETC"))
            2 -> filterDataList.add(FilterData("코디", false, 2, "ETC"))
            3 -> filterDataList.add(FilterData("조명", false, 3, "ETC"))
            4 -> filterDataList.add(FilterData("촬영", false, 4, "ETC"))
            5 -> filterDataList.add(FilterData("매니저", false, 5, "ETC"))
            6 -> filterDataList.add(FilterData("썸네일", false, 6, "ETC"))
        }
        setFilterData(filterDataList)
    }

    private fun getFilterTpat(data: GetModifyPortFolioDataTpat) {
        when (data.concept) {
            //방송 컨셉 - 크리에이터
            1 -> filterDataList.add(FilterData("게임", false, 1, "C"))
            2 -> filterDataList.add(FilterData("ASMR", false, 2, "C"))
            3 -> filterDataList.add(FilterData("Prank", false, 3, "C"))
            4 -> filterDataList.add(FilterData("스포츠", false, 4, "C"))
            5 -> filterDataList.add(FilterData("쿡/먹방", false, 5, "C"))
            6 -> filterDataList.add(FilterData("영화/음악", false, 6, "C"))
            7 -> filterDataList.add(FilterData("교육/정보", false, 7, "C"))
        }
        when (data.pd) {
            //PD - 에디터
            1 -> filterDataList.add(FilterData("편집", false, 1, "E"))
            2 -> filterDataList.add(FilterData("기획", false, 2, "E"))
        }
        //언어 - 트랜슬레이터
        when (data.lang) {
            1 -> filterDataList.add(FilterData("영어", true, 1, "T"))
            2 -> filterDataList.add(FilterData("일본어", true, 2, "T"))
            3 -> filterDataList.add(FilterData("중국어", true, 3, "T"))
            4 -> filterDataList.add(FilterData("독일어", true, 4, "T"))
            5 -> filterDataList.add(FilterData("인도어", true, 5, "T"))
            6 -> filterDataList.add(FilterData("러시아어", true, 6, "T"))
            7 -> filterDataList.add(FilterData("인도네시아어", true, 7, "T"))
            8 -> filterDataList.add(FilterData("베트남어", true, 8, "T"))
            9 -> filterDataList.add(FilterData("이탈리아어", true, 9, "T"))
            10 -> filterDataList.add(FilterData("프랑스어", true, 10, "T"))
            11 -> filterDataList.add(FilterData("스페인어", true, 11, "T"))
        }
        //기타 - 기타
        when (data.etc) {
            1 -> filterDataList.add(FilterData("소품", false, 1, "ETC"))
            2 -> filterDataList.add(FilterData("코디", false, 2, "ETC"))
            3 -> filterDataList.add(FilterData("조명", false, 3, "ETC"))
            4 -> filterDataList.add(FilterData("촬영", false, 4, "ETC"))
            5 -> filterDataList.add(FilterData("매니저", false, 5, "ETC"))
            6 -> filterDataList.add(FilterData("썸네일", false, 6, "ETC"))
        }
        setFilterData(filterDataList)
    }

    private fun getFilterETC(data: GetModifyPortFolioDataEpatAndEtc) {
        when (data.concept) {
            //방송 컨셉 - 크리에이터
            1 -> filterDataList.add(FilterData("게임", false, 1, "C"))
            2 -> filterDataList.add(FilterData("ASMR", false, 2, "C"))
            3 -> filterDataList.add(FilterData("Prank", false, 3, "C"))
            4 -> filterDataList.add(FilterData("스포츠", false, 4, "C"))
            5 -> filterDataList.add(FilterData("쿡/먹방", false, 5, "C"))
            6 -> filterDataList.add(FilterData("영화/음악", false, 6, "C"))
            7 -> filterDataList.add(FilterData("교육/정보", false, 7, "C"))
        }
        when (data.pd) {
            //PD - 에디터
            1 -> filterDataList.add(FilterData("편집", false, 1, "E"))
            2 -> filterDataList.add(FilterData("기획", false, 2, "E"))
        }
        //언어 - 트랜슬레이터
        when (data.lang) {
            1 -> filterDataList.add(FilterData("영어", false, 1, "T"))
            2 -> filterDataList.add(FilterData("일본어", false, 2, "T"))
            3 -> filterDataList.add(FilterData("중국어", false, 3, "T"))
            4 -> filterDataList.add(FilterData("독일어", false, 4, "T"))
            5 -> filterDataList.add(FilterData("인도어", false, 5, "T"))
            6 -> filterDataList.add(FilterData("러시아어", false, 6, "T"))
            7 -> filterDataList.add(FilterData("인도네시아어", false, 7, "T"))
            8 -> filterDataList.add(FilterData("베트남어", false, 8, "T"))
            9 -> filterDataList.add(FilterData("이탈리아어", false, 9, "T"))
            10 -> filterDataList.add(FilterData("프랑스어", false, 10, "T"))
            11 -> filterDataList.add(FilterData("스페인어", false, 11, "T"))
        }
        //기타 - 기타
        when (data.etc) {
            1 -> filterDataList.add(FilterData("소품", true, 1, "ETC"))
            2 -> filterDataList.add(FilterData("코디", true, 2, "ETC"))
            3 -> filterDataList.add(FilterData("조명", true, 3, "ETC"))
            4 -> filterDataList.add(FilterData("촬영", true, 4, "ETC"))
            5 -> filterDataList.add(FilterData("매니저", true, 5, "ETC"))
            6 -> filterDataList.add(FilterData("썸네일", true, 6, "ETC"))
        }
        setFilterData(filterDataList)
    }

    //E-PAT 통신 - 에디터
    private fun getModifyPortFolioResponseEpat() {
        val getModifyPortFolioResponseEpat = networkService.getModifyPortFolioResponseEpat(
            "application/json",
            userToken
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
                        //필터
                        getFilterEpat(data)

                        //유저 이미지
                        Glide.with(this@ModifyPortFolioActivity)
                            .load(data.userImg)
                            .into(iv_act_modify_port_folio_user)

                        //유저 이름
                        tv_modify_port_act_user_name.text = data.userNickname

                        //유저 타입
                        tv_modify_port_folio_act_job.text = "Editor"

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
            "application/json", userToken
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

                        //필터
                        getFilterTpat(data)

                        //유저 이미지
                        Glide.with(this@ModifyPortFolioActivity)
                            .load(data.userImg)
                            .into(iv_act_modify_port_folio_user)

                        //유저 이름
                        tv_modify_port_act_user_name.text = data.userNickname

                        //유저 타입
                        tv_modify_port_folio_act_job.text = "Translator"

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
            "application/json", userToken
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

                        //필터
                        getFilterETC(data)

                        //유저 이미지
                        Glide.with(this@ModifyPortFolioActivity)
                            .load(data.userImg)
                            .into(iv_act_modify_port_folio_user)

                        //유저 이름
                        tv_modify_port_act_user_name.text = data.userNickname

                        //유저 타입
                        tv_modify_port_folio_act_job.text = "Etc."

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
            when (userType) {
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

    //작품 삭제 - CPAT
    private fun deleteModifyPortResponseCpat(workIndex: ArrayList<Int>) {
        val deleteModifyPortResponseCpat = networkService.deleteModifyPortFolioResponseCpat(
            userToken, WorkIndex(workIndex)
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

    //작품 삭제 - EPAT
    private fun deleteModifyPortFolioResponseEpat(workIndex: ArrayList<Int>) {
        val deleteModifyPortFolioResponseEpat = networkService.deleteModifyPortFolioResponseEpat(
            userToken, WorkIndex(workIndex)
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
            userToken, WorkIndex(workIndex)
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
            userToken, WorkIndex(workIndex)
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
        when (userType) {
            //크리에이터
            1 -> {
                //구독자
                var subscriber = String()
                if (edt_modify_port_act_subscriber.text.isNotEmpty())
                    subscriber = edt_modify_port_act_subscriber.text.toString()
                else subscriber = edt_modify_port_act_subscriber.hint.toString()
                //플랫폼
                var platform = 0
                when {
                    btn_youtube.isSelected -> platform = 1
                    btn_afreeca.isSelected -> platform = 2
                    btn_twich.isSelected -> platform = 3
                    else -> platform = 0
                }
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
                var concept = 0
                var pd = 0
                var lang = 0
                var etc = 0
                for (i in filterDataList.indices) {
                    when (filterDataList[i].filterPAT) {
                        "C" -> {
                            concept = filterDataList[i].filterIndex
                        }
                        "E" -> {
                            pd = filterDataList[i].filterIndex
                        }
                        "T" -> {
                            lang = filterDataList[i].filterIndex
                        }
                        "ETC" -> {
                            etc = filterDataList[i].filterIndex
                        }
                    }
                }

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
                    "application/json", userToken, modifyList
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
                                        showSuccessDialog()
                                        Log.v("TAGG platform: ", modifyList.toString())
                                    }
                                    else -> {
                                        toast(it.message)
                                    }
                                }
                            }
                    }
                })
            }
            //에디터
            2 -> {
                // 구독자
                val subscriber = 0
                // 플랫폼
                var platform = 0
                when {
                    btn_youtube.isSelected -> platform = 1
                    btn_afreeca.isSelected -> platform = 2
                    btn_twich.isSelected -> platform = 3
                    else -> platform = 0
                }
                // 소개
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
                //필터
                var concept = 0
                var pd = 0
                var lang = 0
                var etc = 0
                for (i in filterDataList.indices) {
                    when (filterDataList[i].filterPAT) {
                        "C" -> {
                            concept = filterDataList[i].filterIndex
                        }
                        "E" -> {
                            pd = filterDataList[i].filterIndex
                        }
                        "T" -> {
                            lang = filterDataList[i].filterIndex
                        }
                        "ETC" -> {
                            etc = filterDataList[i].filterIndex
                        }
                    }
                }


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
                    "application/json", userToken, modifyList
                )
                putModifyPortFolioResponse.enqueue(object : Callback<PutModifyPortFolioResponse> {
                    override fun onFailure(call: Call<PutModifyPortFolioResponse>, t: Throwable) {
                        Log.e("TAGGG putE Error", t.toString())
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
                                        showSuccessDialog()
                                    }
                                    else -> {
                                        toast(it.message)
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
                //플랫폼
                var platform = 0
                when {
                    btn_youtube.isSelected -> platform = 1
                    btn_afreeca.isSelected -> platform = 2
                    btn_twich.isSelected -> platform = 3
                    else -> platform = 0
                }
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

                //필터
                var concept = 0
                var pd = 0
                var lang = 0
                var etc = 0
                for (i in filterDataList.indices) {
                    when (filterDataList[i].filterPAT) {
                        "C" -> {
                            concept = filterDataList[i].filterIndex
                        }
                        "E" -> {
                            pd = filterDataList[i].filterIndex
                        }
                        "T" -> {
                            lang = filterDataList[i].filterIndex
                        }
                        "ETC" -> {
                            etc = filterDataList[i].filterIndex
                        }
                    }
                }


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
                    "application/json", userToken, modifyList
                )
                putModifyPortFolioResponse.enqueue(object : Callback<PutModifyPortFolioResponse> {
                    override fun onFailure(call: Call<PutModifyPortFolioResponse>, t: Throwable) {
                        Log.e("TAGGG putT Error", t.toString())
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
                                        showSuccessDialog()
                                    }
                                    else -> {
                                        toast(it.message)
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

                //플랫폼
                var platform = 0
                when {
                    btn_youtube.isSelected -> platform = 1
                    btn_afreeca.isSelected -> platform = 2
                    btn_twich.isSelected -> platform = 3
                    else -> platform = 0
                }
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
                //필터
                var concept = 0
                var pd = 0
                var lang = 0
                var etc = 0
                for (i in filterDataList.indices) {
                    when (filterDataList[i].filterPAT) {
                        "C" -> {
                            concept = filterDataList[i].filterIndex
                        }
                        "E" -> {
                            pd = filterDataList[i].filterIndex
                        }
                        "T" -> {
                            lang = filterDataList[i].filterIndex
                        }
                        "ETC" -> {
                            etc = filterDataList[i].filterIndex
                        }
                    }
                }

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
                    "application/json", userToken, modifyList
                )
                putModifyPortFolioResponse.enqueue(object : Callback<PutModifyPortFolioResponse> {
                    override fun onFailure(call: Call<PutModifyPortFolioResponse>, t: Throwable) {
                        Log.e("TAGGG putEtc Error", t.toString())
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
                                        showSuccessDialog()
                                    }
                                    else -> {
                                        toast(it.message)
                                    }
                                }
                            }
                    }
                })
            }
        }
    }

    //다이얼로그
    private fun showSuccessDialog() {
        pofolModifySuccessDialog.show(supportFragmentManager, "modify pofol success")
        val handler = Handler()
        handler.postDelayed({
            run {
                pofolModifySuccessDialog.dismiss()
            }
        }, 1500)
    }

    private fun showGoToUploadDialog() {
        modifyPortGoToUploadDialog.show(supportFragmentManager, "go to upload")
    }

    //필터 리사이클러 뷰
    private fun setFilterRVAdapter() {
        rv_modify_port_folio_act_filter.adapter = filterRVAdapter
        rv_modify_port_folio_act_filter.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    fun setFilterData(arrayList: ArrayList<FilterData>) {

        filterDataList = arrayList

        filterRVAdapter.changeDataList(filterDataList)

        filterRVAdapter.notifyDataSetChanged()

        Log.v("TAGGGGG", filterDataList.toString())
    }

    fun setRemoveIndexListFromAdapter(list: ArrayList<Int>, flag: Int) {
        if (flag == 0)
            rl_modify_port_folio_act_no_work.visibility = View.VISIBLE
        removeIndexList = list
        Log.v("TAGGG", "removeIndex in Act: " + removeIndexList.toString())
    }

    //플랫폼 단일 선택을 위해 쓰이는 함수
    private fun reverseBtn(imageView: ImageView?) {
        when (imageView) {
            btn_youtube -> {
                initBtnFlag()
                btn_youtube.isSelected = true
                btn_youtube.setImageResource(R.drawable.pofol_youtube_white_img)
            }
            btn_afreeca -> {
                initBtnFlag()
                btn_afreeca.isSelected = true
                btn_afreeca.setImageResource(R.drawable.pofol_afreeca_white_img)
            }
            btn_twich -> {
                initBtnFlag()
                btn_twich.isSelected = true
                btn_twich.setImageResource(R.drawable.pofol_twitch_white_img)
            }
        }
    }

    private fun initBtnFlag() {
        btn_youtube.isSelected = false
        btn_youtube.setImageResource(R.drawable.pofol_youtube_off_img)
        btn_afreeca.isSelected = false
        btn_afreeca.setImageResource(R.drawable.pofol_afreeca_white_off_img)
        btn_twich.isSelected = false
        btn_twich.setImageResource(R.drawable.pofol_twitch_white_off_img)
    }

    private fun initView() {
        setFilterRVAdapter()
        getModifyPortFolioResponse()
        setOnClickListener()
    }


}