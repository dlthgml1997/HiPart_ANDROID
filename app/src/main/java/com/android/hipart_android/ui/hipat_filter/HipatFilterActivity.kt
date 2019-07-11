package com.android.hipart_android.ui.hipart_filter

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.ui.main.MainActivity
import com.android.hipart_android.util.BaseActivity
import kotlinx.android.synthetic.main.activity_hipat_filter.*



class HipatFilterActivity : BaseActivity() {

    private var filterFlag = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hipat_filter)

        configureTitleBar()
        setOnClickListener()
    }

    private fun configureTitleBar(){
        btn_toolbar_hipat_filter_back.setOnClickListener {
            val intent = Intent(this@HipatFilterActivity, MainActivity::class.java)
            intent.putExtra("filterFlag", filterFlag)
            setResult(0, intent)
            finish()
        }
        // 필터값이 적용된 것!!!
        btn_hipat_filter_filter.setOnClickListener {
            val intent = Intent(this@HipatFilterActivity, MainActivity::class.java)
            intent.putExtra("filterFlag", filterFlag)
            setResult(9, intent)
            finish()
        }
    }

    private fun setOnClickListener(){
        setClickListenerOnRelativeView(btn_hipat_filter_act_game, tv_hipat_filter_act_game)
        setClickListenerOnRelativeView(btn_hipat_filter_act_asmr, tv_hipat_filter_act_asmr)
        setClickListenerOnRelativeView(btn_hipat_filter_act_prank, tv_hipat_filter_act_prank)
        setClickListenerOnRelativeView(btn_hipat_filter_act_sport, tv_hipat_filter_act_sport)
        setClickListenerOnRelativeView(btn_hipat_filter_act_cook, tv_hipat_filter_act_cook)
        setClickListenerOnRelativeView(btn_hipat_filter_act_moviemusic, tv_hipat_filter_act_moviemusic)
        setClickListenerOnRelativeView(btn_hipat_filter_act_eduinfo, tv_hipat_filter_act_eduinfo)
        setClickListenerOnRelativeView(btn_hipat_filter_act_edit, tv_hipat_filter_act_edit)
        setClickListenerOnRelativeView(btn_hipat_filter_act_produce, tv_hipat_filter_act_produce)
        setClickListenerOnRelativeView(btn_hipat_filter_act_english, tv_hipat_filter_act_english)
        setClickListenerOnRelativeView(btn_hipat_filter_act_japanese, tv_hipat_filter_act_japanese)
        setClickListenerOnRelativeView(btn_hipat_filter_act_chinese, tv_hipat_filter_act_chinese)
        setClickListenerOnRelativeView(btn_hipat_filter_act_german, tv_hipat_filter_act_german)
        setClickListenerOnRelativeView(btn_hipat_filter_act_indian, tv_hipat_filter_act_indian)
        setClickListenerOnRelativeView(btn_hipat_filter_act_russian, tv_hipat_filter_act_russian)
        setClickListenerOnRelativeView(btn_hipat_filter_act_french, tv_hipat_filter_act_french)
        setClickListenerOnRelativeView(btn_hipat_filter_act_spanish, tv_hipat_filter_act_spanish)
        setClickListenerOnRelativeView(btn_hipat_filter_act_vietnames, tv_hipat_filter_act_vietnames)
        setClickListenerOnRelativeView(btn_hipat_filter_act_italian, tv_hipat_filter_act_italian)
        setClickListenerOnRelativeView(btn_hipat_filter_act_indonesian, tv_hipat_filter_act_indonesian)
        setClickListenerOnRelativeView(btn_hipat_filter_act_props, tv_hipat_filter_act_props)
        setClickListenerOnRelativeView(btn_hipat_filter_act_codi, tv_hipat_filter_act_codi)
        setClickListenerOnRelativeView(btn_hipat_filter_act_light, tv_hipat_filter_act_light)
        setClickListenerOnRelativeView(btn_hipat_filter_act_film, tv_hipat_filter_act_film)
        setClickListenerOnRelativeView(btn_hipat_filter_act_manager, tv_hipat_filter_act_manager)
        setClickListenerOnRelativeView(btn_hipat_filter_act_thumbnail, tv_hipat_filter_act_thumbnail)
    }

    fun setClickListenerOnRelativeView(relativeLayout : RelativeLayout, textView : TextView){
        relativeLayout.setOnClickListener {
            // game이 안눌려있을 때 눌린 플로우
            if(relativeLayout.isSelected == false){
                reverseBtn(relativeLayout, textView)
            }
            // game이 눌려있을 경우 눌린 플로우
            else{
                filterFlag = 0
                relativeLayout.isSelected = false
                textView.setTextColor(Color.parseColor("#707070"))
            }
        }
    }
    // 모든 버튼 초기화 한 뒤 reversedImg만 보라색으로!
    private fun reverseBtn(reversedImg : RelativeLayout, reversedTextView: TextView){
        when(reversedImg){
            btn_hipat_filter_act_game -> {
                filterFlag = 1
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_asmr -> {
                filterFlag = 2
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_prank -> {
                filterFlag = 3
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_sport -> {
                filterFlag = 4
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_cook -> {
                filterFlag = 5
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_moviemusic -> {
                filterFlag = 6
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_eduinfo -> {
                filterFlag = 7
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_edit -> {
                filterFlag = 8
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_produce -> {
                filterFlag = 9
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_english -> {
                filterFlag = 10
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_japanese -> {
                filterFlag = 11
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_chinese -> {
                filterFlag = 12
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_german -> {
                filterFlag = 13
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_indian -> {
                filterFlag = 14
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_russian -> {
                filterFlag = 15
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_indonesian -> {
                filterFlag = 16
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_vietnames -> {
                filterFlag = 17
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_italian -> {
                filterFlag = 18
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_french -> {
                filterFlag = 19
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_spanish -> {
                filterFlag = 20
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_props -> {
                filterFlag = 21
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_codi -> {
                filterFlag = 22
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_light -> {
                filterFlag = 23
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_film -> {
                filterFlag = 24
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_manager -> {
                filterFlag = 25
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_thumbnail -> {
                filterFlag = 26
                    initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
        }

    }

    // 모든 버튼 회색으로 바꾸기
    fun initBtnFlag(){
        // 겉 박스 색 26개
        // TODO : 이것도 26개
        btn_hipat_filter_act_game.isSelected = false
        btn_hipat_filter_act_asmr.isSelected = false
        btn_hipat_filter_act_prank.isSelected = false
        btn_hipat_filter_act_sport.isSelected = false
        btn_hipat_filter_act_cook.isSelected = false
        btn_hipat_filter_act_moviemusic.isSelected = false
        btn_hipat_filter_act_eduinfo.isSelected = false
        btn_hipat_filter_act_edit.isSelected = false
        btn_hipat_filter_act_produce.isSelected = false
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
        btn_hipat_filter_act_props.isSelected = false
        btn_hipat_filter_act_codi.isSelected = false
        btn_hipat_filter_act_light.isSelected = false
        btn_hipat_filter_act_film.isSelected = false
        btn_hipat_filter_act_manager.isSelected = false
        btn_hipat_filter_act_thumbnail.isSelected = false
        // 이건 텍스트 색 바꾸기 26개
        // TODO : 이것도 26개
        tv_hipat_filter_act_game.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_asmr.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_prank.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_sport.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_cook.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_moviemusic.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_eduinfo.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_edit.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_produce.setTextColor(Color.parseColor("#707070"))
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
        tv_hipat_filter_act_props.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_codi.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_light.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_film.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_manager.setTextColor(Color.parseColor("#707070"))
        tv_hipat_filter_act_thumbnail.setTextColor(Color.parseColor("#707070"))

    }
}