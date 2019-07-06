package com.android.hipart_android.ui.hipart


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.hipart_android.R
import kotlinx.android.synthetic.main.fragment_contact_purchase.*
import org.jetbrains.anko.support.v4.toast

class ContactPurchaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_purchase, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setListeners()
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


}
