package com.example.retrofit2_mvp.network

import com.example.retrofit2_mvp.network.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit2_MVP
 * Class: MovieInfoOpenApiService
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
interface MovieInfoOpenApiService {

    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    fun getBoxOffice(
        @Query("key")key: String,
        @Query("targetDt")target: String?
    ) : Call<Result>


}