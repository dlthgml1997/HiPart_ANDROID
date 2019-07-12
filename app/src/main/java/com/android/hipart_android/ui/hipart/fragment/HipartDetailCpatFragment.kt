package com.android.hipart_android.ui.hipart.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.hipart_android.R
import com.android.hipart_android.ui.hipart.HipartDetailActivity
import com.android.hipart_android.ui.hipart.HipartDetailArticleData
import com.android.hipart_android.ui.hipart.HipartDetailCpatArticleAdapter
import com.android.hipart_android.ui.hipart.HipartDetailTagRecyclerAdapter
import com.android.hipart_android.ui.hipart.get.UserDetailCData
import com.android.hipart_android.util.Filter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_hipart_detail_eetc.*
import java.util.*

class HipartDetailCpatFragment : Fragment() {

    private val TAG = "HipartDetailCpatfragmen"

    lateinit var user: UserDetailCData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        user = arguments!!.getSerializable("user") as UserDetailCData
        Log.d(TAG, user.user_nickname)

        return inflater.inflate(R.layout.fragment_hipart_detail_eetc, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setListeners()

        setView()

    }

    private fun setView() {
        ll_hip_det_eetc_frag_spec.visibility = View.GONE

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
        tv_frag_hip_det_eetc_subscribe_num.text =user.detail_subscriber
        tv_frag_hip_det_eetc_hif_num.text = user.hifive.toString()
        tv_hip_det_eetc_frag_intro.text = user.detail_oneline
        tv_frag_hip_det_eetc_hif_num.text

        setArticleList(user)

        tv_frag_hip_det_eetc_want.text = user.detail_want
        tv_frag_hip_det_eetc_spec.text = user.detail_appeal

        //연락하기/연락처 보기
        if (HipartDetailActivity.hifiveStatus == 1) {
            btn_frag_hip_det_eetc_call.text = "연락처 보기"
        } else
            btn_frag_hip_det_eetc_call.text = "연락하기"

    }

    private fun setArticleList(user: UserDetailCData) {
        val articleList = ArrayList<HipartDetailArticleData>()
        if (user.thumbnail!!.isNotEmpty()) {
            ll_hipat_detail_frag_no_work.visibility = View.GONE
            for (i in 0..user.thumbnail.size - 1)
                articleList.add(
                    HipartDetailArticleData(
                        user.thumbnail[i],
                        user.title!![i],
                        user.content!![i],
                        user.url!![i]
                    )
                )

            rv_frag_hip_det_frag_article.adapter =
                HipartDetailCpatArticleAdapter(activity!!, articleList)
            rv_frag_hip_det_frag_article.layoutManager =
                LinearLayoutManager(activity!!, OrientationHelper.HORIZONTAL, false)
        }else{
            ll_hipat_detail_frag_no_work.visibility = View.VISIBLE
        }
    }


    private fun setTagList(user: UserDetailCData) {
        val tagList = ArrayList<String>()
        if (user.concept != 0)
            tagList.add(Filter.concept(user.concept))
        if (user.lang != 0)
            tagList.add(Filter.language(user.concept))
        if (user.concept != 0)
            tagList.add(Filter.etc(user.concept))

        if (tagList.size > 0) {
            rv_frag_hip_det_eetc_tag.adapter =
                HipartDetailTagRecyclerAdapter(activity!!, tagList)
            rv_frag_hip_det_eetc_tag.layoutManager =
                LinearLayoutManager(activity!!, OrientationHelper.HORIZONTAL, false)
        }
    }


    private fun setListeners() {
        btn_frag_hip_det_eetc_call.setOnClickListener {


            if (HipartDetailActivity.hifiveStatus == 0) {

                val contactDialog = ContactDialogFragment()

                val bundle = Bundle()
                bundle.putString("nickname", user.user_nickname)
                bundle.putInt("type", user.user_type)
                contactDialog.arguments = bundle

                contactDialog.show(childFragmentManager, "contact dialog")
                Log.d(TAG, "contact btn clicked")
            } else {
                val purchaseFragment = ContactPurchaseFragment()

                val bundle = Bundle()
                bundle.putString("nickname", user.user_nickname)
                bundle.putInt("type", user.user_type)
                purchaseFragment.arguments = bundle

                val fm = activity!!.supportFragmentManager
                val fragmentTransaction = fm.beginTransaction()
                fragmentTransaction.add(R.id.fl_hip_detail_act, purchaseFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }


        iv_frag_hip_det_eetc_back.setOnClickListener {
            (context as HipartDetailActivity).finish()
        }


//            val contactDialog: ContactAlertDialog = ContactAlertDialog(activity!!)
//            contactDialog.show()
//

//            contactDialog.setOnCancelListener {
//                val fm = activity!!.supportFragmentManager
//                val fragmentTransaction = fm.beginTransaction()
//                fragmentTransaction.add(R.id.fl_hip_detail_act, ContactPurchaseFragment())
//                fragmentTransaction.addToBackStack(null)
//                fragmentTransaction.commit()
//            }
//            contactDialog.setOnDismissListener {
//
//            }


    }


}
