package com.example.retrofit2_mvp.network.model.dto

/**
 * Retrofit2_MVP
 * Class: BoxOfficeResult
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
data class BoxOfficeResult(
    /*
    boxofficeType	    문자열	    박스오피스 종류를 출력합니다.
    showRange	        문자열	    박스오피스 조회 일자를 출력합니다.
    DailyBoxOfficeLists
    */

    var boxofficeType: String,
    var showRange: String,
    var dailyBoxOfficeList: MutableList<DailyBoxOfficeList>
)