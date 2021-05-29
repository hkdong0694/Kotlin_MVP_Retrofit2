package com.example.retrofit2_mvp.adapter.holder

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit2_mvp.R
import com.example.retrofit2_mvp.network.model.DailyBoxOfficeList

/**
 * Retrofit2_MVP
 * Class: DailyOfficeHolder
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
class DailyOfficeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val vgItem = itemView.findViewById<ViewGroup>(R.id.vg_item)
    private val ivItem = itemView.findViewById<ImageView>(R.id.iv_item)
    private val tvIndex = itemView.findViewById<TextView>(R.id.tv_index)
    private val tvTitle = itemView.findViewById<TextView>(R.id.tv_name)
    private val tvOpenDt = itemView.findViewById<TextView>(R.id.tv_openDt)
    private val tvDirector = itemView.findViewById<TextView>(R.id.tv_director)
    private val rbItem = itemView.findViewById<RatingBar>(R.id.rb_item)
    private val tvRating = itemView.findViewById<TextView>(R.id.tv_rating)

    /*
    rnum	        문자열	        순번을 출력합니다.
    rank	        문자열	        해당일자의 박스오피스 순위를 출력합니다.
    rankInten	    문자열	        전일대비 순위의 증감분을 출력합니다.
    rankOldAndNew	문자열	        랭킹에 신규진입여부를 출력합니다. “OLD” : 기존 , “NEW” : 신규
    movieCd	        문자열	        영화의 대표코드를 출력합니다.
    movieNm	        문자열	        영화명(국문)을 출력합니다.
    openDt	        문자열	        영화의 개봉일을 출력합니다.
    salesAmt	    문자열	        해당일의 매출액을 출력합니다.
    salesShare	    문자열	        해당일자 상영작의 매출총액 대비 해당 영화의 매출비율을 출력합니다.
    salesInten	    문자열	        전일 대비 매출액 증감분을 출력합니다.
    salesChange	    문자열	        전일 대비 매출액 증감 비율을 출력합니다.
    salesAcc	    문자열	        누적매출액을 출력합니다.
    audiCnt	        문자열	        해당일의 관객수를 출력합니다.
    audiInten	    문자열	        전일 대비 관객수 증감분을 출력합니다.
    audiChange	    문자열	        전일 대비 관객수 증감 비율을 출력합니다.
    audiAcc	        문자열	        누적관객수를 출력합니다.
    scrnCnt	        문자열	        해당일자에 상영한 스크린수를 출력합니다.
    showCnt	        문자열	        해당일자에 상영된 횟수를 출력합니다.
    */
    fun onBind(daily: DailyBoxOfficeList) {
        tvIndex.text = daily.rank
        tvTitle.text = daily.movieNm
        tvOpenDt.text = daily.openDt
        tvDirector.text = daily.movieCd
        tvRating.text = daily.audiCnt
    }
}