package com.appsbyasfar.covid19.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class CountryModel : Serializable {
    @SerializedName("Country")
    @Expose
    var country: String? = null

    @SerializedName("Slug")
    @Expose
    var slug: String? = null

    @SerializedName("ISO2")
    @Expose
    var iSO2: String? = null

}