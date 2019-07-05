package com.android.hipart_android.ui.mypage.dialog

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import kotlinx.android.synthetic.main.dialog_mypage_check_again.*

class ModifyActCheckAgainDialog : DialogFragment() {

    private val modifyActSuccessDialog by lazy {
        ModifyActSuccessDialog()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_mypage_check_again,container,false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setOnBtnClickListener()
    }

    private fun setOnBtnClickListener() {
        txt_check_again_cancel.setOnClickListener {
            dismiss()
        }
        txt_check_again_ok.setOnClickListener {
            dismiss()
            modifyActSuccessDialog.show(activity!!.supportFragmentManager, "modify success")
            val handler = Handler()
            handler.postDelayed({
                run {
                    modifyActSuccessDialog.dismiss()
                }
            }, 1500)
        }
    }
}