# Kotlin_MVP_Retrofit2
Retrofit2 +  MVP 영화진흥원 오픈 API Sample 예제

## MVP ( Model, View, Presenter )

## ApiRepository, Networkcallback.kt

~~~kotlin

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

abstract class NetworkCallback<T> : Callback<T?> {
    abstract fun onSuccess(responseBody: T?) // 조회 성공
    abstract fun onFailure(code: Int, msg: String?) // 조회 실패
    abstract fun onThrowable(t: Throwable?) // Throwable 실패
    abstract fun errorResponse(response: Response<*>?) // 서버 오류


    override fun onResponse(call: Call<T?>, response: Response<T?>) {
        if (null != response) {
            if (response.isSuccessful) {
                // 조회 성공
                onSuccess(response.body())
            } else {
                when (response.code()) {
                    RESPONSE_AUTHENTICATION_FAILURE,
                    RESPONSE_UNAUTHORIZED,
                    RESPONSE_NOT_FOUND,
                    RESPONSE_INVALID_PARAMETER,
                    RESPONSE_PARAMETER_ERROR -> onFailure(
                        response.code(),
                        ""
                    )
                    RESPONSE_INTERNAL_SERVER_ERROR ->                        // 서버 오류
                        errorResponse(response)
                    else -> {
                    }
                }
            }
            response.body()
        }
    }

    override fun onFailure(call: Call<T?>, t: Throwable) {
        onThrowable(t)
    }
}

~~~

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

## MovieListModel.kt ( Model )

~~~kotlin
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
~~~


## MainPresenter.kt ( Presenter )

~~~kotlin

class MainPresenter(context: Context) : MainContract.Presenter {

    private var view: MainContract.View?=null
    private var model: MovieListModel?= null

    init {
        model = MovieListModel(context)
    }

    override fun setView(view: MainContract.View) {
        this.view = view
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