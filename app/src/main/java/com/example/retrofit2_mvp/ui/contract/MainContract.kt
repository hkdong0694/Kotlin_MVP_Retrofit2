package com.example.retrofit2_mvp.ui.contract

/**
 * Retrofit2_MVP
 * Class: MainContract
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
interface MainContract {

    interface View {

    }

    interface Presenter {
        fun setView(view: View)
    }

}