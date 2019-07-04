package com.android.hipart_android.ui.hipart_filter

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.hipart_android.R
import kotlinx.android.synthetic.main.activity_hipat_filter.*

class HipatFilterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hipat_filter)

        setOnClickListener()
    }

    private fun setOnClickListener(){
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
    }

    fun setClickListerOnRelativeView(relativeLayout : RelativeLayout, textView : TextView){
        relativeLayout.setOnClickListener {
            // game이 안눌려있을 때 눌린 플로우
            if(relativeLayout.isSelected == false){
                reverseBtn(relativeLayout, textView)
            }
            // game이 눌려있을 경우 눌린 플로우
            else{
                relativeLayout.isSelected = false
            }
        }
    }
    // 모든 버튼 초기화 한 뒤 reversedImg만 보라색으로!
    private fun reverseBtn(reversedImg : RelativeLayout, reversedTextView: TextView){
        when(reversedImg){
            btn_hipat_filter_act_game -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_asmr -> {

                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_prank -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_sport -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_cook -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_moviemusic -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_eduinfo -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_edit -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_produce -> {

                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_english -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_japanese -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_chinese -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_german -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_indian -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_russian -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_indonesian -> {

                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_vietnames -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_italian -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_french -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_spanish -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_props -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_codi -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_light -> {

                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_film -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_manager -> {
                initBtnFlag()
                reversedImg.isSelected = true
                reversedTextView.setTextColor(Color.parseColor("#7947fd"))
            }
            btn_hipat_filter_act_thumbnail -> {
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