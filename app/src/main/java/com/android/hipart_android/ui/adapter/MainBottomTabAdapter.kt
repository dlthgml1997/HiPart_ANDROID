package com.android.hipart_android.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.android.hipart_android.ui.hipart.HiPartFragment
import com.android.hipart_android.ui.home.HomeFragment
import com.android.hipart_android.ui.mypage.MyPageFragment
import com.android.hipart_android.ui.portfolio.PortFolioFragment

// ###Issue : FragmentPagerAdapter VS FragmentStateAdapter
class MainBottomTabAdapter(private val fragmentCount : Int, fm : FragmentManager) : FragmentPagerAdapter(fm){
    val home = HomeFragment()
    val hiPart = HiPartFragment()
    val portFolio = PortFolioFragment()
    val myPage = MyPageFragment()

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> home
            1 -> hiPart
            2 -> portFolio
            3 -> myPage
            else -> null
        }
    }

    // 현재 PagerAdapter에서 관리할 갯수를 반환 한다.
    override fun getCount(): Int = fragmentCount

    // ViewPager에서 사용할 뷰객체 생성 및 등록
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position)
    }
}