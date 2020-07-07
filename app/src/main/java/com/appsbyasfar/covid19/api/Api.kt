package com.appsbyasfar.covid19.api

import com.appsbyasfar.covid19.models.CountryModel
import com.appsbyasfar.covid19.models.SummeryModel
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("countries")
    fun getCountries(): Call<List<CountryModel>>

    @GET("summary")
    fun getCompleteSummery(): Call<SummeryModel>

}