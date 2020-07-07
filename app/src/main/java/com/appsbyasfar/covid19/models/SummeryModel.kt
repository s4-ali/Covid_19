package com.appsbyasfar.covid19.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class SummeryModel : Serializable{
    @SerializedName("Global")
    @Expose
    private var global: GlobalStatsModel? = null

    @SerializedName("Countries")
    @Expose
    private var countries: List<CountryStatsModel?>? = null

    @SerializedName("Date")
    @Expose
    private var date: String? = null

    fun getGlobal(): GlobalStatsModel? {
        return global
    }

    fun setGlobal(global: GlobalStatsModel?) {
        this.global = global
    }

    fun getCountries(): List<CountryStatsModel?>? {
        return countries
    }

    fun setCountries(countries: List<CountryStatsModel?>?) {
        this.countries = countries
    }

    fun getDate(): String? {
        return date
    }

    fun setDate(date: String?) {
        this.date = date
    }
}