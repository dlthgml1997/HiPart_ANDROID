package com.android.hipart_android.ui.portfolio

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.CursorLoader
import android.util.Log
import android.view.View
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.network.NetworkService
import com.android.hipart_android.ui.portfolio.data.post.PostCPortFolioResponse
import com.android.hipart_android.ui.portfolio.data.post.PostEPortFolioResponse
import com.android.hipart_android.ui.portfolio.data.post.PostEtcPortFolioResponse
import com.android.hipart_android.ui.portfolio.dialog.PortUploadSuccessDialog
import com.android.hipart_android.util.BaseActivity
import com.android.hipart_android.util.SharedPreferenceController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_upload_not_tpat.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

class NotTpatUploadActivity : BaseActivity() {
    private var mImage: MultipartBody.Part? = null

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

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

    private val user_type by lazy {
        SharedPreferenceController.getUserType(this@NotTpatUploadActivity)
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
            getCPortFolioResponse(user_type)
        }
    }

    // C,E,Etc pat 통신 공통부분 & 나누기
    private fun getCPortFolioResponse(user_type: Int) {
        val url = et_not_tpat_upload_act_url.text.toString()
        val title = et_not_tpat_upload_act_title.text.toString()
        val content = et_not_tpat_upload_act_content.text.toString()

        if (title.isNotEmpty() && content.isNotEmpty()) {
            val user_token = SharedPreferenceController.getAuthorization(this@NotTpatUploadActivity)
            val user_url = RequestBody.create(MediaType.parse("text/plain"), url)
            val user_title = RequestBody.create(MediaType.parse("text/plain"), title)
            val user_content = RequestBody.create(MediaType.parse("text/plain"), content)

            when (user_type) {
                1 -> {
                    postPofolCpat(user_token, user_url, user_title, user_content)
                    // Log.v("청하","Cpat 성공")
                }
                2 -> {
                    postPofolEpat(user_token, user_url, user_title, user_content)
                    // Log.v("청하","Epat 성공")
                }
                4 -> {
                    postPofolEtcpat(user_token, user_url, user_title, user_content)
                    // Log.v("청하","Etcpat 성공")
                }
            }
        }
    }

    // 크리에이터 통신
    private fun postPofolCpat(token: String, url: RequestBody, title: RequestBody, content: RequestBody) {
        val postCPortFolioResponse = networkService.postCPortFolioResponse(token, mImage, url, title, content)
        postCPortFolioResponse.enqueue(object : Callback<PostCPortFolioResponse> {
            override fun onFailure(call: Call<PostCPortFolioResponse>, t: Throwable) {
                Log.e("PortFoloi fail", t.toString())
            }

            override fun onResponse(
                call: Call<PostCPortFolioResponse>,
                response: Response<PostCPortFolioResponse>
            ) {
                // 작품 등록 성공
                response?.takeIf { it.isSuccessful }
                    ?.body()?.takeIf { it.message == "작품 등록 성공" }
                    ?.let {
                        showDialog()
                    }
            }
        })
    }
    // 에디터 통신
    private fun postPofolEpat(token: String, url: RequestBody, title: RequestBody, content: RequestBody) {
        val postEPortFolioResponse = networkService.postEPortFolioResponse(token, mImage, url, title, content)
        postEPortFolioResponse.enqueue(object : Callback<PostEPortFolioResponse> {
            override fun onFailure(call: Call<PostEPortFolioResponse>, t: Throwable) {
                Log.e("PortFoloi fail", t.toString())
            }

            override fun onResponse(
                call: Call<PostEPortFolioResponse>,
                response: Response<PostEPortFolioResponse>
            ) {
                // 작품 등록 성공
                response?.takeIf { it.isSuccessful }
                    ?.body()?.takeIf { it.message == "작품 등록 성공" }
                    ?.let {
                        showDialog()
                    }
            }
        })
    }
    // 기타 통신
    private fun postPofolEtcpat(token: String, url: RequestBody, title: RequestBody, content: RequestBody) {
        val postEtcPortFolioResponse = networkService.postEtcPortFolioResponse(token, mImage, url, title, content)
        postEtcPortFolioResponse.enqueue(object : Callback<PostEtcPortFolioResponse> {
            override fun onFailure(call: Call<PostEtcPortFolioResponse>, t: Throwable) {
                Log.e("PortFoloi fail", t.toString())
            }

            override fun onResponse(
                call: Call<PostEtcPortFolioResponse>,
                response: Response<PostEtcPortFolioResponse>
            ) {
                // 작품 등록 성공
                response?.takeIf { it.isSuccessful }
                    ?.body()?.takeIf { it.message == "작품 등록 성공" }
                    ?.let {
                        showDialog()
                    }
            }
        })
    }

    // 성공 다이얼로그 띄우기
    private fun showDialog() {
        portUploadSuccessDialog.show(supportFragmentManager, "modify pofol success")
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
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    val selectedImageUri: Uri = it.data
                    val options = BitmapFactory.Options()
                    val inputStream: InputStream = contentResolver.openInputStream(selectedImageUri)
                    val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
                    imageURI = getRealPathFromURI(selectedImageUri)
                    val photoBody =
                        RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())
                    mImage = MultipartBody.Part.createFormData(
                        "thumbnail",
                        File(selectedImageUri.toString()).name,
                        photoBody
                    )

                    cv_not_tpat_upload_acr_album.visibility = View.VISIBLE
                    img_not_tpat_upload_act_album.visibility = View.VISIBLE
                    ll_not_tpat_upload_act_album.visibility = View.INVISIBLE
                    rl_not_tpat_upload_act_album.setBackgroundResource(R.drawable.upload_tpat_port_border_purple)
                    cv_not_tpat_upload_acr_album.setBackgroundResource(R.drawable.upload_tpat_port_border_purple)

                    Glide.with(this)
                        .load(selectedImageUri)
                        .thumbnail(0.1f)
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

