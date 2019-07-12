package com.android.hipart_android.ui.modifyprofile

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore.Images.Media.CONTENT_TYPE
import android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.TextView
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.ui.modifyprofile.data.get.GetModifyProfileData
import com.android.hipart_android.ui.modifyprofile.data.get.GetModifyProfileResponse
import com.android.hipart_android.ui.modifyprofile.data.put.ModifyProfileResponse
import com.android.hipart_android.ui.mypage.dialog.ModifyActCheckAgainDialog
import com.android.hipart_android.ui.mypage.dialog.ModifyActSuccessDialog
import com.android.hipart_android.ui.mypage.dialog.ModifyActWrongPasswordDialog
import com.android.hipart_android.util.BaseActivity
import com.android.hipart_android.util.ImageConverter
import com.android.hipart_android.util.SharedPreferenceController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_modify.*
import kotlinx.android.synthetic.main.toolbar_modify.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ModifyActivity : BaseActivity() {

    private val MY_READ_STORAGE_REQUEST_CODE by lazy {
        1004
    }

    private val REQ_CODE_SELECT_IMAGE by lazy {
        100
    }

    var imageString: String = ""

    private var userType = 0

    private val modifyActCheckAgainDialog by lazy {
        ModifyActCheckAgainDialog()
    }

    private val modifyActWrongPasswordDialog by lazy {
        ModifyActWrongPasswordDialog()
    }

    private val modifyActSuccessDialog by lazy {
        ModifyActSuccessDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify)
        getProfile()
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
        btn_modify_act_back.setOnClickListener {
            finish()
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

                    imageString = selectedImageUri.toString()

                    Glide.with(this)
                        .load(selectedImageUri)
                        .into(cv_modify_act_user_image)
                }
            }
        }
    }

    private fun setClickListenerOnTextVIew(textView: TextView) {
        textView.setOnClickListener {
            if (!textView.isSelected) {
                reverseBtn(textView)
                textView.textColor = Color.parseColor("#7947fd")
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
                    userType = 1
                }

            }
            txt_modify_act_epat -> {
                initBtnFlag()

                reversedTextView.apply {
                    isSelected = true
                    setTextColor(Color.parseColor("#7947fd"))
                    userType = 2
                }
            }
            txt_modify_act_tpat -> {
                initBtnFlag()

                reversedTextView.apply {
                    isSelected = true
                    setTextColor(Color.parseColor("#7947fd"))
                    userType = 3
                }
            }
            txt_modify_act_etc -> {
                initBtnFlag()

                reversedTextView.apply {
                    isSelected = true
                    setTextColor(Color.parseColor("#7947fd"))
                    userType = 4
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

    private fun getProfile() {
        val networkService = ApplicationController.instance.networkService

        val getProfile = networkService.getProfile(
            SharedPreferenceController.getAuthorization(this@ModifyActivity)
        )

        getProfile.enqueue(object : Callback<GetModifyProfileResponse> {
            override fun onFailure(call: Call<GetModifyProfileResponse>, t: Throwable) {
                Log.e("Modify Activity Err", Log.getStackTraceString(t))
            }

            override fun onResponse(
                call: Call<GetModifyProfileResponse>,
                response: Response<GetModifyProfileResponse>
            ) {
                response
                    ?.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        if (it.message == "회원정보 조회 성공")
                            initView(it.data)
                    }
            }
        })
    }

    private fun initView(dataList: ArrayList<GetModifyProfileData>) {

        if (dataList != null) {

            dataList[0].user_img?.let {
                Glide.with(this@ModifyActivity)
                    .load(it).into(cv_modify_act_user_image)
            }


            dataList[0].user_type?.let {
                when (it) {
                    0 -> {
                        Log.e("Modify Activity Err", "user_type이 Null")
                        userType = 0
                    }
                    1 -> {
                        txt_modify_act_cpat.isSelected = true
                        userType = 1
                    }
                    2 -> {
                        txt_modify_act_epat.isSelected = true
                        userType = 2
                    }
                    3 -> {
                        txt_modify_act_tpat.isSelected = true
                        userType = 3
                    }
                    4 -> {
                        txt_modify_act_etc.isSelected = true
                        userType = 4
                    }
                    else -> {
                        Log.e("Modify Activity Err", "user_type이 이상한 값")
                    }
                }

            }

            dataList[0].user_nickname?.let {
                et_modify_act_nickname.hint = it
            }

            dataList[0].user_number?.let {
                et_modify_act_phone_number.hint = it
            }

        } else
            Log.e("Modify Activity Err", "데이터 리스트가 Null")
    }

    fun modifyProfile() {
        val networkService = ApplicationController.instance.networkService

        val nickName = RequestBody.create(MediaType.parse("searchText/plain"), et_modify_act_nickname.text.toString())
        val phoneNum = RequestBody.create(MediaType.parse("searchText/plain"), et_modify_act_phone_number.text.toString())
        val nowPw = RequestBody.create(MediaType.parse("searchText/plain"), et_modify_act_pw_now.text.toString())
        val newPw = RequestBody.create(MediaType.parse("searchText/plain"), et_modify_act_pw_new.text.toString())

        // 이미지 변경 됐을 시
        if (imageString != "") {
            val imgUri = Uri.parse(imageString)
            // 압축 후 outPutStream으로 변환
            val byteArrayOutputStream = ImageConverter(this@ModifyActivity).imgToByteArrayStream(imgUri)
            val photoBody = RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())
            val image = MultipartBody.Part.createFormData("user_img", File(imgUri.toString()).name, photoBody)

            val modifyProfileResponse = networkService.modifyProfile(
                SharedPreferenceController.getAuthorization(this@ModifyActivity),
                image,
                nickName,
                phoneNum,
                nowPw,
                newPw,
                userType
            )

            modifyProfileResponse.enqueue(object : Callback<ModifyProfileResponse> {
                override fun onFailure(call: Call<ModifyProfileResponse>, t: Throwable) {
                    Log.e("Modify Act Err", Log.getStackTraceString(t))
                }

                override fun onResponse(call: Call<ModifyProfileResponse>, response: Response<ModifyProfileResponse>) {
                    response
                        ?.takeIf { it.isSuccessful }
                        ?.body()
                        ?.message
                        ?.let {
                            when (it) {
                                "회원정보 수정 성공" -> {
                                    modifyActSuccessDialog.show(supportFragmentManager, "modify success")
                                    val handler = Handler()
                                    handler.postDelayed({
                                        run {
                                            modifyActSuccessDialog.dismiss()
                                            finish()
                                        }
                                    }, 1500)

                                }
                                "현재 비밀번호가 일치하지 않습니다" -> {
                                    toast("현재 비밀번호가 일치하지 않습니다")
                                }
                                else -> {
                                    Log.e("Modify Act Err", "DB 에러 발생")
                                }

                            }
                        }
                }
            })
        }
        // 이미지 변경이 안됐을 시
        else {
            val byteArray =
                ImageConverter(this@ModifyActivity).drawableToByteArrayStream(getDrawable(R.drawable.sign_photo_icon))
            val photoBody = RequestBody.create(MediaType.parse("image/jpg"), byteArray)
            var image = MultipartBody.Part.createFormData("user_img", "default_img.jpg", photoBody)

            val modifyProfileResponse = networkService.modifyProfile(
                SharedPreferenceController.getAuthorization(this@ModifyActivity),
                image,
                nickName,
                phoneNum,
                nowPw,
                newPw,
                userType
            )

            modifyProfileResponse.enqueue(object : Callback<ModifyProfileResponse> {
                override fun onFailure(call: Call<ModifyProfileResponse>, t: Throwable) {
                    Log.e("Modify Act Err", Log.getStackTraceString(t))
                }

                override fun onResponse(call: Call<ModifyProfileResponse>, response: Response<ModifyProfileResponse>) {
                    response
                        ?.takeIf { it.isSuccessful }
                        ?.body()
                        ?.message
                        ?.let {
                            when (it) {
                                "회원정보 수정 성공" -> {
                                    modifyActSuccessDialog.show(supportFragmentManager, "modify success")
                                    val handler = Handler()
                                    handler.postDelayed({
                                        run {
                                            modifyActSuccessDialog.dismiss()
                                            finish()
                                        }
                                    }, 1500)
                                }
                                "현재 비밀번호가 일치하지 않습니다" -> {
                                    toast("현재 비밀번호가 일치하지 않습니다")
                                }
                                else -> {
                                    Log.e("Modify Act Err", "DB 에러 발생")
                                }

                            }
                        }
                }
            })

        }


    }

}
