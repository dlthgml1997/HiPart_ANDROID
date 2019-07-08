package com.android.hipart_android.ui.search


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.hipart_android.R

class SearchTpatFragment : Fragment() {

//    private val TAG = "SearchTpatFragment"
//
//    val searchDataT = ArrayList<User>()
//

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_tpat, container, false)
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        val any = arguments?.getParcelable<BaseParcelable>("searchListT")?.value
//        if(any != null) {
//            searchDataT.addAll(any as ArrayList<User>)
//            Log.d(TAG, "$searchDataT.size")
//        }
//
//        if( searchDataT.isNotEmpty()) {
//
//        }
//    }

}
