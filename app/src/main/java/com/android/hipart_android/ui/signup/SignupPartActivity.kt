package com.android.hipart_android.ui.signup

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.android.hipart_android.R
import com.android.hipart_android.network.ApplicationController
import com.android.hipart_android.ui.login.LoginActivity
import com.android.hipart_android.ui.signup.data.PostSignUpResponse
import com.android.hipart_android.util.ImageConverter
import kotlinx.android.synthetic.main.activity_signup_part.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


// TODO : 사진 기본이미지로 작성하면 null로 들어가는지 확인
class SignupPartActivity : AppCompatActivity() {

    private var email = ""
    private var password = ""
    private var nickName = ""
    private var phoneNum = ""
    private var img = ""
    private var userType = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_part)
        init()
        setOnClickListener()
    }

    private fun init() {
        email = intent.getStringExtra("email")
        password = intent.getStringExtra("password")
        nickName = intent.getStringExtra("nickName")
        phoneNum = intent.getStringExtra("phoneNum")
        img = intent.getStringExtra("img")
    }

    private fun postSignUp() {
        val networkService = ApplicationController.instance.networkService

        val emailTemp = RequestBody.create(MediaType.parse("text/plain"), email)
        val password = RequestBody.create(MediaType.parse("text/plain"), password)
        val nickName = RequestBody.create(MediaType.parse("text/plain"), nickName)
        val phoneNum = RequestBody.create(MediaType.parse("text/plain"), phoneNum)

        val imgUri = Uri.parse(img)
        if(img != ""){
            // 압축 후 outPutStream으로 변환
            var byteArrayOutputStream = ImageConverter(this@SignupPartActivity).imgToByteArrayStream(imgUri)
            val photoBody = RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())
            var image = MultipartBody.Part.createFormData("user_img", File(imgUri.toString()).name, photoBody)

            val postSignUpResponse = networkService.postSignUp(emailTemp, nickName, image, password, phoneNum, userType)

            postSignUpResponse.enqueue(object : Callback<PostSignUpResponse> {
                override fun onFailure(call: Call<PostSignUpResponse>, t: Throwable) {
                    Log.e("ERRORMESSAGE", t.toString())
                }

                override fun onResponse(call: Call<PostSignUpResponse>, response: Response<PostSignUpResponse>) {

                    response
                        ?.takeIf { it.isSuccessful }
                        ?.body()
                        ?.takeIf { it.message == "회원가입 성공" }
                        ?.let { startActivity<LoginActivity>() }

                }
            })
        }else {

            val byteArray = ImageConverter(this@SignupPartActivity).drawableToByteArrayStream(getDrawable(R.drawable.sign_photo_icon))
//            val bitmap = ( as BitmapDrawable).bitmap
//
//            val byteArrayOutputStream = ByteArrayOutputStream()
//            bitmap.compress(Bitmap.CompressFormat.PNG, 20, byteArrayOutputStream)
//            val byteArray = byteArrayOutputStream.toByteArray()

            val photoBody = RequestBody.create(MediaType.parse("image/jpg"), byteArray)
            var image = MultipartBody.Part.createFormData("user_img", "default_img.jpg", photoBody)

            val postSignUpResponse = networkService.postSignUp(emailTemp, nickName, image, password, phoneNum, userType)

            postSignUpResponse.enqueue(object : Callback<PostSignUpResponse> {
                override fun onFailure(call: Call<PostSignUpResponse>, t: Throwable) {
                    Log.e("ERRORMESSAGE", t.toString())
                }

                override fun onResponse(call: Call<PostSignUpResponse>, response: Response<PostSignUpResponse>) {

                    response
                        ?.takeIf { it.isSuccessful }
                        ?.body()
                        ?.takeIf { it.message == "회원가입 성공" }
                        ?.let { startActivity<LoginActivity>() }

                }
            })
        }

    }

    fun setOnClickListener() {
        img_Signup_cpat.setOnClickListener {
            if (img_Signup_cpat.isSelected == true) {
                img_Signup_cpat.isSelected = false
                userType = 0
            } else {
                clearAllBtn()
                img_Signup_cpat.isSelected = true
                userType = 1
            }
        }
        img_Signup_epat.setOnClickListener {
            if (img_Signup_epat.isSelected == true) {
                img_Signup_epat.isSelected = false
                userType = 0
            } else {
                clearAllBtn()
                img_Signup_epat.isSelected = true
                userType = 2
            }
        }
        img_Signup_tpat.setOnClickListener {
            if (img_Signup_tpat.isSelected == true) {
                img_Signup_tpat.isSelected = false
                userType = 0
            } else {
                clearAllBtn()
                img_Signup_tpat.isSelected = true
                userType = 3
            }
        }

        img_Signup_etc.setOnClickListener {
            if (img_Signup_etc.isSelected == true) {
                img_Signup_etc.isSelected = false
                userType = 0
            } else {
                clearAllBtn()
                img_Signup_etc.isSelected = true
                userType = 4
            }
        }
        iv_signup_part_act_finish.setOnClickListener {

            if (userType == 0)
                toast("유저 타입을 설정해주세요.")
            else
                postSignUp()

        }
    }

    private fun clearAllBtn(){

        img_Signup_cpat.isSelected = false
        img_Signup_epat.isSelected = false
        img_Signup_tpat.isSelected = false
        img_Signup_etc.isSelected = false
    }
}
