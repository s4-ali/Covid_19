package com.appsbyasfar.covid19.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class CountryStatsModel : Serializable {
    @SerializedName("Country")
    @Expose
    private var country: String? = null

    @SerializedName("CountryCode")
    @Expose
    private var countryCode: String? = null

    @SerializedName("Slug")
    @Expose
    private var slug: String? = null

    @SerializedName("NewConfirmed")
    @Expose
    private var newConfirmed: Int? = null

    @SerializedName("TotalConfirmed")
    @Expose
    private var totalConfirmed: Int? = null

    @SerializedName("NewDeaths")
    @Expose
    private var newDeaths: Int? = null

    @SerializedName("TotalDeaths")
    @Expose
    private var totalDeaths: Int? = null

    @SerializedName("NewRecovered")
    @Expose
    private var newRecovered: Int? = null

    @SerializedName("TotalRecovered")
    @Expose
    private var totalRecovered: Int? = null

    @SerializedName("Date")
    @Expose
    private var date: String? = null

    fun getCountry(): String? {
        return country
    }

    fun setCountry(country: String?) {
        this.country = country
    }

    fun getCountryCode(): String? {
        return countryCode
    }

    fun setCountryCode(countryCode: String?) {
        this.countryCode = countryCode
    }

    fun getSlug(): String? {
        return slug
    }

    fun setSlug(slug: String?) {
        this.slug = slug
    }

    fun getNewConfirmed(): Int? {
        return newConfirmed
    }

    fun setNewConfirmed(newConfirmed: Int?) {
        this.newConfirmed = newConfirmed
    }

    fun getTotalConfirmed(): Int? {
        return totalConfirmed
    }

    fun setTotalConfirmed(totalConfirmed: Int?) {
        this.totalConfirmed = totalConfirmed
    }

    fun getNewDeaths(): Int? {
        return newDeaths
    }

    fun setNewDeaths(newDeaths: Int?) {
        this.newDeaths = newDeaths
    }

    fun getTotalDeaths(): Int? {
        return totalDeaths
    }

    fun setTotalDeaths(totalDeaths: Int?) {
        this.totalDeaths = totalDeaths
    }

    fun getNewRecovered(): Int? {
        return newRecovered
    }

    fun setNewRecovered(newRecovered: Int?) {
        this.newRecovered = newRecovered
    }

    fun getTotalRecovered(): Int? {
        return totalRecovered
    }

    fun setTotalRecovered(totalRecovered: Int?) {
        this.totalRecovered = totalRecovered
    }

    fun getDate(): String? {
        return date
    }

    fun setDate(date: String?) {
        this.date = date
    }
}

