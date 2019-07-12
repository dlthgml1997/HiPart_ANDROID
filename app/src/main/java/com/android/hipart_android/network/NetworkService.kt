package com.android.hipart_android.network

import com.android.hipart_android.ui.hipart.data.GetHifiveNumResponse
import com.android.hipart_android.ui.hipart.data.PostHifiveRequest
import com.android.hipart_android.ui.hipart.data.PostHifiveResponse
import com.android.hipart_android.ui.hipart.get.GetDetailCResponse
import com.android.hipart_android.ui.hipart.get.GetDetailEEtcResponse
import com.android.hipart_android.ui.hipart.get.GetDetailTResponse
import com.android.hipart_android.ui.hipat.data.GetProfileLookUpResponse
import com.android.hipart_android.ui.home.data.get.GetCustomRecommendResponse
import com.android.hipart_android.ui.home.data.get.GetNotificationFlagResponse
import com.android.hipart_android.ui.home.data.post.PickDTO
import com.android.hipart_android.ui.home.data.post.PickResponse
import com.android.hipart_android.ui.login.data.PostLoginRequest
import com.android.hipart_android.ui.login.data.PostLoginResponse
import com.android.hipart_android.ui.login.data.RefreshTokenResponse
import com.android.hipart_android.ui.login.data.get.GetMyInfoResponse
import com.android.hipart_android.ui.main.data.PutClickBannerRequest
import com.android.hipart_android.ui.modifyportfolio.data.ModifyList
import com.android.hipart_android.ui.modifyportfolio.data.WorkIndex
import com.android.hipart_android.ui.modifyportfolio.delete.DeleteModifyPortFolioResponse
import com.android.hipart_android.ui.modifyportfolio.get.GetModifyPortFolioResponseCpat
import com.android.hipart_android.ui.modifyportfolio.get.GetModifyPortFolioResponseEpatAndEtc
import com.android.hipart_android.ui.modifyportfolio.get.GetModifyPortFolioResponseTpat
import com.android.hipart_android.ui.modifyportfolio.put.PutModifyPortFolioResponse
import com.android.hipart_android.ui.modifyprofile.data.get.GetModifyProfileResponse
import com.android.hipart_android.ui.modifyprofile.data.put.ModifyProfileResponse
import com.android.hipart_android.ui.mypage.data.PostManToManQuestionRequest
import com.android.hipart_android.ui.mypage.data.get.GetMyPageResponse
import com.android.hipart_android.ui.mypick.data.GetMyPickResponse
import com.android.hipart_android.ui.notification.get.GetNotificationResponse
import com.android.hipart_android.ui.portfolio.data.PostPortfolioTransRequest
import com.android.hipart_android.ui.portfolio.data.PostPortfolioTransResponse
import com.android.hipart_android.ui.portfolio.data.post.PostCPortFolioResponse
import com.android.hipart_android.ui.portfolio.data.post.PostEPortFolioResponse
import com.android.hipart_android.ui.portfolio.data.post.PostEtcPortFolioResponse
import com.android.hipart_android.ui.search.get.GetSearchResponse
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

    // 도경 라인 ( 각 뷰 별로 주석으로 나눠주세요. )
    @GET("pick")
    fun getMyPickResponse(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String
    ): Call<GetMyPickResponse>

    @GET("auth/refresh")
    fun getRefreshToken(
        @Header("Content-type") content_type: String,
        @Header("refreshtoken") token : String
    ):Call<RefreshTokenResponse>

    @POST("portfolio/translator")
    fun postPortfolioTransResponse(
        @Header("Content-type") content_type: String,
        @Header("token") token: String,
        @Body body : PostPortfolioTransRequest
    ):Call<PostPortfolioTransResponse>

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

    /**
     * 로그인
     */
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
    ): Call<GetMyPageResponse>

    /**
     * 하이 파이브한 사람
     */
    @POST("hifive")
    fun postHifiveResponse(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String,
        @Body body: PostHifiveRequest
    ): Call<PostHifiveResponse>

    /**
     * 하이 파이브한 사람 전화번호
     */
    @GET("hifive/{nickname}")
    fun getHifiveNumResponse(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String,
        @Path("nickname") nickname: String
    ): Call<GetHifiveNumResponse>

    /**
     * 크리에이터 작품 등록
     *
     */
    @Multipart
    @POST("portfolio/creator")
    fun postCPortFolioResponse(
        @Header("token") token: String,
        @Part thumbnail: MultipartBody.Part?,
        @Part("url") url: RequestBody,
        @Part("title") title: RequestBody,
        @Part("content") content: RequestBody
    ): Call<PostCPortFolioResponse>

    /**
     * 에디터 작품 등록
     *
     */
    @Multipart
    @POST("portfolio/editor")
    fun postEPortFolioResponse(
        @Header("token") token: String,
        @Part thumbnail: MultipartBody.Part?,
        @Part("url") url: RequestBody,
        @Part("title") title: RequestBody,
        @Part("content") content: RequestBody
    ): Call<PostEPortFolioResponse>

    /**
     * 기타 작품 등록
     *
     */
    @Multipart
    @POST("portfolio/etc")
    fun postEtcPortFolioResponse(
        @Header("token") token: String,
        @Part thumbnail: MultipartBody.Part?,
        @Part("url") url: RequestBody,
        @Part("title") title: RequestBody,
        @Part("content") content: RequestBody
    ): Call<PostEtcPortFolioResponse>

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

    /**
     * 포트폴리오 수정 페이지 작품 삭제 - 에디터
     * @header token
     * @body work_idx
     */
    @HTTP(method = "DELETE", path = "portfolio/editor", hasBody = true)
    fun deleteModifyPortFolioResponseEpat(
        @Header("token") token: String,
        @Body workIndex : WorkIndex
    ): Call<DeleteModifyPortFolioResponse>

    /**
     * 포트폴리오 수정 페이지 작품 삭제 - 트랜슬레이터
     * @header token
     * @body work_idx
     */
    @HTTP(method = "DELETE", path = "portfolio/translator", hasBody = true)
    fun deleteModifyPortFolioResponseTpat(
        @Header("token") token: String,
        @Body workIndex : WorkIndex
    ): Call<DeleteModifyPortFolioResponse>

    /**
     * 포트폴리오 수정 페이지 작품 삭제 - 기타
     * @header token
     * @body work_idx
     */
    @HTTP(method = "DELETE", path = "portfolio/etc", hasBody = true)
    fun deleteModifyPortFolioResponseEtc(
        @Header("token") token: String,
        @Body workIndex : WorkIndex
    ): Call<DeleteModifyPortFolioResponse>

    /**
     * 포트폴리오 수정
     * @header token
     * @body work_idx
     */
    @PUT("portfolio/detail")
    fun putModifyPortFolioResponse(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String,
        @Body modifyList: ModifyList
    ): Call<PutModifyPortFolioResponse>

    // 지원 라인 ( 각 뷰 별로 주석으로 나눠주세요. )
// 지원 라인 ( 각 뷰 별로 주석으로 나눠주세요. )
    @GET("main/search/{keyword}")
    fun getSearchResponse(
        @Header("Content-Type") content_type: String,
        @Header("token") token : String,
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

    //상세보기-지원
    //c
    @GET("profile/detail/etc/{nickname}")
    fun getDetailEtcResponse(
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

    /**
     * @header token
     * @param flag
     *  0 -> 전체보기
     *  1 -> 크리에이터
     *  2 -> 에디터
     *  3 -> 번역가
     *  4 -> 기타
     */
    @GET("profile/list/{flag}")
    fun getProfileLookUp(
        @Header("token") token: String,
        @Path("flag") flag: Int
    ): Call<GetProfileLookUpResponse>

    @GET("main/notificationState")
    fun getNotificationFlag(
        @Header("token") token: String
    ): Call<GetNotificationFlagResponse>

    @GET("myinfo")
    fun getMyInfo(
        @Header("token") token: String
    ): Call<GetMyInfoResponse>


    /**
     * 클릭배터
     * @header token
     * @body work_idx
     */
    @PUT("banner/click")
    fun putClickBanner(
        @Header("token") token: String,
        @Body putClickBannerRequest : PutClickBannerRequest
    ): Call<PutModifyPortFolioResponse>




}