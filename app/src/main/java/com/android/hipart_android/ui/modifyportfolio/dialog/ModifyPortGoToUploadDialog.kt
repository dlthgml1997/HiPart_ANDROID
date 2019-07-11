package com.android.hipart_android.ui.modifyportfolio.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import com.android.hipart_android.ui.modifyportfolio.ModifyPortFolioActivity
import com.android.hipart_android.ui.portfolio.NotTpatUploadActivity
import com.android.hipart_android.ui.portfolio.TpatUploadActivity
import com.android.hipart_android.util.SharedPreferenceController
import kotlinx.android.synthetic.main.dialog_modify_port_go_to_upload.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class ModifyPortGoToUploadDialog : DialogFragment() {

    private val userType by lazy {
        SharedPreferenceController.getUserType(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_modify_port_go_to_upload, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setOnBtnClickListener()
    }

    private fun setOnBtnClickListener() {
        txt_go_to_upload_cancel.setOnClickListener {
            dismiss()
        }
        txt_go_to_upload_ok.setOnClickListener {
            when (userType) {
                1, 2, 4 -> {
                    startActivity<NotTpatUploadActivity>("nickName" to SharedPreferenceController.getNickName(this@ModifyPortGoToUploadDialog.context!!))
                    (context as ModifyPortFolioActivity).finish()
            }
                3 -> {
                    startActivity<TpatUploadActivity>("nickName" to SharedPreferenceController.getNickName(this@ModifyPortGoToUploadDialog.context!!))
                    (context as ModifyPortFolioActivity).finish()
                }
                else-> {
                    toast("유저타입이 잘못되었습니다.")
                }
            }
        }
    }
}