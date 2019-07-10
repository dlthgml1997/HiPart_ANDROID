package com.android.hipart_android.ui.signup

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.ui.signup.data.GetDuplicateFlagResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Response


class SignupActivity : AppCompatActivity() {
    val REQUEST_CODE_SELECT_IMAGE: Int = 1004
    val My_READ_STORAGE_REQUEST_CODE = 7777
    var imageURI: String = ""
    var emailDuplicateFlag = false
    var nickNameDuplicateFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setOnBtnClickListener()
        edt_Signup_Email.setOnFocusChangeListener { v, hasFocus ->
            // 포커스
            if (hasFocus) {
                ll_Signup_Email.setBackgroundResource(R.drawable.signup_purple_border)
                edt_Signup_Email_img.setImageResource(R.drawable.login_id_on_icon)
            }
            // 포커스 X
            else {
                ll_Signup_Email.setBackgroundResource(R.drawable.signup_gray_border)
                edt_Signup_Email_img.setImageResource(R.drawable.login_id_off_icon)
                txt_Signup_Wrong_Email.visibility = View.INVISIBLE
                // 글이 없을 때
                if (edt_Signup_Email.text.toString().length == 0) {
                    ll_Signup_Email.setBackgroundResource(R.drawable.signup_gray_border)
                    edt_Signup_Email_img.setImageResource(R.drawable.login_id_off_icon)
                } // 글이 있을 때
                else {
                    ll_Signup_Email.setBackgroundResource(R.drawable.signup_purple_border)
                    edt_Signup_Email_img.setImageResource(R.drawable.login_id_on_icon)
                    // 이메일 규격에 맞을 때
                    if (isValidEmail(edt_Signup_Email.text.toString())) {
                        txt_Signup_Wrong_Email.visibility = View.INVISIBLE
                        // 서버 통신
                        getDuplicateNickNameOrEmail(1, edt_Signup_Email.text.toString())

                    } else {
                        txt_Signup_Wrong_Email.visibility = View.VISIBLE
                        txt_Signup_Overlap_Email.visibility = View.GONE
                    }
                }
            }
        }

        edt_Signup_Password.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                ll_Signup_Password.setBackgroundResource(R.drawable.signup_purple_border)
                edt_Signup_Password_img.setImageResource(R.drawable.login_password_on_icon)
            } else {
                ll_Signup_Password.setBackgroundResource(R.drawable.signup_gray_border)
                edt_Signup_Password_img.setImageResource(R.drawable.login_password_off_icon)
                if (edt_Signup_Password.getText().toString().length == 0) {
                    ll_Signup_Password.setBackgroundResource(R.drawable.signup_gray_border)
                    edt_Signup_Password_img.setImageResource(R.drawable.login_password_off_icon)
                } else {
                    ll_Signup_Password.setBackgroundResource(R.drawable.signup_purple_border)
                    edt_Signup_Password_img.setImageResource(R.drawable.login_password_on_icon)
                }
            }
        }
        edt_Signup_PasswordCheck.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                ll_Signup_Password_Check.setBackgroundResource(R.drawable.signup_purple_border)
                edt_Signup_PasswordCheck_img.setImageResource(R.drawable.login_password_on_icon)
            } else {
                ll_Signup_Password_Check.setBackgroundResource(R.drawable.signup_gray_border)
                edt_Signup_PasswordCheck_img.setImageResource(R.drawable.login_password_off_icon)
                if (edt_Signup_PasswordCheck.getText().toString().length == 0) {
                    ll_Signup_Password_Check.setBackgroundResource(R.drawable.signup_gray_border)
                    edt_Signup_PasswordCheck_img.setImageResource(R.drawable.login_password_off_icon)
                } else {
                    ll_Signup_Password_Check.setBackgroundResource(R.drawable.signup_purple_border)
                    edt_Signup_PasswordCheck_img.setImageResource(R.drawable.login_password_on_icon)
                    if (edt_Signup_Password.getText().toString() == edt_Signup_PasswordCheck.getText().toString()) {
                        txt_Signup_Wrong_Password.visibility = View.INVISIBLE
                    } else {
                        txt_Signup_Wrong_Password.visibility = View.VISIBLE
                    }
                }
            }
        }
        edt_Signup_Nickname.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                ll_Signup_Nickname.setBackgroundResource(R.drawable.signup_purple_border)
                edt_Signup_Nickname_img.setImageResource(R.drawable.login_nickname_on_icon)
            } else {
                ll_Signup_Nickname.setBackgroundResource(R.drawable.signup_gray_border)
                edt_Signup_Nickname_img.setImageResource(R.drawable.login_nickname_off_icon)
                if (edt_Signup_Nickname.text.toString().length == 0) {
                    ll_Signup_Nickname.setBackgroundResource(R.drawable.signup_gray_border)
                    edt_Signup_Nickname_img.setImageResource(R.drawable.login_nickname_off_icon)
                    txt_Signup_Overlap_Nickname.visibility = View.GONE
                } else {
                    getDuplicateNickNameOrEmail(2, edt_Signup_Nickname.text.toString())
                    ll_Signup_Nickname.setBackgroundResource(R.drawable.signup_purple_border)
                    edt_Signup_Nickname_img.setImageResource(R.drawable.login_nickname_on_icon)
                }
            }
        }
        edt_Signup_Phonenumber.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                ll_Signup_Phonenumber.setBackgroundResource(R.drawable.signup_purple_border)
                edt_Signup_Phonenumber_img.setImageResource(R.drawable.login_phone_on_icon)
            } else {
                ll_Signup_Phonenumber.setBackgroundResource(R.drawable.signup_gray_border)
                edt_Signup_Phonenumber_img.setImageResource(R.drawable.login_phone_off_icon)
                if (edt_Signup_Phonenumber.getText().toString().length == 0) {
                    ll_Signup_Phonenumber.setBackgroundResource(R.drawable.signup_gray_border)
                    edt_Signup_Phonenumber_img.setImageResource(R.drawable.login_phone_off_icon)
                } else {
                    ll_Signup_Phonenumber.setBackgroundResource(R.drawable.signup_purple_border)
                    edt_Signup_Phonenumber_img.setImageResource(R.drawable.login_phone_on_icon)
                }
            }
        }
        btn_signup_nextstep.setOnClickListener {
            if (emailDuplicateFlag == false && nickNameDuplicateFlag == false) {
                if (txt_Signup_Wrong_Email.visibility == INVISIBLE && txt_Signup_Wrong_Password.visibility == INVISIBLE) {
                    txt_signup_try_again.visibility = INVISIBLE
                    startActivity<SignupPartActivity>(
                        "email" to edt_Signup_Email.text.toString(),
                        "password" to edt_Signup_Password.text.toString(),
                        "nickName" to edt_Signup_Nickname.text.toString(),
                        "phoneNum" to edt_Signup_Phonenumber.text.toString(),
                        "img" to imageURI
                    )
                }
            }
            else{
                txt_signup_try_again.visibility = VISIBLE
            }
        }
    }

    private fun setOnBtnClickListener() {
        btn_signup_update_image.setOnClickListener {
            requestReadExternalStoragePermission()
        }
        btn_signup_update_image_camera.setOnClickListener {
            requestReadExternalStoragePermission()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showAlbum() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
    }

    private fun requestReadExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    My_READ_STORAGE_REQUEST_CODE
                )
            }
        } else {
            showAlbum()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == My_READ_STORAGE_REQUEST_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showAlbum()
            } else {
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    val selectedImageUri: Uri = data.data
                    imageURI = selectedImageUri.toString()
                    Glide.with(this)
                        .load(selectedImageUri)
                        .thumbnail(0.1f)
                        .into(btn_signup_update_image)
                }
            }
        }
    }

    /**
     * @param emailFlag 1일 경우 email, 2일 경우 닉네임
     */
    private fun getDuplicateNickNameOrEmail(emailFlag: Int, emailOrNickName: String) {
        val networkService = ApplicationController.instance.networkService

        val getDuplicateFlag = networkService.getDuplicateFlag(emailFlag, emailOrNickName)

        getDuplicateFlag.enqueue(object : retrofit2.Callback<GetDuplicateFlagResponse> {
            override fun onFailure(call: Call<GetDuplicateFlagResponse>, t: Throwable) {
                Log.e("SignupActivityTAG", Log.getStackTraceString(t))
            }

            override fun onResponse(
                call: Call<GetDuplicateFlagResponse>,
                response: Response<GetDuplicateFlagResponse>
            ) {

                response?.takeIf { it.isSuccessful }?.body()?.data?.let {
                    when (it) {
                        // 중복 X
                        0 -> {
                            if (emailFlag == 1) {

                                emailDuplicateFlag = false
                                txt_Signup_Overlap_Email.visibility = View.GONE

                            } else if (emailFlag == 2) {
                                nickNameDuplicateFlag = false
                                txt_Signup_Overlap_Nickname.visibility = View.GONE

                            }
                        }

                        // 중복
                        1 -> {
                            if (emailFlag == 1) {
                                emailDuplicateFlag = true
                                txt_Signup_Overlap_Email.visibility = View.VISIBLE

                            } else if (emailFlag == 2) {
                                nickNameDuplicateFlag = true
                                txt_Signup_Overlap_Nickname.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        })
    }
}
