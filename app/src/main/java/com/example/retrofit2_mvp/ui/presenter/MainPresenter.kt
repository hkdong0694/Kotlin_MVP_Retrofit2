package com.example.retrofit2_mvp.ui.presenter

import com.example.retrofit2_mvp.network.Constants
import com.example.retrofit2_mvp.network.MovieInfoOpenApiService
import com.example.retrofit2_mvp.network.model.DailyBoxOfficeList
import com.example.retrofit2_mvp.network.model.Result
import com.example.retrofit2_mvp.repository.MovieListRepository
import com.example.retrofit2_mvp.ui.contract.MainContract
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

/**
 * Retrofit2_MVP
 * Class: MainPresenter
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
class MainPresenter : MainContract.Presenter {

    private var view: MainContract.View?=null
    private var apiService: MovieInfoOpenApiService?= null

    override fun setView(view: MainContract.View) {
        this.view = view
        apiService = MovieListRepository().initBuild()
    }

    override fun getBoxOfficeList(dateSet: String) {
        apiService?.getBoxOffice(Constants.KEY, dateSet)?.enqueue(object: Callback, retrofit2.Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                val body = response.body()
                val list : MutableList<DailyBoxOfficeList>? = body?.boxOfficeResult?.dailyBoxOfficeList
                if(list != null) view?.getListSuccess(list)
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                view?.getListFail(t.message)
                t.printStackTrace()
            }
        })
    }

}