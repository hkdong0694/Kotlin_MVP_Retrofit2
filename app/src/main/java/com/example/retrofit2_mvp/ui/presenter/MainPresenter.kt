package com.example.retrofit2_mvp.ui.presenter

import android.content.Context
import com.example.retrofit2_mvp.network.Constants
import com.example.retrofit2_mvp.network.api.MovieInfoOpenApiService
import com.example.retrofit2_mvp.network.model.MovieListModel
import com.example.retrofit2_mvp.network.model.dto.DailyBoxOfficeList
import com.example.retrofit2_mvp.network.model.dto.Result
import com.example.retrofit2_mvp.repository.MovieListRepository
import com.example.retrofit2_mvp.repository.NetworkCallback
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
class MainPresenter(context: Context) : MainContract.Presenter {

    private var mContext: Context?= null

    init {
        mContext = context
    }

    private var view: MainContract.View?=null
    private var model: MovieListModel?= null

    override fun setView(view: MainContract.View) {
        this.view = view
        model = mContext?.let { MovieListModel(it) }
    }

    override fun getBoxOfficeList(dateSet: String) {

        model?.getDailyBox(dateSet, object :Callback, NetworkCallback<Result>() {
            override fun onSuccess(responseBody: Result?) {
                val body = responseBody?.boxOfficeResult?.dailyBoxOfficeList
                if(body != null) view?.getListSuccess(body)
            }

            override fun onFailure(code: Int, msg: String?) {
                view?.getListFail(msg)
            }

            override fun onThrowable(t: Throwable?) {
                view?.getListFail(t?.message)
            }

            override fun errorResponse(response: Response<*>?) {
                view?.getListFail(response?.message())
            }
        })

    }

}