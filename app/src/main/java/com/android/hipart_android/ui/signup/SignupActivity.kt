package com.android.hipart_android.ui.signup

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.CursorLoader
import android.util.Patterns
import android.view.View
import com.android.hipart_android.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.rv_item_notification_overview.*
import java.util.jar.Manifest


class SignupActivity : AppCompatActivity() {
    val REQUEST_CODE_SELECT_IMAGE: Int = 1004
    val My_READ_STORAGE_REQUEST_CODE = 7777
    var imageURI: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setOnBtnClickListener()
        edt_Signup_Email.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                ll_Signup_Email.setBackgroundResource(R.drawable.signup_purple_border)
                edt_Signup_Email_img.setImageResource(R.drawable.login_id_on_icon)
            } else {
                ll_Signup_Email.setBackgroundResource(R.drawable.signup_gray_border)
                edt_Signup_Email_img.setImageResource(R.drawable.login_id_off_icon)
                txt_Signup_Wrong_Email.visibility = View.INVISIBLE
                if (edt_Signup_Email.getText().toString().length == 0) {
                    ll_Signup_Email.setBackgroundResource(R.drawable.signup_gray_border)
                    edt_Signup_Email_img.setImageResource(R.drawable.login_id_off_icon)
                } else {
                    ll_Signup_Email.setBackgroundResource(R.drawable.signup_purple_border)
                    edt_Signup_Email_img.setImageResource(R.drawable.login_id_on_icon)
                    if (isValidEmail(edt_Signup_Email.text.toString())) {
                        txt_Signup_Wrong_Email.visibility = View.INVISIBLE
                    } else {
                        txt_Signup_Wrong_Email.visibility = View.VISIBLE
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
                    if(edt_Signup_Password.getText().toString() == edt_Signup_PasswordCheck.getText().toString()){
                        txt_Signup_Wrong_Password.visibility = View.INVISIBLE
                    }
                    else{
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
                if (edt_Signup_Nickname.getText().toString().length == 0) {
                    ll_Signup_Nickname.setBackgroundResource(R.drawable.signup_gray_border)
                    edt_Signup_Nickname_img.setImageResource(R.drawable.login_nickname_off_icon)
                } else {
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
            val intent = Intent(this, SignupPartActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setOnBtnClickListener(){
        btn_signup_update_image.setOnClickListener{
            requestReadExternalStoragePermission()
        }
        btn_signup_update_image_camera.setOnClickListener{
            requestReadExternalStoragePermission()
        }
    }
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showAlbum(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
    }
    private fun requestReadExternalStoragePermission(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    My_READ_STORAGE_REQUEST_CODE
                )
            }
        } else {
            showAlbum()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == My_READ_STORAGE_REQUEST_CODE){
            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                showAlbum()
            }
            else{
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_SELECT_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                if(data != null){
                    val selectedImageUri : Uri = data.data
                    imageURI = getRealPathFromURI(selectedImageUri)
                    Glide.with(this)
                        .load(selectedImageUri)
                        .thumbnail(0.1f)
                        .into(btn_signup_update_image)
                }
            }
        }
    }
    private fun getRealPathFromURI(content : Uri) : String{
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val loader : CursorLoader = CursorLoader(this, content, proj, null, null, null)
        val cursor : Cursor = loader.loadInBackground()!!
        val columns_idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val result = cursor.getString(columns_idx)
        cursor.close()
        return result
    }
}
