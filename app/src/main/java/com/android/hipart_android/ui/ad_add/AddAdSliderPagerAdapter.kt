package com.android.hipart_android.ui.ad_add

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class AddAdSliderPagerAdapter(fm: FragmentManager?, val num_fragment: Int): FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment? {
        var fragment:AddAdFragment = AddAdFragment()
        var bundle: Bundle = Bundle(1)
        when(p0){
            0-> bundle.putString("background_url", "http")
            1-> bundle.putString("background_url", "http")
            2-> bundle.putString("background_url", "http")
        }
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int {
        return num_fragment
    }
}