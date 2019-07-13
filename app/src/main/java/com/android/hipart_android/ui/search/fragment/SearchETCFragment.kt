package com.android.hipart_android.ui.search.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.hipart_android.R
import com.android.hipart_android.ui.search.adapter.SearchResultEEtcRecyclerViewAdapter
import com.android.hipart_android.ui.search.get.User
import com.android.hipart_android.util.SearchData
import kotlinx.android.synthetic.main.fragment_search_etc.*

class SearchETCFragment : Fragment() {

    private val TAG = "SearchETCFragment"

    var searchDataETC =ArrayList<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        searchDataETC = SearchData.searchDataForETC

        return inflater.inflate(R.layout.fragment_search_etc, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//
//        val any : ArrayList<SearchItem> = arguments!!.getParcelableArrayList("searchListETC")
//
//        searchDataETC = any as ArrayList<User>
//        if(any != null) {
//            searchDataETC.addAll(any as ArrayList<User>)
//            Log.d(TAG, "$searchDataETC.size")
//        }
        if (searchDataETC.isNotEmpty()) {
            rl_sear_etc_frag_no_result.visibility = View.GONE
            rl_sear_etc_frag_yes_result.visibility = View.VISIBLE

            setRecyclerView()
        }
    }
    private fun setRecyclerView() {
        rv_sear_etc_frag.adapter =
            SearchResultEEtcRecyclerViewAdapter(activity!!, searchDataETC)
        rv_sear_etc_frag.layoutManager = LinearLayoutManager(activity!!, OrientationHelper.HORIZONTAL, false)
    }


}
