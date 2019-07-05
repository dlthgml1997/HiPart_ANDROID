package com.android.hipart_android.ui.mypage.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import com.android.hipart_android.ui.mypage.ModifyActivity
import com.android.hipart_android.ui.mypage.MyPickActivity
import com.android.hipart_android.ui.mypage.dialog.HifiveDialog
import com.android.hipart_android.ui.mypage.dialog.QuestionDialog
import kotlinx.android.synthetic.main.dialog_mypage_hifive.*
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.jetbrains.anko.support.v4.startActivity

class MyPageFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mypage, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setOnBtnClikListener()
    }

    private fun setOnBtnClikListener(){
        ll_mypage_frg_myhifive.setOnClickListener {
            val hifiveDialog = HifiveDialog()
            hifiveDialog.show(activity!!.supportFragmentManager, "hifive dialog")
        }

        ll_mypage_frg_talk.setOnClickListener {
            val questionDialog = QuestionDialog()
            questionDialog.show(activity!!.supportFragmentManager, "question dialog")
        }
    }
}