package com.android.hipart_android.ui.portfolio

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import com.android.hipart_android.ui.main.MainActivity
import com.android.hipart_android.ui.modifyportfolio.ModifyPortFolioActivity
import com.android.hipart_android.util.SharedPreferenceController
import kotlinx.android.synthetic.main.fragment_portfolio.*
import org.jetbrains.anko.support.v4.startActivity

class PortFolioFragment : BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_portfolio, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setonBtnClickListener()
    }

    private fun setonBtnClickListener() {
        val userType =  SharedPreferenceController.getUserType(context!!)
        ll_port_frag_portfolio_upload.setOnClickListener {
            when(userType){
                1,2,4 ->{
                    startActivity<NotTpatUploadActivity>("nickName" to SharedPreferenceController.getNickName(this@PortFolioFragment.context!!))
                }
                3->{
                    startActivity<TpatUploadActivity>("nickName" to SharedPreferenceController.getNickName(this@PortFolioFragment.context!!))
                }
            }
        }

        ll_port_frag_portfolio_edit.setOnClickListener {
            startActivity<ModifyPortFolioActivity>()
        }

        img_port_frag_background.setOnClickListener {
            (context as MainActivity).removeFragment()
        }

    }



}