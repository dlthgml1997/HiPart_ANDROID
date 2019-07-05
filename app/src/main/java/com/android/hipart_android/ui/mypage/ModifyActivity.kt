package com.android.hipart_android.ui.mypage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.android.hipart_android.R
import com.android.hipart_android.ui.mypage.dialog.ModifyActCheckAgainDialog
import com.android.hipart_android.ui.mypage.dialog.ModifyActSuccessDialog
import com.android.hipart_android.ui.mypage.dialog.ModifyActWrongPasswordDialog
import kotlinx.android.synthetic.main.activity_modify.*

class ModifyActivity : AppCompatActivity() {

    private val modifyActCheckAgainDialog by lazy {
        ModifyActCheckAgainDialog()
    }

    private val modifyActWrongPasswordDialog by lazy {
        ModifyActWrongPasswordDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify)

        setOnBtnClickListener()
    }

    private fun setOnBtnClickListener() {
        btn_modify_act_submit.setOnClickListener {
            if (!checkPresenetPassword(et_modify_act_pw_now.text.toString())) {
                showWrongPasswordDialog()
            } else {
                showCheckAgainDialog()
            }
        }
    }

    private fun showCheckAgainDialog() {
        modifyActCheckAgainDialog.show(supportFragmentManager, "check again")
    }

    private fun checkPresenetPassword(pw: String): Boolean {
        //나중에 통신 연결
        return !et_modify_act_pw_now.text.toString().isEmpty()
    }

    private fun showWrongPasswordDialog() {
        modifyActWrongPasswordDialog.show(supportFragmentManager, "wrong password")
    }

}
