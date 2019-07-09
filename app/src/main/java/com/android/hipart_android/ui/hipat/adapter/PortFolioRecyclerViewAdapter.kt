package com.android.hipart_android.ui.hipat.adapter

import android.content.Context
import android.provider.Contacts.PresenceColumns.INVISIBLE
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.ui.hipart.HipartDetailActivity
import com.android.hipart_android.ui.hipat.data.PortFolioData
import com.android.hipart_android.ui.main.MainActivity
import com.android.hipart_android.ui.mypage.MyPickActivity
import com.android.hipart_android.ui.mypage.data.GetMyPickData
import org.jetbrains.anko.image
import org.jetbrains.anko.startActivity

class PortFolioRecyclerViewAdapter(val ctx: Context, var dataList: ArrayList<GetMyPickData>, val mainActivityFlag : Boolean) :
    RecyclerView.Adapter<PortFolioRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.rv_item_hipat_frag_portfolio, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var conceptFlag = false
        var pdFlag = false
        var langFlag = false
        var etcFlag = false
        var pickBtnFlag = 0

        var data = dataList[position]

        val firstTheme = holder.firstFilter
        when (data.user_type) {
            1 -> {
                setConceptTheme(data.concept, firstTheme)
                conceptFlag = true
            }
            2 -> {
                setPDTheme(data.pd, firstTheme)
                pdFlag = true
            }
            3 -> {
                setLangTheme(data.lang, firstTheme)
                langFlag = true
            }
            4 -> {
                setEtcTheme(data.lang, firstTheme)
                etcFlag = true
            }
        }

        val secondTheme = holder.secondFilter
        if (conceptFlag == false) {
            setConceptTheme(data.concept, secondTheme)
            conceptFlag = true
        } else if (pdFlag == false) {
            setPDTheme(data.pd, secondTheme)
            pdFlag = true
        } else if (langFlag == false) {
            setLangTheme(data.lang, secondTheme)
            langFlag = true
        } else if (etcFlag == false) {
            setEtcTheme(data.etc, secondTheme)
            etcFlag = true
        }

        val thirdTheme = holder.thirdFilter
        if (conceptFlag == false) {
            setConceptTheme(data.concept, thirdTheme)
            conceptFlag = true
        } else if (pdFlag == false) {
            setPDTheme(data.pd, thirdTheme)
            pdFlag = true
        } else if (langFlag == false) {
            setLangTheme(data.lang, thirdTheme)
            langFlag = true
        } else if (etcFlag == false) {
            setEtcTheme(data.etc, thirdTheme)
            etcFlag = true
        }

        val fourthTheme = holder.fourthFilter
        if (conceptFlag == false) {
            setConceptTheme(data.concept, fourthTheme)
            conceptFlag = true
        } else if (pdFlag == false) {
            setPDTheme(data.pd, fourthTheme)
            pdFlag = true
        } else if (langFlag == false) {
            setLangTheme(data.lang, fourthTheme)
            langFlag = true
        } else if (etcFlag == false) {
            setEtcTheme(data.etc, fourthTheme)
            etcFlag = true
        }


        holder.user_image_thumbnail.setImageResource(R.drawable.main_profile_photo_image)
        holder.user_name.text = dataList[position].user_nickname
        when(dataList[position].detail_platform){
            0->{//크리에이터 아닐때
                holder.kind_of_creator.visibility = View.INVISIBLE
            }
            1->{//유튜브
                holder.kind_of_creator.setImageResource(R.drawable.youtube_grey_img)
            }
            2->{//아프리카
                holder.kind_of_creator.setImageResource(R.drawable.pofol_afreeca_white_img)
            }
            3->{//틱톡
                holder.kind_of_creator.setImageResource(R.drawable.pofol_twitch_white_img)
            }
        }
        when(dataList[position].user_type){
            1->{ //c-pat
                holder.kind_of_pat.text = "크리에이터"
            }
            2->{
                holder.kind_of_pat.text = "편집자"
            }
            3->{
                holder.kind_of_pat.text = "번역가"
            }
            4->{
                holder.kind_of_pat.text = "기타"
            }
        }
        //how_many_filter

        //when(dataList[position].pick){holder.is_picked.isSelected = 1}
        //holder.is_picked.isSelected = dataList[position].detail_platform
        holder.how_picked.text = dataList[position].detail_platform.toString()

        holder.root.setOnClickListener { ctx!!.startActivity<HipartDetailActivity>() }

        if(mainActivityFlag == true){
            holder.is_picked.setOnClickListener {
                if(holder.is_picked.isSelected == false) {
                    (ctx as MainActivity).setAnimPickIcon()
                }
                holder.is_picked.isSelected = !holder.is_picked.isSelected
            }
        }else {
            holder.is_picked.setOnClickListener {
                if(holder.is_picked.isSelected == false) {
                    (ctx as MyPickActivity).setAnimPickIcon()
                }
                holder.is_picked.isSelected = !holder.is_picked.isSelected
            }
        }

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var user_image_thumbnail = itemView.findViewById(R.id.img_rv_item_hipat_frag_port_user_image) as ImageView
        var user_name = itemView.findViewById(R.id.img_rv_item_hipat_frag_port_user_name) as TextView
        var kind_of_pat = itemView.findViewById(R.id.txt_hipat_frag_kind_of_pat) as TextView
        var kind_of_creator = itemView.findViewById(R.id.img_rv_item_hipat_frag_kind_of_creator) as ImageView

        //필터홀더 텍스트로 바꾸기
        var firstFilter = itemView.findViewById(R.id.tv_hipat_filter_act_concept) as TextView
        var secondFilter = itemView.findViewById(R.id.tv_hipat_filter_pd) as TextView
        var thirdFilter = itemView.findViewById(R.id.tv_hipat_filter_lang) as TextView
        var fourthFilter = itemView.findViewById(R.id.tv_hipat_filter_act_chinese) as TextView


        var is_picked = itemView.findViewById(R.id.img_rv_item_hipat_frag_pick) as ImageView
        var how_picked = itemView.findViewById(R.id.txt_rv_item_hipat_frag_how_picked) as TextView
        var root = itemView.findViewById(R.id.btn_rv_item_hipat_frag_port_root) as RelativeLayout

    }

    private fun setConceptTheme(concept: Int, tv: TextView) {
        when (concept) {
            1 -> {
                tv.text = "게임"
            }
            2 -> {
                tv.text = "ASMR"
            }
            3 -> {
                tv.text = "Prank"
            }
            4 -> {
                tv.text = "스포츠"
            }
            5 -> {
                tv.text = "쿡/먹방"
            }
            6 -> {
                tv.text = "영화/음악"
            }
            7 -> {
                tv.text = "교육/정보"
            }
            else -> {
                Log.e("HomeFragHipatAdapter", "conceptFlag == ?")
                tv.visibility = View.GONE
            }
        }
    }

    private fun setPDTheme(pd: Int, tv: TextView) {
        when (pd) {
            1 -> {
                tv.text = "편집"
            }
            2 -> {
                tv.text = "기획"
            }
            else -> {
                Log.e("HomeFragHipatAdapter", "pd == ?")
                tv.visibility = View.GONE
            }
        }
    }

    private fun setLangTheme(lang: Int, tv: TextView) {
        when (lang) {

            1 -> {
                tv.text = "영어"
            }
            2 -> {
                tv.text = "일본어"
            }
            3 -> {
                tv.text = "중국어"
            }
            4 -> {
                tv.text = "독일어"
            }
            5 -> {
                tv.text = "인도어"
            }
            6 -> {
                tv.text = "러시아어"
            }
            7 -> {
                tv.text = "프랑스어"
            }
            8 -> {
                tv.text = "스페인어"
            }
            9 -> {
                tv.text = "베트남어"
            }
            10 -> {
                tv.text = "이탈리아어"
            }
            11 -> {
                tv.text = "인도네시아어"
            }
            else -> {
                Log.e("HomeFragHipatAdapter", "langFlag == ?")
                tv.visibility = View.GONE
            }
        }
    }

    private fun setEtcTheme(etc: Int, tv: TextView) {
        when (etc) {
            1 -> {
                tv.text = "소품"
            }
            2 -> {
                tv.text = "코디"
            }
            3 -> {
                tv.text = "조명"
            }
            4 -> {
                tv.text = "촬영"
            }
            5 -> {
                tv.text = "매니저"
            }
            6 -> {
                tv.text = "썸네일"
            }
            else -> {
                Log.e("HomeFragHipatAdapter", "etcFlag == ?")
                tv.visibility = View.GONE
            }
        }
    }

}