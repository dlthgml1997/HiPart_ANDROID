package com.android.hipart_android.ui.modifyportfolio

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.android.hipart_android.R
import com.android.hipart_android.ui.modifyportfolio.adapter.FilterRVAdapter
import com.android.hipart_android.ui.modifyportfolio.adapter.WorkRVAdapter
import com.android.hipart_android.ui.modifyportfolio.data.FilterData
import com.android.hipart_android.ui.modifyportfolio.data.WorkData
import com.android.hipart_android.util.BaseActivity
import kotlinx.android.synthetic.main.activity_modify_port_folio.*

class ModifyPortFolioActivity : BaseActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_port_folio)

        setFilterRVAdapter()
        setWorkRVAdapter()
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
}
