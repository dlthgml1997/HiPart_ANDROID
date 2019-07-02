package com.android.hipart_android.ui.hipat.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import com.android.hipart_android.ui.hipat.fragment.*

class HipatFragPortViewPagerAdapter(fm: FragmentManager, private val num_fragment: Int) : FragmentPagerAdapter(fm) {
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
            0 -> getAllHiPatFragment()
            1 -> getCPatHiPatFragment()
            2 -> getEPatHiPatFragment()
            3 -> getTPatHiPatFragment()
            4 -> getEtcHiPatFragment()
            else -> null
        }
    }

    override fun getCount(): Int = num_fragment
}