package com.example.prodia.qrcodeapps.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Response2 : Serializable {
    @SerializedName("SEX_ID")
    var sexId: String? = null
    @SerializedName("SEX")
    var sex: String? = null
    @SerializedName("PHONE")
    var phone: String? = null
    @SerializedName("HP")
    var hp: String? = null
    @SerializedName("EMAIL")
    var email: String? = null
    @SerializedName("NAME")
    var name: String? = null
    @SerializedName("FRONT_TITLE")
    var frontTitle: String? = null
    @SerializedName("TITLE_ID")
    var titleId: String? = null
    @SerializedName("CITY_ID")
    var cityId: String? = null
    @SerializedName("CITY")
    var city: String? = null
    @SerializedName("DOB")
    var dob: String? = null
    @SerializedName("ADDRESS")
    var address: String? = null
    @SerializedName("PATIENT_ID")
    var patientId: String? = null
}