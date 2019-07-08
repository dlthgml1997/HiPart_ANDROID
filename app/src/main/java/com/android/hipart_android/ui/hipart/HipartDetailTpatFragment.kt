package com.android.hipart_android.ui.hipart


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import kotlinx.android.synthetic.main.fragment_hipart_detail_tpat.*

class HipartDetailTpatFragment : Fragment() {

    private val TAG = "HipartDetailTFrag"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hipart_detail_tpat, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setListeners()


    }

    private fun setListeners() {
        val contactDialog = ContactDialogFragment()
        contactDialog.show(childFragmentManager, "contact dialog")
        Log.d(TAG, "contact btn clicked")

        iv_frag_hip_det_t_back.setOnClickListener {
            (context as HipartDetailActivity).finish()
        }

    }



}


