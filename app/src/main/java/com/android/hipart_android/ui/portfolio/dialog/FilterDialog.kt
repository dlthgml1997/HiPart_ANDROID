package com.android.hipart_android.ui.portfolio.dialog

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.ui.modifyportfolio.ModifyPortFolioActivity
import com.android.hipart_android.ui.modifyportfolio.adapter.FilterRVAdapter
import com.android.hipart_android.ui.modifyportfolio.data.FilterData
import com.android.hipart_android.util.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_hipat_filter.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.textColor
import org.jetbrains.anko.textColorResource

class FilterDialog : DialogFragment() {

    private val userType by lazy{
        SharedPreferenceController.getUserType(context!!)
    }
    private val filterSuccessDialog by lazy {
        FilterSuccessDialog()
    }

    lateinit var selectedDataList : ArrayList<FilterData>
    lateinit var tvDataList : ArrayList<TextView>
    lateinit var rlDataList : ArrayList<RelativeLayout>
    lateinit var data : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_pofol_filter, container, false)
        return view
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)

        selectedDataToModifyActivity()
        (context as ModifyPortFolioActivity).setFilterData(selectedDataList)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setOnClickListener()
        addFilterArray()
    }

    private fun addFilterArray() {
        tvDataList = ArrayList<TextView>()
        tvDataList.add(tv_hipat_filter_act_game)
        tvDataList.add(tv_hipat_filter_act_asmr)
        tvDataList.add(tv_hipat_filter_act_prank)
        tvDataList.add(tv_hipat_filter_act_sport)
        tvDataList.add(tv_hipat_filter_act_cook)
        tvDataList.add(tv_hipat_filter_act_moviemusic)
        tvDataList.add(tv_hipat_filter_act_eduinfo)
        tvDataList.add(tv_hipat_filter_act_edit)
        tvDataList.add(tv_hipat_filter_act_produce)
        tvDataList.add(tv_hipat_filter_act_english)
        tvDataList.add(tv_hipat_filter_act_japanese)
        tvDataList.add(tv_hipat_filter_act_chinese)
        tvDataList.add(tv_hipat_filter_act_german)
        tvDataList.add(tv_hipat_filter_act_indian)
        tvDataList.add(tv_hipat_filter_act_russian)
        tvDataList.add(tv_hipat_filter_act_french)
        tvDataList.add(tv_hipat_filter_act_spanish)
        tvDataList.add(tv_hipat_filter_act_vietnames)
        tvDataList.add(tv_hipat_filter_act_italian)
        tvDataList.add(tv_hipat_filter_act_indonesian)
        tvDataList.add(tv_hipat_filter_act_props)
        tvDataList.add(tv_hipat_filter_act_codi)
        tvDataList.add(tv_hipat_filter_act_light)
        tvDataList.add(tv_hipat_filter_act_film)
        tvDataList.add(tv_hipat_filter_act_manager)
        tvDataList.add(tv_hipat_filter_act_thumbnail)

        rlDataList = ArrayList<RelativeLayout>()
        rlDataList.add(btn_hipat_filter_act_game)
        rlDataList.add(btn_hipat_filter_act_asmr)
        rlDataList.add(btn_hipat_filter_act_prank)
        rlDataList.add(btn_hipat_filter_act_sport)
        rlDataList.add(btn_hipat_filter_act_cook)
        rlDataList.add(btn_hipat_filter_act_moviemusic)
        rlDataList.add(btn_hipat_filter_act_eduinfo)
        rlDataList.add(btn_hipat_filter_act_edit)
        rlDataList.add(btn_hipat_filter_act_produce)
        rlDataList.add(btn_hipat_filter_act_english)
        rlDataList.add(btn_hipat_filter_act_japanese)
        rlDataList.add(btn_hipat_filter_act_chinese)
        rlDataList.add(btn_hipat_filter_act_german)
        rlDataList.add(btn_hipat_filter_act_indian)
        rlDataList.add(btn_hipat_filter_act_russian)
        rlDataList.add(btn_hipat_filter_act_french)
        rlDataList.add(btn_hipat_filter_act_spanish)
        rlDataList.add(btn_hipat_filter_act_vietnames)
        rlDataList.add(btn_hipat_filter_act_italian)
        rlDataList.add(btn_hipat_filter_act_indonesian)
        rlDataList.add(btn_hipat_filter_act_props)
        rlDataList.add(btn_hipat_filter_act_codi)
        rlDataList.add(btn_hipat_filter_act_light)
        rlDataList.add(btn_hipat_filter_act_film)
        rlDataList.add(btn_hipat_filter_act_manager)
        rlDataList.add(btn_hipat_filter_act_thumbnail)

        Log.v("init",tvDataList.size.toString())
    }

    private fun setOnClickListener() {
        setClickListerOnRelativeView(btn_hipat_filter_act_game, tv_hipat_filter_act_game)
        setClickListerOnRelativeView(btn_hipat_filter_act_asmr, tv_hipat_filter_act_asmr)
        setClickListerOnRelativeView(btn_hipat_filter_act_prank, tv_hipat_filter_act_prank)
        setClickListerOnRelativeView(btn_hipat_filter_act_sport, tv_hipat_filter_act_sport)
        setClickListerOnRelativeView(btn_hipat_filter_act_cook, tv_hipat_filter_act_cook)
        setClickListerOnRelativeView(btn_hipat_filter_act_moviemusic, tv_hipat_filter_act_moviemusic)
        setClickListerOnRelativeView(btn_hipat_filter_act_eduinfo, tv_hipat_filter_act_eduinfo)
        setClickListerOnRelativeView(btn_hipat_filter_act_edit, tv_hipat_filter_act_edit)
        setClickListerOnRelativeView(btn_hipat_filter_act_produce, tv_hipat_filter_act_produce)
        setClickListerOnRelativeView(btn_hipat_filter_act_english, tv_hipat_filter_act_english)
        setClickListerOnRelativeView(btn_hipat_filter_act_japanese, tv_hipat_filter_act_japanese)
        setClickListerOnRelativeView(btn_hipat_filter_act_chinese, tv_hipat_filter_act_chinese)
        setClickListerOnRelativeView(btn_hipat_filter_act_german, tv_hipat_filter_act_german)
        setClickListerOnRelativeView(btn_hipat_filter_act_indian, tv_hipat_filter_act_indian)
        setClickListerOnRelativeView(btn_hipat_filter_act_russian, tv_hipat_filter_act_russian)
        setClickListerOnRelativeView(btn_hipat_filter_act_french, tv_hipat_filter_act_french)
        setClickListerOnRelativeView(btn_hipat_filter_act_spanish, tv_hipat_filter_act_spanish)
        setClickListerOnRelativeView(btn_hipat_filter_act_vietnames, tv_hipat_filter_act_vietnames)
        setClickListerOnRelativeView(btn_hipat_filter_act_italian, tv_hipat_filter_act_italian)
        setClickListerOnRelativeView(btn_hipat_filter_act_indonesian, tv_hipat_filter_act_indonesian)
        setClickListerOnRelativeView(btn_hipat_filter_act_props, tv_hipat_filter_act_props)
        setClickListerOnRelativeView(btn_hipat_filter_act_codi, tv_hipat_filter_act_codi)
        setClickListerOnRelativeView(btn_hipat_filter_act_light, tv_hipat_filter_act_light)
        setClickListerOnRelativeView(btn_hipat_filter_act_film, tv_hipat_filter_act_film)
        setClickListerOnRelativeView(btn_hipat_filter_act_manager, tv_hipat_filter_act_manager)
        setClickListerOnRelativeView(btn_hipat_filter_act_thumbnail, tv_hipat_filter_act_thumbnail)

        btn_hipat_filter_filter.setOnClickListener {
            dismiss()
            showFilterSuccessDialog()
        }
    }

    private fun selectedDataToModifyActivity() {
        selectedDataList = ArrayList(0)

        /**
         * 1: 크리에이터
         * 2: 에디터
         * 3: 트랜슬레이터
         * 4: 기타
         */

        for(i in rlDataList.indices){
            if(rlDataList[i].isSelected)
            {
                when(tvDataList[i].text.toString()){
                    //방송 컨셉 - 크리에이터
                    "게임"->{
                        Log.v("TAGGG cpat filter",tvDataList[i].text.toString())
                        when(userType){
                            1-> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,1,"C"))
                            2,3,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,1,"C"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "ASMR"->{
                        Log.v("TAGGG cpat filter",tvDataList[i].text.toString())
                        when(userType){
                            1-> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,2,"C"))
                            2,3,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,2,"C"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "Prank"->{
                    Log.v("TAGGG cpat filter",tvDataList[i].text.toString())
                    when(userType){
                        1-> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,3,"C"))
                        2,3,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,3,"C"))
                        else -> toast("User PAT Type Error")
                    }
                }
                    "스포츠"->{
                        Log.v("TAGGG cpat filter",tvDataList[i].text.toString())
                        when(userType){
                            1-> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,4,"C"))
                            2,3,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,4,"C"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "쿡/먹방"->{
                        Log.v("TAGGG cpat filter",tvDataList[i].text.toString())
                        when(userType){
                            1-> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,5,"C"))
                            2,3,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,5,"C"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "영화/음악"->{
                        Log.v("TAGGG cpat filter",tvDataList[i].text.toString())
                        when(userType){
                            1-> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,6,"C"))
                            2,3,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,6,"C"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "교육/정보" ->{
                        Log.v("TAGGG cpat filter",tvDataList[i].text.toString())
                        when(userType){
                            1-> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,7,"C"))
                            2,3,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,7,"C"))
                            else -> toast("User PAT Type Error")
                            }
                    }
                    //PD - 에디터
                    "편집"->{
                        Log.v("TAGGG epat filter",tvDataList[i].text.toString())
                        when(userType){
                            2-> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,1,"E"))
                            1,3,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,1,"E"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "기획" ->{
                        Log.v("TAGGG epat filter",tvDataList[i].text.toString())
                        when(userType){
                            2-> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,2,"E"))
                            1,3,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,2,"E"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    //언어 - 트랜슬레이터
                    "영어" ->{
                        Log.v("TAGGG trans filter",tvDataList[i].text.toString())
                        when(userType){
                            3 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,1,"T"))
                            1,2,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,1,"T"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "일본어"->{
                        Log.v("TAGGG trans filter",tvDataList[i].text.toString())
                        when(userType){
                            3 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,2,"T"))
                            1,2,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,2,"T"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "중국어"->{
                        Log.v("TAGGG trans filter",tvDataList[i].text.toString())
                        when(userType){
                            3 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,3,"T"))
                            1,2,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,3,"T"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "독일어"->{
                        Log.v("TAGGG trans filter",tvDataList[i].text.toString())
                        when(userType){
                            3 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,4,"T"))
                            1,2,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,4,"T"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "인도어"->{
                        Log.v("TAGGG trans filter",tvDataList[i].text.toString())
                        when(userType){
                            3 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,5,"T"))
                            1,2,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,5,"T"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "러시아어"->{
                        Log.v("TAGGG trans filter",tvDataList[i].text.toString())
                        when(userType){
                            3 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,6,"T"))
                            1,2,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,6,"T"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "인도네시아어" ->{
                        Log.v("TAGGG trans filter",tvDataList[i].text.toString())
                        when(userType){
                            3 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,7,"T"))
                            1,2,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,7,"T"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "베트남어"->{
                        Log.v("TAGGG trans filter",tvDataList[i].text.toString())
                        when(userType){
                            3 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,8,"T"))
                            1,2,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,8,"T"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "이탈리아어"->{
                        Log.v("TAGGG trans filter",tvDataList[i].text.toString())
                        when(userType){
                            3 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,9,"T"))
                            1,2,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,9,"T"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "프랑스어"->{
                        Log.v("TAGGG trans filter",tvDataList[i].text.toString())
                        when(userType){
                            3 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,10,"T"))
                            1,2,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,10,"T"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "스페인어" ->{
                        Log.v("TAGGG trans filter",tvDataList[i].text.toString())
                        when(userType){
                            3 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,11,"T"))
                            1,2,4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,11,"T"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    //기타 - 기타
                    "소품"->{
                        Log.v("TAGGG etc filter",tvDataList[i].text.toString())
                        when(userType){
                            4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,1,"ETC"))
                            1,2,3 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,1,"ETC"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "코디" ->{
                        Log.v("TAGGG etc filter",tvDataList[i].text.toString())
                        when(userType){
                            4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,2,"ETC"))
                            1,2,3 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,2,"ETC"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "조명"->{
                        Log.v("TAGGG etc filter",tvDataList[i].text.toString())
                        when(userType){
                            4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,3,"ETC"))
                            1,2,3 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,3,"ETC"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "촬영"->{
                        Log.v("TAGGG etc filter",tvDataList[i].text.toString())
                        when(userType){
                            4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,4,"ETC"))
                            1,2,3 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,4,"ETC"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "매니저"->{
                        Log.v("TAGGG etc filter",tvDataList[i].text.toString())
                        when(userType){
                            4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,5,"ETC"))
                            1,2,3 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,5,"ETC"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    "썸네일" ->{
                        Log.v("TAGGG etc filter",tvDataList[i].text.toString())
                        when(userType){
                            4 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),true,6,"ETC"))
                            1,2,3 -> selectedDataList.add(FilterData(tvDataList[i].text.toString(),false,6,"ETC"))
                            else -> toast("User PAT Type Error")
                        }
                    }
                    else-> {
                        toast("필터 선택 에러")
                    }
                }
            }
        }
    }

    private fun showFilterSuccessDialog() {
        filterSuccessDialog.show(activity!!.supportFragmentManager, "filter success")
        val handler = Handler()
        handler.postDelayed({
            run {
                filterSuccessDialog.dismiss()
            }
        }, 1500)
    }

    fun setClickListerOnRelativeView(relativeLayout: RelativeLayout, textView: TextView) {
        relativeLayout.setOnClickListener {
            // game이 안눌려있을 때 눌린 플로우
            if (!relativeLayout.isSelected) {
                reverseConceptBtn(relativeLayout, textView)
                reversePdBtn(relativeLayout, textView)
                reverseLanguageBtn(relativeLayout, textView)
                reverseEtcBtn(relativeLayout, textView)
            }
            // game이 눌려있을 경우 눌린 플로우
            else {
                relativeLayout.isSelected = false
                textView.setTextColor(Color.parseColor("#707070"))
            }
        }
    }

    // 모든 버튼 초기화 한 뒤 reversedImg만 보라색으로!
    private fun reverseConceptBtn(reversedImg: RelativeLayout, reversedTextView: TextView) {
        when (reversedImg) {
            btn_hipat_filter_act_game -> {
                initConceptBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_asmr -> {

                initConceptBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_prank -> {
                initConceptBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_sport -> {
                initConceptBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_cook -> {
                initConceptBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_moviemusic -> {
                initConceptBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_eduinfo -> {
                initConceptBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
        }

    }

    // 모든 버튼 회색으로 바꾸기
    fun initConceptBtnFlag() {
        // 겉 박스 색 26개
        // TODO : 이것도 26개
        btn_hipat_filter_act_game.isSelected = false
        btn_hipat_filter_act_asmr.isSelected = false
        btn_hipat_filter_act_prank.isSelected = false
        btn_hipat_filter_act_sport.isSelected = false
        btn_hipat_filter_act_cook.isSelected = false
        btn_hipat_filter_act_moviemusic.isSelected = false
        btn_hipat_filter_act_eduinfo.isSelected = false
        // 이건 텍스트 색 바꾸기 26개
        // TODO : 이것도 26개
        tv_hipat_filter_act_game.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_asmr.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_prank.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_sport.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_cook.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_moviemusic.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_eduinfo.setTextColor(Color.parseColor("#707070"))
    }

    // 모든 버튼 초기화 한 뒤 reversedImg만 보라색으로!
    private fun reversePdBtn(reversedImg: RelativeLayout, reversedTextView: TextView) {
        when (reversedImg) {
            btn_hipat_filter_act_edit -> {
                initPdBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_produce -> {

                initPdBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
        }
    }

    // 모든 버튼 회색으로 바꾸기
    fun initPdBtnFlag() {
        // 겉 박스 색 26개
        btn_hipat_filter_act_edit.isSelected = false
        btn_hipat_filter_act_produce.isSelected = false
        // 이건 텍스트 색 바꾸기 26개
        // TODO : 이것도 26개
        tv_hipat_filter_act_edit.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_produce.setTextColor(Color.parseColor("#707070"))
    }

    // 모든 버튼 초기화 한 뒤 reversedImg만 보라색으로!
    private fun reverseLanguageBtn(reversedImg: RelativeLayout, reversedTextView: TextView) {
        when (reversedImg) {
            btn_hipat_filter_act_english -> {
                initLanguageBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_japanese -> {
                initLanguageBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_chinese -> {
                initLanguageBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_german -> {
                initLanguageBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_indian -> {
                initLanguageBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_russian -> {
                initLanguageBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_indonesian -> {

                initLanguageBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_vietnames -> {
                initLanguageBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_italian -> {
                initLanguageBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_french -> {
                initLanguageBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_spanish -> {
                initLanguageBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
        }

    }

    // 모든 버튼 회색으로 바꾸기
    fun initLanguageBtnFlag() {
        // 겉 박스 색 26개
        btn_hipat_filter_act_english.isSelected = false
        btn_hipat_filter_act_japanese.isSelected = false
        btn_hipat_filter_act_chinese.isSelected = false
        btn_hipat_filter_act_german.isSelected = false
        btn_hipat_filter_act_indian.isSelected = false
        btn_hipat_filter_act_russian.isSelected = false
        btn_hipat_filter_act_indonesian.isSelected = false
        btn_hipat_filter_act_vietnames.isSelected = false
        btn_hipat_filter_act_italian.isSelected = false
        btn_hipat_filter_act_french.isSelected = false
        btn_hipat_filter_act_spanish.isSelected = false
        // 이건 텍스트 색 바꾸기 26개
        // TODO : 이것도 26개
        tv_hipat_filter_act_english.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_japanese.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_chinese.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_german.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_indian.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_russian.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_indonesian.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_vietnames.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_italian.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_french.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_spanish.setTextColor(Color.parseColor("#707070"))
    }

    // 모든 버튼 초기화 한 뒤 reversedImg만 보라색으로!
    private fun reverseEtcBtn(reversedImg: RelativeLayout, reversedTextView: TextView) {
        when (reversedImg) {
            btn_hipat_filter_act_props -> {
                initEtcBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_codi -> {
                initEtcBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_light -> {

                initEtcBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_film -> {
                initEtcBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_manager -> {
                initEtcBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_thumbnail -> {
                initEtcBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
        }
    }

    // 모든 버튼 회색으로 바꾸기
    fun initEtcBtnFlag() {
        // 겉 박스 색 26개
        btn_hipat_filter_act_props.isSelected = false
        btn_hipat_filter_act_codi.isSelected = false
        btn_hipat_filter_act_light.isSelected = false
        btn_hipat_filter_act_film.isSelected = false
        btn_hipat_filter_act_manager.isSelected = false
        btn_hipat_filter_act_thumbnail.isSelected = false
        // 이건 텍스트 색 바꾸기 26개
        tv_hipat_filter_act_props.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_codi.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_light.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_film.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_manager.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_thumbnail.setTextColor(Color.parseColor("#707070"))

    }
}