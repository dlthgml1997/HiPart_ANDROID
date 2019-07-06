package com.android.hipart_android.ui.modifyportfolio

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.android.hipart_android.R
import com.android.hipart_android.ui.modifyportfolio.adapter.FilterRVAdapter
import com.android.hipart_android.ui.modifyportfolio.adapter.TransWorkRVAdapter
import com.android.hipart_android.ui.modifyportfolio.adapter.WorkRVAdapter
import com.android.hipart_android.ui.modifyportfolio.data.FilterData
import com.android.hipart_android.ui.modifyportfolio.data.TransWorkData
import com.android.hipart_android.ui.modifyportfolio.data.WorkData
import com.android.hipart_android.ui.portfolio.dialog.FilterDialog
import com.android.hipart_android.util.BaseActivity
import kotlinx.android.synthetic.main.activity_modify_port_folio.*

class ModifyPortFolioActivity : BaseActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v) {

            btn_modify_port_folio_back -> {
                finish()
            }

            btn_youtube -> {
                btn_youtube.isSelected = !btn_youtube.isSelected
            }

            btn_afreeca -> {
                btn_afreeca.isSelected = !btn_afreeca.isSelected
            }

            btn_twich -> {
                btn_twich.isSelected = !btn_twich.isSelected
            }

            // 필터 수정하기
            btn_modify_port_folio_act_modify_filter -> {
                filterDialog.show(supportFragmentManager,"filter dialog")
            }

            // 작품 올리기
            btn_modify_port_folio_act_work_add_work -> {

            }

            // 취소하기
            btn_modify_port_folio_cancel -> {
                finish()
            }

            // 수정 완료
            btn_modify_port_folio_confirm -> {

            }
        }
    }

    private val filterDateList by lazy {
        ArrayList<FilterData>()
    }

    private val filterRVAdapter by lazy {
        FilterRVAdapter(this@ModifyPortFolioActivity, filterDateList)
    }

    private val workDateList by lazy {
        ArrayList<WorkData>()
    }

    private val workRVAdapter by lazy {
        WorkRVAdapter(this@ModifyPortFolioActivity, workDateList)
    }

    private val transWorkDateList by lazy {
        ArrayList<TransWorkData>()
    }

    private val transWorkRVAdapter by lazy {
        TransWorkRVAdapter(this@ModifyPortFolioActivity, transWorkDateList)
    }

    private val filterDialog by lazy {
        FilterDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_port_folio)

        initView()

    }

    private fun initView() {
        setFilterRVAdapter()
        setTransWorkRVAdapter()
        setOnClickListener()
        // setWorkRVAdapter()

    }

    private fun setFilterRVAdapter() {
        filterDateList.add(FilterData("기획", true))
        filterDateList.add(FilterData("먹방", false))
        filterDateList.add(FilterData("영어", false))
        filterDateList.add(FilterData("소품", false))

        rv_modify_port_folio_act_filter.adapter = filterRVAdapter
        rv_modify_port_folio_act_filter.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setWorkRVAdapter() {
        workDateList.add(
            WorkData(
                "https://i.pinimg.com/236x/ae/c9/ea/aec9eadd89aa51a9b753b221f3bcce12.jpg"
                , "벤쯔의 먹방"
                , "많이먹어요~"
            )
        )

        workDateList.add(
            WorkData(
                "https://i.pinimg.com/236x/ae/c9/ea/aec9eadd89aa51a9b753b221f3bcce12.jpg"
                , "벤쯔의 먹방"
                , "많이먹어요~"
            )
        )

        workDateList.add(
            WorkData(
                "https://i.pinimg.com/236x/ae/c9/ea/aec9eadd89aa51a9b753b221f3bcce12.jpg"
                , "벤쯔의 먹방"
                , "많이먹어요~"
            )
        )

        workDateList.add(
            WorkData(
                "https://i.pinimg.com/236x/ae/c9/ea/aec9eadd89aa51a9b753b221f3bcce12.jpg"
                , "벤쯔의 먹방"
                , "많이먹어요~"
            )
        )

        workDateList.add(
            WorkData(
                "https://i.pinimg.com/236x/ae/c9/ea/aec9eadd89aa51a9b753b221f3bcce12.jpg"
                , "벤쯔의 먹방"
                , "많이먹어요~"
            )
        )

        workDateList.add(
            WorkData(
                "https://i.pinimg.com/236x/ae/c9/ea/aec9eadd89aa51a9b753b221f3bcce12.jpg"
                , "벤쯔의 먹방"
                , "많이먹어요~"
            )
        )

        workDateList.add(
            WorkData(
                "https://i.pinimg.com/236x/ae/c9/ea/aec9eadd89aa51a9b753b221f3bcce12.jpg"
                , "벤쯔의 먹방"
                , "많이먹어요~"
            )
        )

        rv_modify_port_folio_act_work.adapter = workRVAdapter
        rv_modify_port_folio_act_work.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setTransWorkRVAdapter() {
        transWorkDateList.add(
            TransWorkData("바뀌기 전 텍스트 입니당~", "바뀐후 텍스트 입니당")
        )
        transWorkDateList.add(
            TransWorkData("바뀌기 전 텍스트 입니당~", "바뀐후 텍스트 입니당")
        )
        transWorkDateList.add(
            TransWorkData("바뀌기 전 텍스트 입니당~", "바뀐후 텍스트 입니당")
        )
        transWorkDateList.add(
            TransWorkData("바뀌기 전 텍스트 입니당~", "바뀐후 텍스트 입니당")
        )
        transWorkDateList.add(
            TransWorkData("바뀌기 전 텍스트 입니당~", "바뀐후 텍스트 입니당")
        )

        rv_modify_port_folio_act_work.adapter = transWorkRVAdapter
        rv_modify_port_folio_act_work.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setOnClickListener() {
        btn_modify_port_folio_back.setOnClickListener(this)

        btn_youtube.setOnClickListener(this)

        btn_afreeca.setOnClickListener(this)

        btn_twich.setOnClickListener(this)

        // 필터 수정하기
        btn_modify_port_folio_act_modify_filter.setOnClickListener(this)

        // 작품 올리기
        btn_modify_port_folio_act_work_add_work.setOnClickListener(this)

        // 취소하기
        btn_modify_port_folio_cancel.setOnClickListener(this)

        // 수정 완료
        btn_modify_port_folio_confirm.setOnClickListener(this)

    }
}
