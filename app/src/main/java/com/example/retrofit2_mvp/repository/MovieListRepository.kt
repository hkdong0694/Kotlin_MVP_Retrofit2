package com.example.retrofit2_mvp.repository

import com.example.retrofit2_mvp.network.Constants
import com.example.retrofit2_mvp.network.MovieInfoOpenApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit2_MVP
 * Class: MovieListRepository
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
class MovieListRepository {

    var retrofit: Retrofit?= null
    var apiService: MovieInfoOpenApiService?= null

    fun initBuild(): MovieInfoOpenApiService? {

        retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()).build()
        apiService = retrofit?.create(MovieInfoOpenApiService::class.java)
        return apiService

    }
}