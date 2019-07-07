package com.android.hipart_android.network

import com.android.hipart_android.ui.login.data.PostLoginRequest
import com.android.hipart_android.ui.login.data.PostLoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


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

    // 지원 라인 ( 각 뷰 별로 주석으로 나눠주세요. )

    // 형민 라인 ( 각 뷰 별로 주석으로 나눠주세요. )

}