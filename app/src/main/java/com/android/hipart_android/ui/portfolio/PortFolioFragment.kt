package com.android.hipart_android.ui.portfolio

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import com.android.hipart_android.ui.hipat.fragment.TPatHiPatFragment
import com.android.hipart_android.ui.main.MainActivity
import com.android.hipart_android.ui.portfolio.dialog.FilterDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_portfolio.*
import org.jetbrains.anko.startActivity
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
        btn_port_frag_upload_today_port.setOnClickListener {
            //startActivity<TpatUploadActivity>()
            startActivity<NotTpatUploadActivity>()
        }

        ll_port_frag_portfolio_edit.setOnClickListener {
            showDialog()
        }

        img_port_frag_background.setOnClickListener {
            activity as MainActivity
        }
    }

    private fun showDialog() {
        val filterDialog = FilterDialog()
        filterDialog.show(activity!!.supportFragmentManager, "filter dialog")
    }


}