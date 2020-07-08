package com.appsbyasfar.covid19.api

import com.appsbyasfar.covid19.models.CountryDayModel
import com.appsbyasfar.covid19.models.CountryModel
import com.appsbyasfar.covid19.models.SummeryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("countries")
    fun getCountries(): Call<List<CountryModel>>

    @GET("summary")
    fun getCompleteSummery(): Call<SummeryModel>

    @GET("total/dayone/country/{south-africa}")
    fun getCountryDetailsFromFirstDay(@Path("south-africa") country:String): Call<List<CountryDayModel>>

}