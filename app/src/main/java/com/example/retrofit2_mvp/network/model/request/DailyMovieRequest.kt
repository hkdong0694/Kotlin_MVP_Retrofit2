package com.example.retrofit2_mvp.network.model.request

/**
 * Retrofit2_MVP
 * Class: DailyMovieRequest
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
data class DailyMovieRequest(
    var key: String,
    var targetDt: String
)