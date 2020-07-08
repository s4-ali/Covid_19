package com.appsbyasfar.covid19.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class CountryDayModel {
    @SerializedName("Country")
    @Expose
    var country: String? = null

    @SerializedName("CountryCode")
    @Expose
    var countryCode: String? = null

    @SerializedName("Province")
    @Expose
    var province: String? = null

    @SerializedName("City")
    @Expose
    var city: String? = null

    @SerializedName("CityCode")
    @Expose
    var cityCode: String? = null

    @SerializedName("Lat")
    @Expose
    var lat: String? = null

    @SerializedName("Lon")
    @Expose
    var lon: String? = null

    @SerializedName("Confirmed")
    @Expose
    var confirmed: Int? = null

    @SerializedName("Deaths")
    @Expose
    var deaths: Int? = null

    @SerializedName("Recovered")
    @Expose
    var recovered: Int? = null

    @SerializedName("Active")
    @Expose
    var active: Int? = null

    @SerializedName("Date")
    @Expose
    var date: String? = null

}