package com.example.retrofit2_mvp.repository

import com.example.retrofit2_mvp.network.SchemeConstants.RESPONSE_AUTHENTICATION_FAILURE
import com.example.retrofit2_mvp.network.SchemeConstants.RESPONSE_INTERNAL_SERVER_ERROR
import com.example.retrofit2_mvp.network.SchemeConstants.RESPONSE_INVALID_PARAMETER
import com.example.retrofit2_mvp.network.SchemeConstants.RESPONSE_NOT_FOUND
import com.example.retrofit2_mvp.network.SchemeConstants.RESPONSE_PARAMETER_ERROR
import com.example.retrofit2_mvp.network.SchemeConstants.RESPONSE_UNAUTHORIZED
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Retrofit2_MVP
 * Class: NetworkCallback
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
abstract class NetworkCallback<T> : Callback<T?> {
    abstract fun onSuccess(responseBody: T?) // 조회 성공
    abstract fun onFailure(code: Int, msg: String?) // 조회 실패
    abstract fun onThrowable(t: Throwable?) // Throwable 실패
    abstract fun errorResponse(response: Response<*>?) // 서버 오류


    override fun onResponse(call: Call<T?>, response: Response<T?>) {
        if (null != response) {
            if (response.isSuccessful) {
                // 조회 성공
                onSuccess(response.body())
            } else {
                when (response.code()) {
                    RESPONSE_AUTHENTICATION_FAILURE,
                    RESPONSE_UNAUTHORIZED,
                    RESPONSE_NOT_FOUND,
                    RESPONSE_INVALID_PARAMETER,
                    RESPONSE_PARAMETER_ERROR -> onFailure(
                        response.code(),
                        ""
                    )
                    RESPONSE_INTERNAL_SERVER_ERROR ->                        // 서버 오류
                        errorResponse(response)
                    else -> {
                    }
                }
            }
            response.body()
        }
    }

    override fun onFailure(call: Call<T?>, t: Throwable) {
        onThrowable(t)
    }
}
