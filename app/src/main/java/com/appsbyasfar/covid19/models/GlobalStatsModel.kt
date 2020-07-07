package com.appsbyasfar.covid19.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class GlobalStatsModel : Serializable {
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
}