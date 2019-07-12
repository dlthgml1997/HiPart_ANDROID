package com.android.hipart_android.ui.hipat.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.android.hipart_android.ui.hipat.fragment.*

class HipatFragPortViewPagerAdapter(fm: FragmentManager, private val num_fragment: Int) : FragmentStatePagerAdapter(fm) {
    companion object {
        private var allHiPatFragment: AllHipatFragment? = null
        private var cPatHiPatFragment: CPatHiPatFragment? = null
        private var ePatHiPatFragment: EPatHiPatFragment? = null
        private var tPatHiPatFragment: TPatHiPatFragment? = null
        private var etcHiPatFragment: EtcHiPatFragment? = null

        @Synchronized
        fun getAllHiPatFragment(): AllHipatFragment {
            if (allHiPatFragment == null) allHiPatFragment = AllHipatFragment()
            return allHiPatFragment!!
        }

        @Synchronized
        fun getCPatHiPatFragment(): CPatHiPatFragment {
            if (cPatHiPatFragment == null) cPatHiPatFragment = CPatHiPatFragment()
            return cPatHiPatFragment!!
        }

        @Synchronized
        fun getEPatHiPatFragment(): EPatHiPatFragment {
            if (ePatHiPatFragment == null) ePatHiPatFragment = EPatHiPatFragment()
            return ePatHiPatFragment!!
        }

        @Synchronized
        fun getTPatHiPatFragment(): TPatHiPatFragment {
            if (tPatHiPatFragment == null) tPatHiPatFragment = TPatHiPatFragment()
            return tPatHiPatFragment!!
        }

        @Synchronized
        fun getEtcHiPatFragment(): EtcHiPatFragment {
            if (etcHiPatFragment == null) etcHiPatFragment = EtcHiPatFragment()
            return etcHiPatFragment!!
        }
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> AllHipatFragment()
            1 -> CPatHiPatFragment()
            2 -> EPatHiPatFragment()
            3 -> TPatHiPatFragment()
            4 -> EtcHiPatFragment()
            else -> null
        }
    }

    override fun getCount(): Int = num_fragment
}