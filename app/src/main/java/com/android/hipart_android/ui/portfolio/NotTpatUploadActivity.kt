package com.android.hipart_android.ui.portfolio

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.CONTENT_TYPE
import android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.CursorLoader
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.android.hipart_android.R
import com.android.hipart_android.ui.portfolio.dialog.PortUploadSuccessDialog
import com.android.hipart_android.util.BaseActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_upload_not_tpat.*
import java.lang.Exception
import java.lang.Thread.sleep
import java.util.jar.Manifest

class NotTpatUploadActivity : BaseActivity() {

    private val MY_READ_STORAGE_REQUEST_CODE by lazy {
        1004
    }
    private val REQ_CODE_SELECT_IMAGE by lazy {
        100
    }

    lateinit var imageURI: String

    private val portUploadSuccessDialog by lazy {
        PortUploadSuccessDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_not_tpat)

        setOnBtnClickListener()
        setOnFocusListener()
    }

    private fun setOnFocusListener() {
        et_not_tpat_upload_act_url.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                et_not_tpat_upload_act_url.setBackgroundResource(R.drawable.url_border_purple)
            } else if (!hasFocus && et_not_tpat_upload_act_url.text.isEmpty()) {
                et_not_tpat_upload_act_url.setBackgroundResource(R.drawable.url_border_gray)
            }
        }
    }

    private fun setOnBtnClickListener() {
        rl_not_tpat_upload_act_album.setOnClickListener {
            requestReadExternalStoragePermission()
        }

        btn_not_tpat_upload_act_submit.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        portUploadSuccessDialog.show(supportFragmentManager, "portUploadDialod")
        val handler = Handler()
        handler.postDelayed({
            run {
                portUploadSuccessDialog.dismiss()
            }
        }, 1500)

    }

    private fun requestReadExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    READ_EXTERNAL_STORAGE
                )
            ) {
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(READ_EXTERNAL_STORAGE),
                    MY_READ_STORAGE_REQUEST_CODE
                )
            }
        } else {
            showAlbum()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == MY_READ_STORAGE_REQUEST_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showAlbum()
            } else {
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
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {

                    val selectedImageUri: Uri = data.data
                    imageURI = getRealPathFromURI(selectedImageUri)

                    cv_not_tpat_upload_acr_album.visibility = View.VISIBLE
                    img_not_tpat_upload_act_album.visibility = View.VISIBLE
                    ll_not_tpat_upload_act_album.visibility = View.INVISIBLE
                    rl_not_tpat_upload_act_album.setBackgroundResource(R.drawable.upload_tpat_port_border_purple)
                    cv_not_tpat_upload_acr_album.setBackgroundResource(R.drawable.upload_tpat_port_border_purple)

                    Glide.with(this)
                        .load(selectedImageUri)
                        .into(img_not_tpat_upload_act_album)
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
}


