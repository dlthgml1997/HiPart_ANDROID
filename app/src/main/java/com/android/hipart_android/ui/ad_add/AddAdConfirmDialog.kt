package com.android.hipart_android.ui.ad_add

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import kotlinx.android.synthetic.main.dialog_addad_confirm.*

class AddAdConfirmDialog : DialogFragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_addad_confirm, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setOnBtnClikListener()
    }

    private fun setOnBtnClikListener(){
        btn_dialog_addad_confirm.setOnClickListener {

            (activity as AddAdCheckActivity).dismissFinish()

            activity?.setResult(-101)
            activity?.finish()

            dismiss()

        }
    }
}