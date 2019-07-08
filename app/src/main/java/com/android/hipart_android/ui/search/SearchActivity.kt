package com.android.hipart_android.ui.search

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "SearchActivity"

    private val networkService = ApplicationController.instance.networkService
    private lateinit var getSearchResponse: Call<GetSearchResponse>
    private val searchData = ArrayList<User>()
    private val searchDataForC = ArrayList<User>()
    private val searchDataForE = ArrayList<User>()
    private val searchDataForT = ArrayList<User>()
    private val searchDataForETC = ArrayList<User>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSearch()

        setViewPager()

        setTablayout()

        setOnClickLister()

        btn_search_act_search.setOnClickListener {
            Log.d("TAG", "search button clicked")
            setViewPagerVisibility()
            getSearchResponse()
        }


    }

    override fun onClick(v: View?) {
        when (v) {
            btn_act_search_cancel -> {
                finish()
            }

        }
    }

    private fun setViewPagerVisibility() {
        ll_search_act_recent_search.visibility = View.GONE
        rl_search_act_search_result.visibility = View.VISIBLE
    }

    private fun getSearchResponse() {
        val searchText = et_search_act_search.text.toString()
        getSearchResponse = networkService.getSearchResponse("application/json", searchText)
        getSearchResponse.enqueue(object : Callback<GetSearchResponse> {
            override fun onFailure(call: Call<GetSearchResponse>, t: Throwable) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<GetSearchResponse>, response: Response<GetSearchResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        searchData.addAll(response.body()!!.data)

                        filterSearchData(searchData)

                    }
                }
            }
        })
    }

    private fun filterSearchData(data: ArrayList<User>) {
        for (i in 0..data.size-1) {
            if (data[i].user_type == 1) {
                searchDataForC.add(data[i])
            } else if (data[i].user_type == 2) {
                searchDataForE.add(data[i])
            } else if (data[i].user_type == 3) {
                searchDataForT.add(data[i])
            } else if (data[i].user_type == 4) {
                searchDataForETC.add(data[i])
            }
        }

        sendSearchDataToEachFragments()
    }

    private fun sendSearchDataToEachFragments() {
        val bundleAll = Bundle()
        bundleAll.putParcelable("searchListAll", BaseParcelable(searchData))
        SearchAllFragment().arguments = bundleAll

        val bundleC = Bundle()
        bundleC.putParcelable("searchListC", BaseParcelable(searchDataForC))
        SearchCpatFragment().arguments = bundleC

        val bundleE = Bundle()
        bundleE.putParcelable("searchListE", BaseParcelable(searchDataForE))
        SearchEpatFragment().arguments = bundleE

        val bundleETC = Bundle()
        bundleE.putParcelable("searchListETC", BaseParcelable(searchDataForETC))
        SearchEpatFragment().arguments = bundleETC

        vp_search_act.adapter!!.notifyDataSetChanged()
    }


    private fun setTablayout() {
        vp_search_act.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                tl_search_act.getTabAt(p0)?.select()
//                when(p0) {
//                    0 -> {
//                        tl_search_act.getTabAt(0)!!(Color.parseColor("#7947fd"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                    }
//                    1 -> {
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#7947fd"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                    }
//                    2 -> {
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#7947fd"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                    }
//                    3 -> {
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#7947fd"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                    }
//                    4 -> {
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#484848"))
//                        tv_tab_search_act_all.setTextColor(Color.parseColor("#7947fd"))
//                    }
//
//                }
            }
        })
    }

    private fun setSearch() {


        et_search_act_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().length > 0) {
                    iv_search_act_search_text.setImageResource(R.drawable.search_x_big_icon)
                } else {
                    iv_search_act_search_text.setImageResource(R.drawable.search_search_icon)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun setViewPager() {
        vp_search_act.adapter = SearchFragmentPagerAdapter(5, supportFragmentManager)
        tl_search_act.setupWithViewPager(vp_search_act)

        val tabSearch: View = (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.tab_search_act, null, false)

        tl_search_act.getTabAt(0)!!.customView = tabSearch.findViewById(R.id.rl_tab_search_frag_all) as RelativeLayout
        tl_search_act.getTabAt(1)!!.customView = tabSearch.findViewById(R.id.rl_tab_search_frag_c) as RelativeLayout
        tl_search_act.getTabAt(2)!!.customView = tabSearch.findViewById(R.id.rl_tab_search_frag_e) as RelativeLayout
        tl_search_act.getTabAt(3)!!.customView = tabSearch.findViewById(R.id.rl_tab_search_frag_t) as RelativeLayout
        tl_search_act.getTabAt(4)!!.customView = tabSearch.findViewById(R.id.rl_tab_search_frag_etc) as RelativeLayout


//        tl_search_act.getTabAt(0)!!.customView = tabSearch.findViewById(R.id.rl_nav_category_main_all) as RelativeLayout
//        tl_search_act.getTabAt(1)!!.customView = tabSearch.findViewById(R.id.rl_nav_category_main_new) as RelativeLayout
//        tl_search_act.getTabAt(2)!!.customView = tabSearch.findViewById(R.id.rl_nav_category_main_end) as RelativeLayout

    }

    fun setOnClickLister() {
        btn_act_search_cancel.setOnClickListener(this)
    }
}
