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
            0-> bundle.putString("background_url", "https://image.ajunews.com/content/image/2015/02/24/20150224102559716635.jpg")
            1-> bundle.putString("background_url", "https://imgnews.pstatic.net/image/417/2017/03/02/2017022714268028180_1_99_20170302174602.jpg?type=w647")
            2-> bundle.putString("background_url", "https://post-phinf.pstatic.net/MjAxNzAzMDNfMzUg/MDAxNDg4NTAzNzU1MjY5.pc9QkkeZl5SvGA4MmrLzXRj1gM20RRD_dVPpg5V_bEgg.jZ317mvtBZzRStiHcPGuTCHU9PvgtpqLtwYSB666nFEg.PNG/4.PNG?type=w1200")
        }
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int {
        return num_fragment
    }
}