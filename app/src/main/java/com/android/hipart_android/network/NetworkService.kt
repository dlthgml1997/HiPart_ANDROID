package com.android.hipart_android.network

import com.android.hipart_android.ui.hipart.get.GetDetailCResponse
import com.android.hipart_android.ui.hipart.get.GetDetailEEtcResponse
import com.android.hipart_android.ui.hipart.get.GetDetailTResponse
import com.android.hipart_android.ui.home.data.get.GetCustomRecommendResponse
import com.android.hipart_android.ui.home.data.post.PickDTO
import com.android.hipart_android.ui.home.data.post.PickResponse
import com.android.hipart_android.ui.login.data.PostLoginRequest
import com.android.hipart_android.ui.login.data.PostLoginResponse
import com.android.hipart_android.ui.modifyportfolio.data.WorkIndex
import com.android.hipart_android.ui.modifyportfolio.delete.DeleteModifyPortFolioResponse
import com.android.hipart_android.ui.modifyportfolio.get.GetModifyPortFolioResponseCpat
import com.android.hipart_android.ui.modifyportfolio.get.GetModifyPortFolioResponseEpatAndEtc
import com.android.hipart_android.ui.modifyportfolio.get.GetModifyPortFolioResponseTpat
import com.android.hipart_android.ui.modifyprofile.data.get.GetModifyProfileResponse
import com.android.hipart_android.ui.modifyprofile.data.put.ModifyProfileResponse
import com.android.hipart_android.ui.mypage.data.PostManToManQuestionRequest
import com.android.hipart_android.ui.mypage.get.GetMypageResponse
import com.android.hipart_android.ui.mypick.data.GetMyPickResponse
import com.android.hipart_android.ui.notification.get.GetNotificationResponse
import com.android.hipart_android.ui.search.GetSearchResponse
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

    @GET("pick")
    fun getMyPickResponse(
        @Header("token") token: String
    ): Call<GetMyPickResponse>

    // 청하 라인 ( 각 뷰 별로 주석으로 나눠주세요. )
//    // 로그인-청하
//    @POST("auth/signin")
//    fun postLoginResponse(
//        @Header("Content-Type") content_type: String,
//        @Body body: PostLoginRequest
//    ): Call<PostLoginResponse>

//    @POST("auth/signin")
//    fun postLoginResponse(
//        @Header("Content-Type") content_type: String,
//        @Body body: PostLoginRequest
//    ): Call<PostLoginResponse>
//
//    /**
//     * 알림 조회
//     */
//    @GET("main/notification")
//    fun getNotificationResponse(
//        @Header("Content-Type") content_type: String,
//        @Header("token") token: String
//    ): Call<GetNotificationResponse>
//
//    /**
//     * 마이 페이지
//     */
//    @GET("mypage/info")
//    fun getMypageResponse(
//        @Header("Content-Type") content_type: String,
//        @Header("token") token: String
//    ): Call<GetMypageResponse>

    @POST("auth/signin")
    fun postLoginResponse(
        @Header("Content-Type") content_type: String,
        @Body body: PostLoginRequest
    ): Call<PostLoginResponse>

    /**
     * 알림 조회
     */
    @GET("main/notification")
    fun getNotificationResponse(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String
    ): Call<GetNotificationResponse>

    /**
     * 마이 페이지
     */
    @GET("mypage/info")
    fun getMypageResponse(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String
    ): Call<GetMypageResponse>

//    /**
//     * 알림 조회
//     */
//    @GET("mypage/info")
//    fun getNotificationResponse(
//        @Header("Content-Type") content_type: String,
//        @Header("token") token: String
//    ): Call<GetNotificationResponse>
//
//    /**
//     * 마이 페이지
//     */
//    @GET("mypage/info")
//    fun getMypageResponse(
//        @Header("Content-Type") content_type: String,
//        @Header("token") token: String
//    ): Call<GetMypageResponse>

    // 소희 라인 ( 각 뷰 별로 주석으로 나눠주세요. )
////    /**
////     * 포트폴리오 수정 페이지 로딩
////     */
////    @GET("portfolio/detail")
////    fun getModifyPortFolioResponse(
////        @Header("Content-Type") content_type: String,
////        @Header("token") token: String
////    ): Call<GetModifyPortFolioResponse>
//
//    //    /**
////     * 포트폴리오 수정 페이지 로딩 - 에디터
////     */
////    @GET("portfolio/detail/editor")
////    fun getModifyPortFolioResponseEpat(
////        @Header("Content-Type") content_type: String,
////        @Header("token") token: String
////    ): Call<GetModifyPortFolioResponseEpat>
////
////    /**
////     * 포트폴리오 수정 페이지 로딩 - 크리에이터
////     */
////    @GET("portfolio/detail/creator")
////    fun getModifyPortFolioResponseCpat(
////        @Header("Content-Type") content_type: String,
////        @Header("token") token: String
////    ): Call<GetModifyPortFolioResponseCpat>
//    @GET("portfolio/detail/creator")
//    fun getModifyPortFolioResponseCpat(
//        @Header("Content-Type") content_type: String,
//        @Header("token") token: String
//    ): Call<GetModifyPortFolioResponseCpat>
//
//    /**
//     * 포트폴리오 수정 페이지 로딩 - 에디터
//     */
//    @GET("portfolio/detail/editor")
//    fun getModifyPortFolioResponseEpat(
//        @Header("Content-Type") content_type: String,
//        @Header("token") token: String
//    ): Call<GetModifyPortFolioResponseEpatAndEtc>
//
//    /**
//     * 포트폴리오 수정 페이지 로딩 - 트랜슬레이터
//     * @header token
//     */
//    @GET("portfolio/detail/translator")
//    fun getModifyPortFolioResponseTpat(
//        @Header("Content-Type") content_type: String,
//        @Header("token") token: String
//    ): Call<GetModifyPortFolioResponseTpat>
//
//    /**
//     * 포트폴리오 수정 페이지 로딩 - 기타
//     * @header token
//     */
//    @GET("portfolio/detail/etc")
//    fun getModifyPortFolioResponseEtc(
//        @Header("Content-Type") content_type: String,
//        @Header("token") token: String
//    ): Call<GetModifyPortFolioResponseEpatAndEtc>


    // 소희 라인 ( 각 뷰 별로 주석으로 나눠주세요. )
    /**
     * 포트폴리오 수정 페이지 로딩 - 크리에이터
     */
    @GET("portfolio/detail/creator")
    fun getModifyPortFolioResponseCpat(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String
    ): Call<GetModifyPortFolioResponseCpat>

    /**
     * 포트폴리오 수정 페이지 로딩 - 에디터
     */
    @GET("portfolio/detail/editor")
    fun getModifyPortFolioResponseEpat(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String
    ): Call<GetModifyPortFolioResponseEpatAndEtc>

    /**
     * 포트폴리오 수정 페이지 로딩 - 트랜슬레이터
     * @header token
     */
    @GET("portfolio/detail/translator")
    fun getModifyPortFolioResponseTpat(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String
    ): Call<GetModifyPortFolioResponseTpat>

    /**
     * 포트폴리오 수정 페이지 로딩 - 기타
     * @header token
     */
    @GET("portfolio/detail/etc")
    fun getModifyPortFolioResponseEtc(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String
    ): Call<GetModifyPortFolioResponseEpatAndEtc>


    /**
     * 포트폴리오 수정 페이지 작품 삭제 - 크리에이터
     * @header token
     * @body work_idx
     */
    @HTTP(method = "DELETE", path = "portfolio/creator", hasBody = true)
    fun deleteModifyPortFolioResponseCpat(
        @Header("token") token: String,
        @Body workIndex : WorkIndex
    ): Call<DeleteModifyPortFolioResponse>

    // 지원 라인 ( 각 뷰 별로 주석으로 나눠주세요. )
// 지원 라인 ( 각 뷰 별로 주석으로 나눠주세요. )
    @GET("main/search/{keyword}")
    fun getSearchResponse(
        @Header("Content-Type") content_type: String,
        @Path("keyword") keyword: String
    ): Call<GetSearchResponse>

    //상세보기-지원
    //c
    @GET("profile/detail/editor/{nickname}")
    fun getDetailEEtcResponse(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String,
        @Path("nickname") nickname: String
    ): Call<GetDetailEEtcResponse>

    //e
    @GET("profile/detail/creator/{nickname}")
    fun getDetailCResponse(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String,
        @Path("nickname") nickname: String
    ): Call<GetDetailCResponse>

    //t
    @GET("profile/detail/translator/{nickname}")
    fun getDetailTResponse(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String,
        @Path("nickname") nickname: String
    ): Call<GetDetailTResponse>
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
        @Part("user_pw") userPw: RequestBody,
        @Part("user_number") userNumber: RequestBody,
        @Part("user_type") userType: Int
    ): Call<PostSignUpResponse>

    /**
     * 이메일 중복 확인
     * @param flag
     *  1일 경우 : email
     *  2일 경우 : nickName
     * @param input
     *  이메일 또는 닉네임
     * @return
     *  dataTpat 값이 중복일 시 1, 중복 아니면 0
     */
    @GET("auth/duplicated/{flag}/{input}")
    fun getDuplicateFlag(
        @Path("flag") flag: Int,
        @Path("input") input: String
    ): Call<GetDuplicateFlagResponse>

    /**
     * 픽추가
     * @header token
     * @body nickname
     */
    @POST("pick")
    fun addPick(
        @Header("token") token: String,
        @Body pickDTO: PickDTO
    ): Call<PickResponse>

    /**
     * 픽 삭제
     * @header token
     * @body nickname
     */
    @HTTP(method = "DELETE", path = "pick", hasBody = true)
    fun deletePick(
        @Header("token") token: String,
        @Body pickDTO: PickDTO
    ): Call<PickResponse>

    /**
     * 1대1 문의
     * @header token
     * @body comment
     */
    @POST("mypage/question")
    fun postManToManQusetion(
        @Header("Token") token: String,
        @Body postManToManQuestionRequest: PostManToManQuestionRequest
    ): Call<PickResponse>

    /**
     * 회원정보 조회
     * @header token
     * @body comment
     */
    @GET("mypage/modify")
    fun getProfile(
        @Header("Token") token: String
    ): Call<GetModifyProfileResponse>

    /**
     * 회원정보 변경
     * @header token
     */
    @Multipart
    @PUT("mypage/modify")
    fun modifyProfile(
        @Header("Token") token: String,
        @Part user_img: MultipartBody.Part?,
        @Part("user_nickname") userNickname: RequestBody,
        @Part("user_number") userNumber: RequestBody,
        @Part("user_pw") userPw: RequestBody,
        @Part("new_pw") newPw: RequestBody,
        @Part("user_type") userType: Int
    ): Call<ModifyProfileResponse>

    /**
     * 맞춤추천
     * @header token
     */
    @GET("profile/recommend")
    fun customRecommend(
        @Header("token") token: String
    ): Call<GetCustomRecommendResponse>


}