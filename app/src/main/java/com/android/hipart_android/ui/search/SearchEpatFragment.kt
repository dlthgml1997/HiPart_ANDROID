package com.android.hipart_android.ui.search


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.hipart_android.R
import kotlinx.android.synthetic.main.fragment_search_etc.*

class SearchEpatFragment : Fragment() {
    private val TAG = "SearchEpatFragment"

    var searchDataE = ArrayList<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_search_etc, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val any =arguments?.getParcelable<BaseParcelable>("searchListE")?.value
        if(any != null) {
            searchDataE.addAll(any as ArrayList<User>)
            Log.d(TAG, "$searchDataE.size")
        }

        if(searchDataE.isNotEmpty()) {
            rl_sear_etc_frag_no_result.visibility = View.GONE
            rl_sear_etc_frag_yes_result.visibility = View.VISIBLE
        }


    }
    private fun setRecyclerView() {
        rv_sear_etc_frag.adapter = SearchResultRecyclerViewAdapter(activity!!, searchDataE)
        rv_sear_etc_frag.layoutManager = LinearLayoutManager(activity!!, OrientationHelper.HORIZONTAL, false)
    }




}
