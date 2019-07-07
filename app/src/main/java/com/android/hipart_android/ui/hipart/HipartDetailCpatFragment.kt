package com.android.hipart_android.ui.hipart


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.hipart_android.R
import kotlinx.android.synthetic.main.fragment_hipart_detail_cpat.*

class HipartDetailCpatFragment : Fragment(){

    private val TAG = "HipartDetailCpatfragmen"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hipart_detail_cpat, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setListeners()

    }


    private fun setListeners() {
        btn_hip_detail_frag_contact.setOnClickListener {

            val contactDialog = ContactDialogFragment()
            contactDialog.show(childFragmentManager, "contact dialog")
            Log.d(TAG, "contact btn clicked")


//            val contactDialog: ContactAlertDialog = ContactAlertDialog(activity!!)
//            contactDialog.show()
//

//            contactDialog.setOnCancelListener {
//                val fm = activity!!.supportFragmentManager
//                val fragmentTransaction = fm.beginTransaction()
//                fragmentTransaction.add(R.id.fl_hip_detail_act, ContactPurchaseFragment())
//                fragmentTransaction.addToBackStack(null)
//                fragmentTransaction.commit()
//            }
//            contactDialog.setOnDismissListener {
//
//            }
        }

    }



}
