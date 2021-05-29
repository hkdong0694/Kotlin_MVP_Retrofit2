package com.example.retrofit2_mvp.network.api

import com.example.retrofit2_mvp.network.Constants.BOX_OFFICE_URL
import com.example.retrofit2_mvp.network.model.dto.Result
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

    @GET(BOX_OFFICE_URL)
    fun getBoxOffice(
        @Query("key")key: String,
        @Query("targetDt")target: String?
    ) : Call<Result>

}