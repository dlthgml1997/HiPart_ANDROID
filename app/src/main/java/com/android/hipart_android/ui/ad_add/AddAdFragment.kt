package com.android.hipart_android.ui.ad_add

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_add_ad.*

class AddAdFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_ad, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val Img_url:String = arguments!!.getString("background_url")

        Glide.with(this)
            .load(Img_url)
            .into(img_fragment_add_ad)
    }
}

