package com.example.retrofit2_mvp.network.model.dto

/**
 * Retrofit2_MVP
 * Class: DailyBoxOfficeList
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
data class DailyBoxOfficeList(
    /*
          rnum	            문자열	        순번을 출력합니다.
          rank	            문자열	        해당일자의 박스오피스 순위를 출력합니다.
          rankInten	        문자열	        전일대비 순위의 증감분을 출력합니다.
          rankOldAndNew	    문자열	        랭킹에 신규진입여부를 출력합니다. “OLD” : 기존 , “NEW” : 신규
          movieCd	        문자열	        영화의 대표코드를 출력합니다.
          movieNm	        문자열	        영화명(국문)을 출력합니다.
          openDt	        문자열	        영화의 개봉일을 출력합니다.
          salesAmt	        문자열	        해당일의 매출액을 출력합니다.
          salesShare	    문자열	        해당일자 상영작의 매출총액 대비 해당 영화의 매출비율을 출력합니다.
          salesInten	    문자열	        전일 대비 매출액 증감분을 출력합니다.
          salesChange	    문자열	        전일 대비 매출액 증감 비율을 출력합니다.
          salesAcc	        문자열	        누적매출액을 출력합니다.
          audiCnt	        문자열	        해당일의 관객수를 출력합니다.
          audiInten	        문자열	        전일 대비 관객수 증감분을 출력합니다.
          audiChange	    문자열	        전일 대비 관객수 증감 비율을 출력합니다.
          audiAcc	        문자열	        누적관객수를 출력합니다.
          scrnCnt	        문자열	        해당일자에 상영한 스크린수를 출력합니다.
          showCnt	        문자열	        해당일자에 상영된 횟수를 출력합니다.
      */

    var rnum: String,
    var rank: String,
    var rankInten: String,
    var rankOldAndNew: String,
    var movieCd: String,
    var movieNm: String,
    var openDt: String,
    var salesAmt: String,
    var salesInten: String,
    var salesChange: String,
    var salesAcc: String,
    var audiCnt: String,
    var audiInten: String,
    var audiChange: String,
    var audiAcc: String,
    var scrnCnt: String,
    var showCnt: String
)