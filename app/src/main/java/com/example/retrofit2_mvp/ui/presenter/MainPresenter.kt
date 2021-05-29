package com.example.retrofit2_mvp.ui.presenter

import com.example.retrofit2_mvp.ui.contract.MainContract

/**
 * Retrofit2_MVP
 * Class: MainPresenter
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
class MainPresenter : MainContract.Presenter {

    private var view: MainContract.View?=null

    override fun setView(view: MainContract.View) {
        this.view = view
    }


}