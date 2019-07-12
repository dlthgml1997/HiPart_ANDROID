package com.android.hipart_android.ui.hipart


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import com.android.hipart_android.ui.hipart.get.UserDetailTData


import com.android.hipart_android.util.Filter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_hipart_detail_eetc.*

class HipartDetailTpatFragment : Fragment() {

    private val TAG = "HipartDetailTFrag"

    lateinit var user: UserDetailTData
    var pickBtnFlag = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        user = arguments!!.getSerializable("user") as UserDetailTData
        Log.d(TAG, user.user_nickname)
        return inflater.inflate(R.layout.fragment_hipart_detail_eetc, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setListeners()
        setView()


    }

    private fun setView() {
        ll_hip_det_eetc_frag_subscribe.visibility = View.GONE

        Glide.with(activity!!)
            .load(user.user_img)
            .into(iv_frag_hip_det_eetc_photo)

        tv_hip_det_frag_eetc_name.text = user.user_nickname
        tv_hip_det_eetc_frag_pick_num.text = user.pick.toString()

        tv_frag_hip_det_eetc_type.text = Filter.type(user.user_type)

        if (user.pd != 0) {
            tv_frag_hip_det_eetc_pd.text = Filter.pd(user.pd)
        } else
            rl_hip_det_eetc_frag_pd_background.visibility = View.GONE


        setTagList(user)

        tv_hip_det_eetc_frag_intro.text = user.detail_oneline
        img_hipart_detail_work.setImageResource(R.drawable.hipat_tpat_work_adot_img)

        setArticleList(user)

        tv_frag_hip_det_eetc_hif_num.text =user.hifive.toString()

        tv_frag_hip_det_eetc_want.text = user.detail_want
        tv_frag_hip_det_eetc_spec.text = user.detail_appeal

        //연락하기/연락처 보기
        if (HipartDetailActivity.hifiveStatus == 1) {
            btn_frag_hip_det_eetc_call.text = "연락처 보기"
        } else
            btn_frag_hip_det_eetc_call.text = "연락하기"
    }

    private fun setArticleList(user: UserDetailTData) {
        val translationList = ArrayList<HipartDetailTranslationData>()

        if (user.before!!.isNotEmpty()) {
            ll_hipat_detail_frag_no_work.visibility = View.GONE
            for (i in 0..user.before.size - 1)
                translationList.add(HipartDetailTranslationData(user.before[i], user.after!![i]))
            rv_frag_hip_det_frag_article.adapter = HipartDetailTranslationAdapter(activity!!, translationList)
        } else {
            ll_hipat_detail_frag_no_work.visibility = View.VISIBLE
        }
    }

    private fun setTagList(user: UserDetailTData) {
        val tagList = ArrayList<String>()
        if (user.concept != 0)
            tagList.add(Filter.concept(user.concept))
        if (user.lang != 0)
            tagList.add(Filter.language(user.concept))
        if (user.concept != 0)
            tagList.add(Filter.etc(user.concept))

        if (tagList.size > 0) {
            rv_frag_hip_det_eetc_tag.adapter = HipartDetailTagRecyclerAdapter(activity!!, tagList)
            rv_frag_hip_det_eetc_tag.layoutManager =
                LinearLayoutManager(activity!!, OrientationHelper.HORIZONTAL, false)
        }
    }

    private fun setListeners() {
        btn_frag_hip_det_eetc_call.setOnClickListener {
            if (HipartDetailActivity.hifiveStatus == 0) {
                val contactDialog = ContactDialogFragment()
                contactDialog.show(childFragmentManager, "contact dialog")
                Log.d(TAG, "contact btn clicked")
            } else {
                val fm = activity!!.supportFragmentManager
                val fragmentTransaction = fm.beginTransaction()
                fragmentTransaction.add(R.id.fl_hip_detail_act, ContactPurchaseFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }

        }
        iv_frag_hip_det_eetc_back.setOnClickListener {
            (context as HipartDetailActivity).finish()
        }

        btn_frag_hip_det_eetc_pick_off.setOnClickListener{
            (context as HipartDetailActivity).setAnimPickIcon()
            iv_frag_hip_det_eetc_pick_off.setImageResource(R.drawable.pofol_pick_off_white_icon)
        }

    }


}


