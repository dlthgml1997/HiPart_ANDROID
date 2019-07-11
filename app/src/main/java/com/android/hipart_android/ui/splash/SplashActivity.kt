package com.android.hipart_android.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.hipart_android.R
import com.android.hipart_android.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        animation_act_splash.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                startActivity<LoginActivity>()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
        animation_act_splash.playAnimation()
    }
}
