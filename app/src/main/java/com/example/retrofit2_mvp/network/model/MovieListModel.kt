package com.example.retrofit2_mvp.network.model

import android.content.Context
import com.example.retrofit2_mvp.network.Constants
import com.example.retrofit2_mvp.network.api.MovieInfoOpenApiService
import com.example.retrofit2_mvp.network.model.dto.Result
import com.example.retrofit2_mvp.network.model.request.DailyMovieRequest
import com.example.retrofit2_mvp.repository.ApiRepository
import com.example.retrofit2_mvp.repository.NetworkCallback

/**
 * Retrofit2_MVP
 * Class: MovieListModel
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
class MovieListModel(context: Context) {

    var repository: ApiRepository<MovieInfoOpenApiService>? = null
    var apiInterface: MovieInfoOpenApiService?= null

    init {
        repository = ApiRepository()
        apiInterface = repository?.initBuild(context, MovieInfoOpenApiService::class.java )
    }

    fun getDailyBox(targetDt: String, callback: NetworkCallback<Result>) {
        if( null != repository && null != apiInterface && null != callback ) {
            var request = DailyMovieRequest(Constants.KEY, targetDt)
            apiInterface?.getBoxOffice(Constants.KEY, targetDt)?.enqueue(callback)
        }
    }

}