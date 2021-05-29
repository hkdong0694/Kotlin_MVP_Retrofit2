package com.example.retrofit2_mvp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit2_mvp.R
import com.example.retrofit2_mvp.adapter.DailyOfficeAdapter
import com.example.retrofit2_mvp.ui.contract.MainContract
import com.example.retrofit2_mvp.ui.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Retrofit2_MVP
 * Class: MainActivity
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
class MainActivity : AppCompatActivity(), MainContract.View {

    var mPresenter: MainPresenter?= null
    var adapter: DailyOfficeAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter = MainPresenter()
        mPresenter?.setView(this)
        adapter = DailyOfficeAdapter()
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = adapter
        setDailyList()
    }

    private fun setDailyList() {

    }
}