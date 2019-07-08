package com.android.hipart_android.ui.mypage.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.ui.home.data.post.PickResponse
import com.android.hipart_android.ui.mypage.data.PostManToManQuestionRequest
import com.android.hipart_android.util.SharedPreferenceController
import kotlinx.android.synthetic.main.dialog_question.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_question,container,false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setOnBtnClickListener()
    }

    private fun setOnBtnClickListener() {
        btn_question_dialog_ok.setOnClickListener {
            postManToManQuestion(edt_question_dialog_content.text.toString())
        }
    }

    private fun postManToManQuestion(comment : String) {
        val networkService = ApplicationController.instance.networkService

        val postManToMantQuestion = networkService.postManToManQusetion(
            SharedPreferenceController.getAuthorization(this@QuestionDialog.context!!),
            PostManToManQuestionRequest(comment)
        )

        postManToMantQuestion.enqueue(object: Callback<PickResponse>{
            override fun onFailure(call: Call<PickResponse>, t: Throwable) {
                Log.e("Question Dialog Err", Log.getStackTraceString(t))
            }

            override fun onResponse(call: Call<PickResponse>, response: Response<PickResponse>) {


                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        Log.v("요놈", it.message.toString())
                        if(it.message == "이메일 전송 성공")
                            dismiss()
                        else{
                            toast("이메일 전송 실패")
                        }
                    }

            }
        })
    }

}