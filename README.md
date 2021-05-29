# Kotlin_MVP_Retrofit2
Retrofit2 +  MVP 영화진흥원 오픈 API Sample 예제

## MVP ( Model, View, Presenter )


## MainContract ( Contract : View, Presenter Interface 관리 )

~~~kotlin

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

~~~

## MovieListRepository.kt ( Model 미완성 )

~~~kotlin

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

interface MovieInfoOpenApiService {

    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    fun getBoxOffice(
        @Query("key")key: String,
        @Query("targetDt")target: String?
    ) : Call<Result>

}


data class Result(
    var boxOfficeResult: BoxOfficeResult
) : Serializable

~~~


## MainPresenter.kt ( Presenter )

~~~kotlin

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


~~~

## MainActivity.kt ( View )

~~~kotlin

class MainActivity : AppCompatActivity(), MainContract.View {

    var mPresenter: MainPresenter?= null
    var adapter: DailyOfficeAdapter?= null
    var dataSet: String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dateInit()
        mPresenter = MainPresenter()
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

~~~