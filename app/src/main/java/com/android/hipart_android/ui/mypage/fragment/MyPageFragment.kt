package com.android.hipart_android.ui.mypage.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.network.NetworkService
import com.android.hipart_android.ui.modifyportfolio.ModifyPortFolioActivity
import com.android.hipart_android.ui.modifyprofile.ModifyActivity
import com.android.hipart_android.ui.mypage.data.get.GetMyPageResponse
import com.android.hipart_android.ui.mypage.dialog.HifiveDialog
import com.android.hipart_android.ui.mypage.dialog.QuestionDialog
import com.android.hipart_android.ui.mypick.MyPickActivity
import com.android.hipart_android.util.SharedPreferenceController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageFragment : Fragment() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    lateinit var name : TextView

    lateinit var part : TextView

    lateinit var platform : ImageView


    lateinit var profilePhoto : ImageView

    lateinit var farmNum : TextView

    lateinit var pickNum : TextView

    lateinit var hifiveNum : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mypage, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.v("성청하", "onActCre")
        name= txt_mypage_frag_name

        part = txt_mypage_frag_part

        platform = txt_mypage_frag_detail_platform


        profilePhoto = img_mypage_frag_profile_photo

        farmNum = txt_mypage_frag_farm_num

        pickNum = txt_mypage_frag_pick_num

        hifiveNum = txt_mypage_frag_hifive_num
        getMypageResponse()

        setOnBtnClikListener()
    }

    private fun getMypageResponse() {
        val getMypageResponse = networkService.getMypageResponse(
            "application/json",
            SharedPreferenceController.getAuthorization(this@MyPageFragment.context!!)
        )
        getMypageResponse.enqueue(object : Callback<GetMyPageResponse> {

            // 통신 실패 시
            override fun onFailure(call: Call<GetMyPageResponse>, t: Throwable) {
                Log.e("MyPageFragment Fail", t.toString())
            }

            // 통신 성공 시
            override fun onResponse(call: Call<GetMyPageResponse>, response: Response<GetMyPageResponse>) {
                Log.v("Success!", response.body()!!.toString())
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.data
                    ?.let {
//                        val data = response.body()!!.data[0]
//
//                        txt_mypage_frag_name.searchText = data.user_nickname
//                        txt_mypage_frag_part.searchText = getUserType(data.user_type)
//                        getPlatformType(data.detail_platform, txt_mypage_frag_detail_platform)
//                        Glide.with(ctx)
//                            .load(data.user_img)
//                            .into(img_mypage_frag_profile_photo)
//                        txt_mypage_frag_farm_num.searchText = data.point.toString()
//                        txt_mypage_frag_pick_num.searchText = data.pick.toString()
//                        txt_mypage_frag_hifive_num.searchText = data.hifive.toString()
                        val data = response.body()!!.data
                        name.text = data.user_nickname
                        part.text = getUserType(data.user_type)
                        getPlatformType(data.detail_platform, platform)
                        Glide.with(ctx)
                            .load(data.user_img)
                            .into(profilePhoto)
                        farmNum.text = data.point.toString()
                        pickNum.text = data.pick.toString()
                        hifiveNum.text = data.hifive.toString()
                    }
            }

        })
    }

    private fun getUserType(p0: Int): String {
        return when (p0) {
            1 -> "Creator"
            2 -> "Editor"
            3 -> "Translator"
            4 -> "Etc."
            else -> ""
        }
    }

    private fun getPlatformType(p0: Int, imageView: ImageView) {
        when (p0) {
            0 -> imageView.visibility = View.GONE
            1 -> imageView.setImageResource(R.drawable.pofol_youtube_white_img)
            2 -> imageView.setImageResource(R.drawable.pofol_afreeca_white_img)
            3 -> imageView.setImageResource(R.drawable.pofol_twitch_white_img)
        }
    }

    // 버튼 구현
    private fun setOnBtnClikListener() {

        btn_toolbar_mypage_frag_setting.setOnClickListener {
            startActivity<ModifyActivity>()
        }

        ll_mypage_frag_mypick.setOnClickListener {
            startActivity<MyPickActivity>()
        }

        ll_mypage_frag_myhifive.setOnClickListener {
            val hifiveDialog = HifiveDialog()
            hifiveDialog.show(activity!!.supportFragmentManager, "hifive dialog")
        }

        ll_mypage_frag_mypofol.setOnClickListener {
            startActivity<ModifyPortFolioActivity>()
        }

        ll_mypage_frag_talk.setOnClickListener {
            val questionDialog = QuestionDialog()
            questionDialog.show(activity!!.supportFragmentManager, "question dialog")
        }
    }
}