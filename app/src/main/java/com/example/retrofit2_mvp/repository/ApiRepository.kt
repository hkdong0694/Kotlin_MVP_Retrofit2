package com.example.retrofit2_mvp.repository

import android.content.Context
import com.example.retrofit2_mvp.network.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit2_MVP
 * Class: ApiRepository
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
class ApiRepository<T> {

    var retrofit: Retrofit?= null
    var apiInterface: T?= null
    var interceptor: HttpLoggingInterceptor?= null

    fun initBuild(context: Context, service : Class<T>) : T {
        interceptor = HttpLoggingInterceptor()
        interceptor?.level = HttpLoggingInterceptor.Level.BODY
        var builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
        var client = builder.build()
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create() )
            .baseUrl(Constants.BASE_URL)
            .client(client).build()
        apiInterface = retrofit?.create(service)
        return (apiInterface as T)
    }


}