package com.android.hipart_android.ui.hipart

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.android.hipart_android.R
import kotlinx.android.synthetic.main.fragment_contact_dialog.*

class ContactDialogFragment : DialogFragment() {

    private val TAG = "ContactDialogFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contact_dialog, container, false)

        Log.d(TAG, "dialog created")
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        buttonChange()
    }

    private fun buttonChange() {
        ll_contact_dial_frag_term.setOnClickListener{
            if(btn_contact_dial_term_accept.isChecked){
                btn_contact_dial_term_accept.isChecked = false
            }else {
                btn_contact_dial_term_accept.isChecked = true
            }
        }
        btn_contact_dial_term_accept.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    btn_contact_dial_term_accept.setBackgroundResource(R.drawable.hipat_check_on_img)

                    btn_contact_dial_confirm.apply {
                        setBackgroundResource(R.drawable.bg_contact_alert_after)
                        setOnClickListener {
                            val fm = activity!!.supportFragmentManager
                            val fragmentTransaction = fm.beginTransaction()
                            fragmentTransaction.add(R.id.fl_hip_detail_act, ContactPurchaseFragment())
                            fragmentTransaction.addToBackStack(null)
                            fragmentTransaction.commit()
                            dismiss()

                        }
                    }

                } else {
                    btn_contact_dial_term_accept.setBackgroundResource(R.drawable.hipat_check_off_img)
                    btn_contact_dial_confirm.setBackgroundResource(R.drawable.bg_contact_alert_before)
                }
            }
        })


    }
}
