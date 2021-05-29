package com.example.retrofit2_mvp.ui.contract

import com.example.retrofit2_mvp.network.model.dto.DailyBoxOfficeList

/**
 * Retrofit2_MVP
 * Class: MainContract
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
interface MainContract {

    interface View {

        fun getListSuccess(list: MutableList<DailyBoxOfficeList>?)

        fun getListFail(message: String?)

    }

    interface Presenter {

        fun setView(view: View)

        fun getBoxOfficeList(dataSet: String)

    }

}