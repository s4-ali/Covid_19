package com.appsbyasfar.covid19.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class DayOne {
    @SerializedName("Country")
    @Expose
    var country: String? = null

    @SerializedName("CountryCode")
    @Expose
    var countryCode: String? = null

    @SerializedName("Lat")
    @Expose
    var lat: String? = null

    @SerializedName("Lon")
    @Expose
    var lon: String? = null

    @SerializedName("Cases")
    @Expose
    var cases: Int? = null

    @SerializedName("Status")
    @Expose
    var status: String? = null

    @SerializedName("Date")
    @Expose
    var date: String? = null

}