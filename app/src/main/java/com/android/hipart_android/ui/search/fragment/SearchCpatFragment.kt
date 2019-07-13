package com.android.hipart_android.ui.search.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.hipart_android.R
import com.android.hipart_android.ui.search.adapter.SearchResultCpatRecyclerViewAdapter
import com.android.hipart_android.ui.search.adapter.SearchResultEEtcRecyclerViewAdapter
import com.android.hipart_android.ui.search.get.User
import com.android.hipart_android.util.SearchData
import kotlinx.android.synthetic.main.fragment_search_etc.*

class SearchCpatFragment : Fragment() {

    private val TAG = "SearchCpatFragment"

    var searchDataC = ArrayList<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        searchDataC = SearchData.searchDataForC

        return inflater.inflate(R.layout.fragment_search_etc, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//
//        val any : ArrayList<SearchItem> = arguments!!.getParcelableArrayList("searchListC")
//
//        searchDataC = any as ArrayList<User>
////        if(any != null) {
//
////            searchDataC.addAll( any as ArrayList<User> )
//        Log.d(TAG, "$searchDataC.size")
//    }
        if (searchDataC.isNotEmpty()) {
            rl_sear_etc_frag_no_result.visibility = View.GONE
            rl_sear_etc_frag_yes_result.visibility = View.VISIBLE

            setRecyclerView()
        }
    }

    private fun setRecyclerView() {
        rv_sear_etc_frag.adapter =
            SearchResultCpatRecyclerViewAdapter(activity!!, searchDataC)
        rv_sear_etc_frag.layoutManager = LinearLayoutManager(activity!!, OrientationHelper.HORIZONTAL, false)
    }

}
