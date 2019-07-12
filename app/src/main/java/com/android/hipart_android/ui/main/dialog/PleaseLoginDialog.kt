package com.android.hipart_android.ui.main.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import com.android.hipart_android.ui.login.LoginActivity
import kotlinx.android.synthetic.main.dialog_please_login.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * Created by TakHyeongMin on 2019-07-12.
 */
class PleaseLoginDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_please_login, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setOnBtnClickListener()
    }

    private fun setOnBtnClickListener() {
        btn_dialog_please_login.setOnClickListener {
            startActivity<LoginActivity>()
        }
    }
}