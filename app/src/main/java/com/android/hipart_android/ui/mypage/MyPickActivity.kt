package com.android.hipart_android.ui.mypage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.android.hipart_android.R
import com.android.hipart_android.ui.hipat.adapter.PortFolioRecyclerViewAdapter
import com.android.hipart_android.ui.hipat.data.PortFolioData
import kotlinx.android.synthetic.main.activity_mypick.*
import kotlinx.android.synthetic.main.toolbar_mypick.*

class MyPickActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v){
            btn_my_pick_back -> {
                finish()
            }
        }
    }

    val dataList by lazy {
        ArrayList<PortFolioData>()
    }

    lateinit var portFolioRecyclerViewAdapter: PortFolioRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypick)

        configureRecyclerView()
        setOnClickListener()
    }

    private fun configureRecyclerView() {
        dataList.add(
            PortFolioData(
                "default", "NaDo", "크리에이터", true, 224, "안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)", 3
            )
        )
        dataList.add(
            PortFolioData(
                "default", "NaDo", "에디터", true, 224, "안녕 모든 것들을 먹으며 방송하고 있어! 감사 :)", 3
            )
        )
        dataList.add(
            PortFolioData(
                "default", "NaDo", "트랜슬레이터", true, 224, "안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)", 3
            )
        )
        dataList.add(
            PortFolioData(
                "default", "NaDo", "크리에이터", true, 224, "안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)", 3
            )
        )
        dataList.add(
            PortFolioData(
                "default", "NaDo", "크리에이터", true, 224, "안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)", 3
            )
        )
        dataList.add(
            PortFolioData(
                "default", "NaDo", "크리에이터", true, 224, "안녕하세요~ 먹고 싶은 모든 것들을 먹으며 방송하고 있어요! 감사합니다아 :)", 3
            )
        )

        portFolioRecyclerViewAdapter= PortFolioRecyclerViewAdapter(this,dataList, false)
        rv_mypick_act_port.adapter = portFolioRecyclerViewAdapter
        rv_mypick_act_port.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
    }

    fun setAnimPickIcon() {
        val interpolator = MyBounceInterpolator(0.2, 20.0)
        val anim: Animation = AnimationUtils.loadAnimation(this@MyPickActivity, R.anim.expand_anim)
        anim.interpolator = interpolator

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                rl_mypick_act_anim.visibility = View.GONE
            }

            override fun onAnimationStart(animation: Animation?) {
                rl_mypick_act_anim.visibility = View.VISIBLE
            }
        })
        rl_mypick_act_anim.startAnimation(anim)
    }

    internal inner class MyBounceInterpolator(amplitude: Double, frequency: Double) :
        android.view.animation.Interpolator {
        private var mAmplitude = 1.0
        private var mFrequency = 10.0

        init {
            mAmplitude = amplitude
            mFrequency = frequency
        }

        override fun getInterpolation(time: Float): Float {
            return (-1.0 * Math.pow(Math.E, -time / mAmplitude) *
                    Math.cos(mFrequency * time) + 1).toFloat()
        }
    }

    private fun setOnClickListener() {
        btn_my_pick_back.setOnClickListener(this)
    }
}
