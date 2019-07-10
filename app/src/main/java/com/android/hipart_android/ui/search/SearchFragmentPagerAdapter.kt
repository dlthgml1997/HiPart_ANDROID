package com.android.hipart_android.ui.search

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.android.hipart_android.ui.search.fragment.*

class SearchFragmentPagerAdapter(val num : Int, val fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        return when(position) {
            0 -> SearchAllFragment()
            1 -> SearchCpatFragment()
            2 -> SearchEpatFragment()
            3 -> SearchTpatFragment()
            4 -> SearchETCFragment()
            else -> null
        }
    }

    override fun getCount(): Int = num
}