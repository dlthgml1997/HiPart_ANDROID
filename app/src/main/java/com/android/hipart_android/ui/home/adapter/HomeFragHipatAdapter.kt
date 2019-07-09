package com.android.hipart_android.ui.home.adapter

import android.content.Context
import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.ui.hipart.HipartDetailActivity
import com.android.hipart_android.ui.home.data.get.ResData
import com.android.hipart_android.ui.home.data.post.PickDTO
import com.android.hipart_android.ui.home.data.post.PickResponse
import com.android.hipart_android.ui.main.MainActivity
import com.android.hipart_android.util.SharedPreferenceController
import com.bumptech.glide.Glide
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textColor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by TakHyeongMin on 2019-07-03.
 */
class HomeFragHipatAdapter(private val dataList: ArrayList<ResData>, private val context: Context?) :
    PagerAdapter() {

    override fun getCount(): Int = dataList.size

    override fun isViewFromObject(view: View, obj: Any): Boolean = view.equals(obj)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var conceptFlag = false
        var pdFlag = false
        var langFlag = false
        var etcFlag = false
        var pickBtnFlag = 0

        val view = LayoutInflater.from(context)!!.inflate(R.layout.vp_item_home_hipat, container, false)

        var data = dataList[position].info[0]
        pickBtnFlag = dataList[position].pickState

        val userImg = view.findViewById<ImageView>(R.id.iv_rv_home_hipat_user)
        Glide.with(context!!).load(data.user_img).into(userImg)

        val userName = view.findViewById<TextView>(R.id.tv_rv_home_hipat_user)
        userName.text = data.user_nickname

        val job = view.findViewById<TextView>(R.id.tv_rv_home_hipat_job)
        job.text = setUserJob(data.user_type)

        val firstTheme = view.findViewById<TextView>(R.id.tv_rv_home_hipat_theme_first)

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

        val secondTheme = view.findViewById<TextView>(R.id.tv_rv_home_hipat_theme_second)

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

        val thirdTheme = view.findViewById<TextView>(R.id.tv_rv_home_hipat_theme_third)
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

        val fourthTheme = view.findViewById<TextView>(R.id.tv_rv_home_hipat_theme_fourth)
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
        val des = view.findViewById<TextView>(R.id.tv_rv_home_hipat_description)
        des.text = data.detail_oneline

        val pickNum = view.findViewById<TextView>(R.id.tv_rv_home_hipat_pick_num)
        pickNum.text = data.pick.toString()

        val btnPick = view.findViewById<LinearLayout>(R.id.btn_rv_home_hipat_pick)

        val ivPick = view.findViewById<ImageView>(R.id.iv_rv_home_hipat_pick)

        if (pickBtnFlag == 0) {
            ivPick.isSelected = false
            pickNum.textColor = Color.parseColor("#c7c7c7")
        } else {
            ivPick.isSelected = true
            pickNum.textColor = Color.parseColor("#7947fd")
        }

        val root = view.findViewById<RelativeLayout>(R.id.root_hipat_frag)

        container.addView(view, 0)

        btnPick.setOnClickListener {
            if (pickBtnFlag == 0) {
                addPick(data.user_nickname)
                ivPick.isSelected = true
                pickNum.text = "${pickNum.text.toString().toInt()+ 1}"
                pickNum.textColor = Color.parseColor("#7947fd")

                pickBtnFlag = 1
            } else {
                deletePick(data.user_nickname)
                ivPick.isSelected = false
                pickNum.text = "${pickNum.text.toString().toInt()- 1}"
                pickNum.textColor = Color.parseColor("#c7c7c7")
                pickBtnFlag = 0
            }
        }

        root.setOnClickListener {
            // TODO : HipartDetailActivity에 user_id 넘기기
            context!!.startActivity<HipartDetailActivity>()
        }

        val platform = view.findViewById<ImageView>(R.id.iv_rv_home_hipat_platform)
        setPlatform(data.detail_platform, platform)

        return view
    }


    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    private fun addPick(nickName: String) {

        val networkService = ApplicationController.instance.networkService
        val addPick = networkService.addPick(SharedPreferenceController.getAuthorization(context!!), PickDTO(nickName))
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
                                Log.v("태그", it.message)
                                (context as MainActivity).setAnimPickIcon()
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
            networkService.deletePick(SharedPreferenceController.getAuthorization(context!!), PickDTO(nickName))
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

    private fun setUserJob(userType: Int): String {
        when (userType) {
            0 -> {
                Log.e("HomeFragHipatAdapter", "Null 발견")
                return ""
            }
            1 -> {
                return "크리에이터"
            }
            2 -> {
                return "에디터"
            }
            3 -> {
                return "번역가"
            }
            4 -> {
                return "기타"
            }
            else -> {
                Log.e("HomeFragHipatAdapter", "오류 발견")
                return ""
            }
        }
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

    private fun setPlatform(platformFlag: Int, iv: ImageView) {
        when (platformFlag) {
            1 -> {
                iv.setImageResource(R.drawable.youtube_grey_img)
            }
            2 -> {
                iv.setImageResource(R.drawable.afreeca_grey_img)
            }
            3 -> {
                iv.setImageResource(R.drawable.twitch_grey_img)
            }
        }
    }

    private val pickBtnCLickListener : View.OnClickListener = View.OnClickListener {

    }
}