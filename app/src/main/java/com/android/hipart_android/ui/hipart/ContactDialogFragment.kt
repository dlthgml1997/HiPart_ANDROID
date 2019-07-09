package com.android.hipart_android.ui.hipart

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.network.NetworkService
import com.android.hipart_android.ui.hipart.data.PostHifiveRequest
import com.android.hipart_android.ui.hipart.data.PostHifiveResponse
import com.android.hipart_android.util.SharedPreferenceController
import kotlinx.android.synthetic.main.fragment_contact_dialog.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactDialogFragment : DialogFragment() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    private val TAG = "ContactDialogFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contact_dialog, container, false)

        Log.d(TAG, "dialog created")
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        buttonChange()
    }

    private fun buttonChange() {
        ll_contact_dial_frag_term.setOnClickListener{
            if(btn_contact_dial_term_accept.isChecked){
                btn_contact_dial_term_accept.isChecked = false
            }else {
                btn_contact_dial_term_accept.isChecked = true
            }
        }
        btn_contact_dial_term_accept.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    btn_contact_dial_term_accept.setBackgroundResource(R.drawable.hipat_check_on_img)

                    btn_contact_dial_confirm.apply {
                        setBackgroundResource(R.drawable.bg_contact_alert_after)
                        setOnClickListener {
                            val fm = activity!!.supportFragmentManager
                            val fragmentTransaction = fm.beginTransaction()
                            fragmentTransaction.add(R.id.fl_hip_detail_act, ContactPurchaseFragment())
                            fragmentTransaction.addToBackStack(null)
                            fragmentTransaction.commit()

                            // 연락처 보기 통신
                            var nickName = "bj_ho"
                            postHifiveResponse(nickName)
                        }
                    }

                } else {
                    btn_contact_dial_term_accept.setBackgroundResource(R.drawable.hipat_check_off_img)
                    btn_contact_dial_confirm.setBackgroundResource(R.drawable.bg_contact_alert_before)
                }
            }
        })
    }
    private fun postHifiveResponse(nickname: String) {

        val networkService = ApplicationController.instance.networkService
        val postHifiveResponse = networkService.postHifiveResponse("application/json",
            SharedPreferenceController.getAuthorization(context!!),
            PostHifiveRequest(nickname)
        )
        postHifiveResponse.enqueue(object : Callback<PostHifiveResponse> {

            override fun onFailure(call: Call<PostHifiveResponse>, t: Throwable) {
                Log.e("Hifive Error", Log.getStackTraceString(t))
            }

            override fun onResponse(call: Call<PostHifiveResponse>, response: Response<PostHifiveResponse>) {
                // 연락 성공
                Log.v("청하", response.body()!!.message)
                response?.takeIf { it.isSuccessful }
                    ?.body()?.takeIf { it.message == "연락 성공" }
                    ?.let {
                        dismiss()
                    }

                // 포인트 부족
                Log.v("태그", response.body()!!.message)
                response?.takeIf { it.isSuccessful }
                    ?.body()?.takeIf { it.message == "포인트 부족" }
                    ?.let {
                        toast("코인이 부족합니다.")
                    }
            }
        })
    }
}
