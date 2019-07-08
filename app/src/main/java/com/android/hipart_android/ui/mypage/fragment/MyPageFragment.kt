package com.android.hipart_android.ui.mypage.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import com.android.hipart_android.ui.modifyprofile.ModifyActivity
import com.android.hipart_android.ui.mypage.MyPickActivity
import com.android.hipart_android.ui.mypage.dialog.HifiveDialog
import com.android.hipart_android.ui.mypage.dialog.QuestionDialog
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
        }

        ll_mypage_frag_talk.setOnClickListener {
            val questionDialog = QuestionDialog()
            questionDialog.show(activity!!.supportFragmentManager, "question dialog")
        }
    }
}