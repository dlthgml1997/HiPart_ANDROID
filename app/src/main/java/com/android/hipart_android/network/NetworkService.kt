package com.android.hipart_android.network

import com.android.hipart_android.ui.login.data.PostLoginRequest
import com.android.hipart_android.ui.login.data.PostLoginResponse
import com.android.hipart_android.ui.modifyportfolio.get.GetModifyPortFolioResponse
import com.android.hipart_android.ui.signup.data.GetDuplicateFlagResponse
import com.android.hipart_android.ui.signup.data.PostSignUpResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


/**
 * Created by TakHyeongMin on 2019-07-07.
 */
interface NetworkService {
    // 도경 라인 ( 각 뷰 별로 주석으로 나눠주세요. )

    // 청하 라인 ( 각 뷰 별로 주석으로 나눠주세요. )
    // 로그인-청하
    @POST("auth/signin")
    fun postLoginResponse(
        @Header("Content-Type") content_type: String,
        @Body body: PostLoginRequest
    ): Call<PostLoginResponse>

    // 소희 라인 ( 각 뷰 별로 주석으로 나눠주세요. )
    /**
     * 포트폴리오 수정 페이지 로딩
     */
    @GET("portfolio/detail")
    fun getModifyPortFolioResponse(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String
    ): Call<GetModifyPortFolioResponse>

    // 지원 라인 ( 각 뷰 별로 주석으로 나눠주세요. )

    // 형민 라인 ( 각 뷰 별로 주석으로 나눠주세요. )


    /**
     * 회원 가입
     */
    @Multipart
    @POST("auth/signup")
    fun postSignUp(
        @Part("user_email") userEmail: RequestBody,
        @Part("user_nickname") userNickname: RequestBody,
        @Part user_img: MultipartBody.Part?,
        @Part("user_pw") userPw : RequestBody,
        @Part("user_number") userNumber : RequestBody,
        @Part("user_type") userType : Int
    ): Call<PostSignUpResponse>

    /**
     * 이메일 중복 확인
     * @param flag
     *  1일 경우 : email
     *  2일 경우 : nickName
     * @param input
     *  이메일 또는 닉네임
     * @return
     *  data 값이 중복일 시 1, 중복 아니면 0
     */
    @GET("auth/duplicated/{flag}/{input}")
    fun getDuplicateFlag(
        @Path("flag") flag : Int,
        @Path("input") input : String
    ) : Call<GetDuplicateFlagResponse>


}