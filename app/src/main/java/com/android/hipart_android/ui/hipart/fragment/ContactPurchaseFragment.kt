package com.android.hipart_android.ui.hipart.fragment


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.network.NetworkService
import com.android.hipart_android.ui.hipart.data.GetHifiveNumResponse
import com.android.hipart_android.util.Filter
import com.android.hipart_android.util.SharedPreferenceController
import kotlinx.android.synthetic.main.fragment_contact_purchase.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactPurchaseFragment : Fragment() {

    val networkService: NetworkService by lazy{
        ApplicationController.instance.networkService
    }

    private var nickname : String = ""
    private var type : Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        nickname = arguments!!.getString("nickname")
        type = arguments!!.getInt("type")

        return inflater.inflate(R.layout.fragment_contact_purchase, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//
        setView(nickname, type)



        setListeners()
    }


    private fun setView(nickname : String, type : Int) {
        tv_contact_purc_frag_name.text = nickname
        tv_contact_purc_frag_type.text = Filter.typePat(type)
        getHifiveNumResponse(nickname)
    }

    private fun setListeners() {
        iv_contact_purc_frag_back.setOnClickListener {
            activity!!.onBackPressed()
        }
        btn_contact_dial_frag_copy.setOnClickListener {
            val number = tv_contact_purc_frag_nunmber.text
            val cm = activity!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cm.apply {
                setPrimaryClip(ClipData.newPlainText(null, number))
                toast("번호가 복사되었습니다")
            }
            activity!!.onBackPressed()
        }
    }

    private fun getHifiveNumResponse(nickName:String) {
        val getHifiveNumResponse = networkService.getHifiveNumResponse("application/json",
            SharedPreferenceController.getAuthorization(activity!!)
            //SharedPreferenceController.getAuthorization(this@ContactPurchaseFragment.context!!)
            , nickName)
        getHifiveNumResponse.enqueue(object : Callback<GetHifiveNumResponse> {

            // 통신 실패 시
            override fun onFailure(call: Call<GetHifiveNumResponse>, t: Throwable) {
                Log.e("MyPageFragment Fail", t.toString())
            }

            // 통신 성공 시
            override fun onResponse(call: Call<GetHifiveNumResponse>, response: Response<GetHifiveNumResponse>) {
                if (response.isSuccessful) {
                    Log.v("Success!", response.body()!!.toString())
                    if (response.body()!!.message == "조회 성공") {
                        Log.v("Success!", response.body()!!.toString())
                        val data = response.body()!!.data

                        tv_contact_purc_frag_farm.text = "남은 팜: " + data.point.toString() + "개"
                        tv_contact_purc_frag_nunmber.text = data.number
                        Log.d("ContactFragment", "number : ${data.number}")
                    }
                }
            }
        })
    }


}
