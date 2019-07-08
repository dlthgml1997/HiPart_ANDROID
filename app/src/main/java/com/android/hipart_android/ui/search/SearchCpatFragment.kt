package com.android.hipart_android.ui.search


import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.hipart_android.R
import kotlinx.android.synthetic.main.fragment_search_cpat.*

class SearchCpatFragment : Fragment() {

    private val TAG = "SearchCpatFragment"

    var searchDataC = ArrayList<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_search_cpat, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val any =arguments?.getParcelable<BaseParcelable>("searchListC")?.value
        if(any != null) {
            searchDataC.addAll( any as ArrayList<User> )
            Log.d(TAG, "$searchDataC.size")
        }

        if (searchDataC.isNotEmpty()) {
            ll_sear_c_frag_no_result.visibility = View.GONE
            rl_sear_c_frag_yes_result.visibility = View.VISIBLE
        }
    }

}
