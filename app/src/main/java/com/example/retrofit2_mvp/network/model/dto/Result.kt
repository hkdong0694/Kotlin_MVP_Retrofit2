package com.example.retrofit2_mvp.network.model.dto

import com.example.retrofit2_mvp.network.model.dto.BoxOfficeResult
import java.io.Serializable

/**
 * Retrofit2_MVP
 * Class: Result
 * Created by 한경동 (Joel) on 2021/05/29.
 * Description:
 */
data class Result(
    var boxOfficeResult: BoxOfficeResult
) : Serializable