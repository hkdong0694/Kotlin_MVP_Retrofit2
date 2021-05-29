package com.example.retrofit2_mvp.network

/**
 * Retrofit2_MVP
 * Class: ScemeConstants
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
object SchemeConstants {

    /* NetworkCallback.class */
    var RESPONSE_SUCCESS = 200 // 성공
    var RESPONSE_INVALID_PARAMETER = 400 // 요청 오류
    var RESPONSE_AUTHENTICATION_FAILURE = 401 // 인증 실패
    var RESPONSE_UNAUTHORIZED = 403 // 권한 없음
    var RESPONSE_NOT_FOUND = 404 // 요청한 API가 없음
    var RESPONSE_INTERNAL_SERVER_ERROR = 500 // 서버 오류
    var RESPONSE_PARAMETER_ERROR = 422 // 파라미터 에러


}