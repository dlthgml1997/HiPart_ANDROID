package com.android.hipart_android.ui.search


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.android.hipart_android.R
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_search_all.*
import kotlinx.android.synthetic.main.fragment_search_etc.*

class SearchAllFragment : Fragment() {

    private val TAG = "SearchAllFragment"

    lateinit var searchDataAll :ArrayList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search_all, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val any =arguments?.getParcelable<BaseParcelable>("searchListAll")?.value
        if(any != null) {
            searchDataAll = any as ArrayList<User>
            Log.d(TAG, "$searchDataAll.size")

            if (searchDataAll.isNotEmpty()) {
                ll_sear_all_frag_no_result.visibility = View.GONE
                rl_sear_all_frag_yes_result.visibility = View.VISIBLE


                setRecyclerView()
            }
        }

    }

    private fun setRecyclerView() {
        rv_sear_all_frag.adapter = SearchResultRecyclerViewAdapter(activity!!, searchDataAll)
        rv_sear_all_frag.layoutManager = LinearLayoutManager(activity!!, OrientationHelper.HORIZONTAL, false)
    }


}
