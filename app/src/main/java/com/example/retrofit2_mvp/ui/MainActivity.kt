package com.example.retrofit2_mvp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit2_mvp.R
import com.example.retrofit2_mvp.adapter.DailyOfficeAdapter
import com.example.retrofit2_mvp.network.model.dto.DailyBoxOfficeList
import com.example.retrofit2_mvp.ui.contract.MainContract
import com.example.retrofit2_mvp.ui.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Retrofit2_MVP
 * Class: MainActivity
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
class MainActivity : AppCompatActivity(), MainContract.View {

    var mPresenter: MainPresenter?= null
    var adapter: DailyOfficeAdapter?= null
    var dataSet: String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dateInit()
        mPresenter = MainPresenter(this)
        mPresenter?.setView(this)
        adapter = DailyOfficeAdapter()
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = adapter
        setDailyList()
    }

    private fun dateInit() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val mNow = System.currentTimeMillis()
        val mDate = Date(mNow)
        dateFormat.format(mDate)
        dataSet = dateFormat(dateFormat.calendar.get(Calendar.YEAR), dateFormat.calendar.get(
            Calendar.MONTH) + 1 , dateFormat.calendar.get(Calendar.DAY_OF_MONTH) -1)
    }

    private fun dateFormat(year: Int, month: Int, day: Int) : String {
        var result =
            if (month <= 9 && day <= 9) year.toString() + "0" + month + "0" + day
            else if (day <= 9) year.toString() + month + "0" + day
            else if (month <= 9) year.toString() + "0" + month + day
            else year.toString() + month + day
        return result
    }

    private fun setDailyList() {
        prog.visibility = View.VISIBLE
        dataSet?.let { mPresenter?.getBoxOfficeList(it) }
    }

    override fun getListSuccess(list: MutableList<DailyBoxOfficeList>?) {
        prog.visibility = View.GONE
        if (list != null) adapter?.setData(list)
    }

    override fun getListFail(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        prog.visibility = View.GONE
    }
}