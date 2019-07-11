package com.android.hipart_android.ui.hipat.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.ui.hipart.HipartDetailActivity
import com.android.hipart_android.ui.home.data.post.PickDTO
import com.android.hipart_android.ui.home.data.post.PickResponse
import com.android.hipart_android.ui.main.MainActivity
import com.android.hipart_android.ui.mypick.MyPickActivity
import com.android.hipart_android.ui.mypick.data.GetMyPickData
import com.android.hipart_android.util.SharedPreferenceController
import com.bumptech.glide.Glide
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textColor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PortFolioRecyclerViewAdapter(
    val ctx: Context,
    var dataList: List<GetMyPickData>,
    val mainActivityFlag: Boolean
) :
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

        var data = dataList[position].info[0]

        val firstTheme = holder.firstFilter
        when (data.user_type) {
            1 -> {
                Log.v("요놈", data.user_nickname + "  " + data.concept)
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
            Log.v("요놈", data.user_nickname + "  " + data.concept)
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
            Log.v("요놈", data.user_nickname + "  " + data.concept)
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
            Log.v("요놈", data.user_nickname + "  " + data.concept)
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


        val userImg = holder.user_image_thumbnail
        Glide.with(ctx).load(data.user_img).into(userImg)

        val name = holder.user_name
        name.text = data.user_nickname

        val platform = holder.kind_of_creator
        when (data.detail_platform) {
            0 -> {//크리에이터 아닐때
                platform.visibility = View.INVISIBLE
            }
            1 -> {//유튜브
                platform.setImageResource(R.drawable.youtube_grey_img)
            }
            2 -> {//아프리카
                platform.setImageResource(R.drawable.afreeca_grey_img)
            }
            3 -> {//트위치
                platform.setImageResource(R.drawable.twitch_grey_img)
            }
        }

        val kindOfPat = holder.kind_of_pat
        when (data.user_type) {
            1 -> { //c-pat
                kindOfPat.text = "크리에이터"
            }
            2 -> {
                kindOfPat.text = "편집자"
            }
            3 -> {
                kindOfPat.text = "번역가"
            }
            4 -> {
                kindOfPat.text = "기타"
            }
        }

        val des = holder.des
        des.text = data.detail_oneline

        val pickNum = holder.how_picked
        pickNum.text = data.pick.toString()

        val btnPick = holder.is_picked

        btnPick.isSelected = dataList[position].pickState == 1

        if (btnPick.isSelected == true) {
            pickNum.textColor = Color.parseColor("#7947fd")
        } else {
            pickNum.textColor = Color.parseColor("#c7c7c7")
        }



        holder.root.setOnClickListener { ctx!!.startActivity<HipartDetailActivity>("user_nickname" to data.user_nickname, "user_type" to data.user_type) }

        if (mainActivityFlag == true) {
            btnPick.setOnClickListener {
//                if (holder.is_picked.isSelected == false) {
//                    (ctx as MainActivity).setAnimPickIcon()
//                }


                if (btnPick.isSelected == false) {
                    addPick(data.user_nickname, true)
                    btnPick.isSelected = true
                    pickNum.textColor = Color.parseColor("#7947fd")
                    dataList[position].pickState = 1
                    data.pick = data.pick + 1

                } else {
                    deletePick(data.user_nickname)
                    btnPick.isSelected = false
                    data.pick = data.pick - 1
                    pickNum.textColor = Color.parseColor("#c7c7c7")
                    dataList[position].pickState = 0
                }
                notifyItemChanged(position)
            }
        } else {
            btnPick.setOnClickListener {
//                if (holder.is_picked.isSelected == false) {
//                    (ctx as MyPickActivity).setAnimPickIcon()
//                }
//                holder.is_picked.isSelected = !holder.is_picked.isSelected
                if (btnPick.isSelected == false) {
                    addPick(data.user_nickname, false)
                    btnPick.isSelected = true
                    pickNum.textColor = Color.parseColor("#7947fd")
                    dataList[position].pickState = 1
                    data.pick = data.pick + 1
                } else {
                    deletePick(data.user_nickname)
                    btnPick.isSelected = false
                    data.pick = data.pick - 1
                    pickNum.textColor = Color.parseColor("#c7c7c7")
                    dataList[position].pickState = 0
                }
                notifyItemChanged(position)
            }
        }

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var user_image_thumbnail = itemView.findViewById(R.id.img_rv_item_hipat_frag_port_user_image) as ImageView
        var user_name = itemView.findViewById(R.id.img_rv_item_hipat_frag_port_user_name) as TextView
        var kind_of_pat = itemView.findViewById(R.id.txt_hipat_frag_kind_of_pat) as TextView
        var des = itemView.findViewById<TextView>(R.id.tv_hipat_filter_act_description)
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

    private fun addPick(nickName: String, mainActFlag: Boolean) {

        val networkService = ApplicationController.instance.networkService
        val addPick = networkService.addPick(SharedPreferenceController.getAuthorization(ctx!!), PickDTO(nickName))
        addPick.enqueue(object : Callback<PickResponse> {
            override fun onFailure(call: Call<PickResponse>, t: Throwable) {
                Log.e("Add Pick Error", Log.getStackTraceString(t))
            }

            override fun onResponse(call: Call<PickResponse>, response: Response<PickResponse>) {
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        when (it?.message ?: " ") {
                            "픽 성공" -> {
                                if (mainActFlag == true)
                                    (ctx as MainActivity).setAnimPickIcon()
                                else
                                    (ctx as MyPickActivity).setAnimPickIcon()

                            }
                            " " -> {
                                Log.v("태그", "message가 널인데 ?")
                            }
                            "닉네임을 가진 유저가 없습니다." -> {
                                Log.v("태그", "닉네임을 가진 유저가 없다.")
                            }
                            // TODO : 픽리스트 없음
                            else -> {
                                Log.v("태그", it.message)
                            }
                        }
                    }
            }
        })
    }

    private fun deletePick(nickName: String) {

        val networkService = ApplicationController.instance.networkService
        val deletePick =
            networkService.deletePick(SharedPreferenceController.getAuthorization(ctx!!), PickDTO(nickName))
        deletePick.enqueue(object : Callback<PickResponse> {
            override fun onFailure(call: Call<PickResponse>, t: Throwable) {
                Log.e("Delete Pick Error", Log.getStackTraceString(t))
            }

            override fun onResponse(call: Call<PickResponse>, response: Response<PickResponse>) {
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        when (it?.message ?: " ") {

                            "픽 취소 성공" -> {
                                Log.v("태그", it.message)
                            }
                            " " -> {
                                Log.v("태그", "message가 널인데 ?")
                            }
                            "닉네임을 가진 유저가 없습니다." -> {
                                Log.v("태그", "닉네임을 가진 유저가 없다.")
                            }
                            // TODO : 픽리스트 없음
                            else -> {
                                Log.v("태그", it.message)
                            }
                        }
                    }
            }
        })
    }

}