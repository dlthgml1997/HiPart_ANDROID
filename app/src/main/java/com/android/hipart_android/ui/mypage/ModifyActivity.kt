package com.android.hipart_android.ui.mypage

import android.Manifest
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.CONTENT_TYPE
import android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v4.content.CursorLoader
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.ui.mypage.dialog.ModifyActCheckAgainDialog
import com.android.hipart_android.ui.mypage.dialog.ModifyActSuccessDialog
import com.android.hipart_android.ui.mypage.dialog.ModifyActWrongPasswordDialog
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_modify.*
import org.jetbrains.anko.textColor

class ModifyActivity : AppCompatActivity() {

    private val MY_READ_STORAGE_REQUEST_CODE by lazy {
        1004
    }

    private val REQ_CODE_SELECT_IMAGE by lazy {
        100
    }

    lateinit var imageURI: String

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

        rl_modify_act_user_image.setOnClickListener {
            requestReadExternalStoragePermission()
        }
    }

    private fun requestReadExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                this, READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    READ_EXTERNAL_STORAGE
                )
            ) {

            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(READ_EXTERNAL_STORAGE),
                    MY_READ_STORAGE_REQUEST_CODE
                )
            }
        } else {
            showAlbum()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == MY_READ_STORAGE_REQUEST_CODE)
        {
            if(grantResults.size >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                showAlbum()
            }else
            {
                finish()
            }
        }
    }

    private fun showAlbum() {
        var intent = Intent(Intent.ACTION_PICK)
        intent.type = CONTENT_TYPE
        intent.data = EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQ_CODE_SELECT_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                if(data != null){

                    val selectedImageUri : Uri = data.data

                    imageURI = getRealPathFromURI(selectedImageUri)

                    Glide.with(this)
                        .load(selectedImageUri)
                        .into(cv_modify_act_user_image)
                }
            }
        }
    }

    private fun getRealPathFromURI(content: Uri): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val loader: CursorLoader = CursorLoader(this, content, proj, null, null, null)
        val cursor: Cursor = loader.loadInBackground()!!
        val column_idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val result = cursor.getString(column_idx)
        cursor.close()
        return result
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
