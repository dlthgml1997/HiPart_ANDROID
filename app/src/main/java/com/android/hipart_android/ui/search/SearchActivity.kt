package com.android.hipart_android.ui.search

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.ui.search.fragment.SearchAllFragment
import com.android.hipart_android.ui.search.fragment.SearchCpatFragment
import com.android.hipart_android.ui.search.fragment.SearchEpatFragment
import com.android.hipart_android.ui.search.get.GetSearchResponse
import com.android.hipart_android.ui.search.get.SearchUserDetail
import com.android.hipart_android.ui.search.get.User
import com.android.hipart_android.util.BaseActivity
import com.android.hipart_android.util.SearchData
import com.android.hipart_android.util.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_search.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchActivity : BaseActivity(), View.OnClickListener, KeyboardVisibilityEventListener {
    override fun onVisibilityChanged(isOpen: Boolean) {
        if (isOpen) {
            sv_search_act.scrollTo(0, sv_search_act.bottom)
            rl_search_act_search_button.visibility = View.VISIBLE
        } else {
            sv_search_act.scrollTo(0, sv_search_act.top)
            rl_search_act_search_button.visibility = View.GONE
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_act_search_cancel -> {
                finish()
            }

        }
    }

    private val TAG = "SearchActivity"

    private val networkService = ApplicationController.instance.networkService
    private lateinit var getSearchResponse: Call<GetSearchResponse>
    private val searchData = ArrayList<User>()
    private val searchDataForC = ArrayList<User>()
    private val searchDataForE = ArrayList<User>()
    private val searchDataForT = ArrayList<User>()
    private val searchDataForETC = ArrayList<User>()

    val imm by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val token: String =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrbmFtZSI6Iuq4sO2DgCIsImlkeCI6NiwidHlwZSI6NCwiaWF0IjoxNTYyNTY2OTgwLCJleHAiOjE1NjM3NzY1ODAsImlzcyI6ImlnIn0.Q2x2Z6OKdAs78ExzZk5zZvRNfsu9lL3Av3WJ05XB74g"

        setRecentSearchList()

        setSearch()

        setViewPager()

        setTablayout()

        setOnClickLister()

        btn_search_act_search.setOnClickListener {
            Log.d(TAG, "search button clicked")
            setViewPagerVisibility()

            getSearchResponse(token)

            setKeyboardListener()
        }

    }

    fun setRecentSearchList() {
        rv_search_act_history.adapter = SearchHistoryAdapter(SharedPreferenceController.getSearchHistory(this))
        rv_search_act_history.layoutManager = LinearLayoutManager(this)
    }

    private fun setKeyboardListener() {
        KeyboardVisibilityEvent.setEventListener(this, this)
        hideKeyboard()
    }

    private fun hideKeyboard() {
        imm.hideSoftInputFromWindow(et_search_act_search.windowToken, 0)

    }

    fun setViewPagerVisibility() {
        ll_search_act_recent_search.visibility = View.GONE
        rl_search_act_search_result.visibility = View.VISIBLE
    }

    fun getSearchResponse(token: String) {
        val searchText = et_search_act_search.text.toString()
        if (et_search_act_search.text.isNotEmpty()) {
            getSearchResponse = networkService.getSearchResponse("application/json", token, searchText)
            getSearchResponse.enqueue(object : Callback<GetSearchResponse> {
                override fun onFailure(call: Call<GetSearchResponse>, t: Throwable) {
                    toast(t.toString())
                    Log.e(TAG, t.toString())
                }

                override fun onResponse(call: Call<GetSearchResponse>, response: Response<GetSearchResponse>) {
                    if (response.isSuccessful) {
                        if (response.body()!!.status == 200) {
                            if (response.body()!!.data != null) {
                                Log.d(TAG, response.body()!!.message + response.body()!!.data.size.toString())
                                SearchData.searchDataAll = response.body()!!.data

                                filterSearchData(SearchData.searchDataAll)

                                var sameExist = false
                                if (SharedPreferenceController.searchHistoryList.size > 0) {
                                    for (i in 0..SharedPreferenceController.searchHistoryList.size - 1) {
                                        if (searchText == SharedPreferenceController.searchHistoryList[i])
                                            sameExist = true
                                    }
                                }
                                if (!sameExist) {
                                    SharedPreferenceController.addSearchHistory(this@SearchActivity, searchText)
                                }
                            }

                        }
                    }
                }
            })
        }
    }
//    private fun getSearchResponse(token: String) {
//        val userDetailDataList = ArrayList<SearchUserDetail>()
//        userDetailDataList.add(
//            SearchUserDetail(
//                "https://igbb.s3.ap-northeast-2.amazonaws.com/1562062301183.jpg",
//                "cuteyang",
//                1,
//                1,
//                1,
//                "sogae modify",
//                1,
//                2,
//                2,
//                3
//            )
//        )
//
//        searchData.add(User(0,userDetailDataList))
//
//        SearchData.searchDataAll = searchData
//
//        filterSearchData(searchData)
//    }

    private fun filterSearchData(data: ArrayList<User>) {
        for (i in 0..data.size - 1) {
            if (data[i].info[0].user_type == 1) {
                searchDataForC.removeAll(searchDataForC)
                searchDataForC.add(data[i])
                Log.d("searchDataForC", "added")
            } else if (data[i].info[0].user_type == 2) {
                searchDataForE.removeAll(searchDataForE)
                searchDataForE.add(data[i])
                Log.d("searchDataForE", "added")

            } else if (data[i].info[0].user_type == 3) {
                searchDataForT.removeAll(searchDataForT)
                searchDataForT.add(data[i])
                Log.d("searchDataForT", "added")
            } else {
                searchDataForETC.removeAll(searchDataForETC)
                searchDataForETC.add(data[i])
                Log.d("searchDataForETC", "added")
            }
        }
        SearchData.searchDataForC = searchDataForC
        SearchData.searchDataForE = searchDataForE
        SearchData.searchDataForT = searchDataForT
        SearchData.searchDataForETC = searchDataForETC

        this.onResume()

        Log.d("SearchData full size", "filter SearchData ${data.size}")
        Log.d("SearchDataC full size", "filter SearchData ${SearchData.searchDataForC.size}")
        Log.d("SearchDataE full size", "filter SearchData ${searchDataForE.size}")
        Log.d("SearchDataT full size", "filter SearchData ${SearchData.searchDataForT.size}")
        Log.d("SearchDataETC full size", "filter SearchData ${SearchData.searchDataForETC.size}")

    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        vp_search_act.adapter!!.notifyDataSetChanged()
        setViewPager()
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
                    rl_search_act_search_button.visibility = View.VISIBLE
                } else {
                    iv_search_act_search_text.setImageResource(R.drawable.search_search_icon)
                    rl_search_act_search_button.visibility = View.GONE
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

        val tabSearch: View =
            (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                .inflate(R.layout.tab_search_act, null, false)

        tl_search_act.getTabAt(0)!!.customView =
            tabSearch.findViewById(R.id.rl_tab_search_frag_all) as RelativeLayout
        tl_search_act.getTabAt(1)!!.customView = tabSearch.findViewById(R.id.rl_tab_search_frag_c) as RelativeLayout
        tl_search_act.getTabAt(2)!!.customView = tabSearch.findViewById(R.id.rl_tab_search_frag_e) as RelativeLayout
        tl_search_act.getTabAt(3)!!.customView = tabSearch.findViewById(R.id.rl_tab_search_frag_t) as RelativeLayout
        tl_search_act.getTabAt(4)!!.customView =
            tabSearch.findViewById(R.id.rl_tab_search_frag_etc) as RelativeLayout


//        tl_search_act.getTabAt(0)!!.customView = tabSearch.findViewById(R.id.rl_nav_category_main_all) as RelativeLayout
//        tl_search_act.getTabAt(1)!!.customView = tabSearch.findViewById(R.id.rl_nav_category_main_new) as RelativeLayout
//        tl_search_act.getTabAt(2)!!.customView = tabSearch.findViewById(R.id.rl_nav_category_main_end) as RelativeLayout

    }

    fun setOnClickLister() {
        btn_act_search_cancel.setOnClickListener(this)
    }
}


//
//        sendSearchDataToEachFragments()

//
//    private fun sendSearchDataToEachFragments() {
//        val bundleAll = Bundle()
//        bundleAll.putParcelableArrayList("searchListAll", SearchData.searchDataAll as ArrayList<out Parcelable>)
//        SearchAllFragment().arguments = bundleAll
//
//
//        val bundleC = Bundle()
//        bundleC.putParcelableArrayList("searchListC", SearchData.searchDataForC as ArrayList<out Parcelable>)
//        SearchCpatFragment().arguments = bundleC
//
//        val bundleE = Bundle()
//        bundleE.putParcelableArrayList("searchListE", SearchData.searchDataForE as ArrayList<out Parcelable>)
//        SearchEpatFragment().arguments = bundleE
//
//        val bundleETC = Bundle()
//        bundleE.putParcelableArrayList("searchListETC", SearchData.searchDataForETC as ArrayList<out Parcelable>)
//        SearchEpatFragment().arguments = bundleETC
//
//
//        Log.d(TAG, "put SearchData")
//
//
//        this.onResume()
//
//    }