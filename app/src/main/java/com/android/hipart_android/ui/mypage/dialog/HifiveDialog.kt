package com.android.hipart_android.ui.mypage.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import kotlinx.android.synthetic.main.dialog_mypage_hifive.*
import kotlinx.android.synthetic.main.dialog_mypage_hifive.view.*

class HifiveDialog : DialogFragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_mypage_hifive, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setOnBtnClikListener()
    }

    private fun setOnBtnClikListener(){
        btn_dialog_mypage_hifive.setOnClickListener {
            dismiss()
        }
    }
}