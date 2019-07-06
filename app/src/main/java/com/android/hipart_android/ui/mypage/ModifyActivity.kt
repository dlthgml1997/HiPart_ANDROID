package com.android.hipart_android.ui.mypage

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.ui.mypage.dialog.ModifyActCheckAgainDialog
import com.android.hipart_android.ui.mypage.dialog.ModifyActSuccessDialog
import com.android.hipart_android.ui.mypage.dialog.ModifyActWrongPasswordDialog
import kotlinx.android.synthetic.main.activity_modify.*
import org.jetbrains.anko.textColor

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

        setClickListenerOnTextVIew(txt_modify_act_cpat)
        setClickListenerOnTextVIew(txt_modify_act_epat)
        setClickListenerOnTextVIew(txt_modify_act_tpat)
        setClickListenerOnTextVIew(txt_modify_act_etc)
    }

    private fun setClickListenerOnTextVIew(textView: TextView) {
        textView.setOnClickListener {
            if (!textView.isSelected) {
                reverseBtn(textView)
            } else {
                textView.isSelected = false
                textView.textColor = Color.parseColor("#707070")
            }
        }
    }

    private fun reverseBtn(reversedTextView: TextView) {
        when (reversedTextView) {
            txt_modify_act_cpat -> {
                initBtnFlag()

                reversedTextView.apply {
                    isSelected = true
                    setTextColor(Color.parseColor("#7947fd"))
                }
            }
            txt_modify_act_epat -> {
                initBtnFlag()

                reversedTextView.apply {
                    isSelected = true
                    setTextColor(Color.parseColor("#7947fd"))
                }
            }
            txt_modify_act_tpat -> {
                initBtnFlag()

                reversedTextView.apply {
                    isSelected = true
                    setTextColor(Color.parseColor("#7947fd"))
                }
            }
            txt_modify_act_etc -> {
                initBtnFlag()

                reversedTextView.apply {
                    isSelected = true
                    setTextColor(Color.parseColor("#7947fd"))
                }
            }
        }
    }

    private fun initBtnFlag() {
        txt_modify_act_cpat.isSelected = false
        txt_modify_act_epat.isSelected = false
        txt_modify_act_tpat.isSelected = false
        txt_modify_act_etc.isSelected = false

        txt_modify_act_cpat.setTextColor(Color.parseColor("#707070"))
        txt_modify_act_epat.setTextColor(Color.parseColor("#707070"))
        txt_modify_act_tpat.setTextColor(Color.parseColor("#707070"))
        txt_modify_act_etc.setTextColor(Color.parseColor("#707070"))
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
