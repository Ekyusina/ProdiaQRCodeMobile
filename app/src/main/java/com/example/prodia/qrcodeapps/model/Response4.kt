package com.example.prodia.qrcodeapps.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Response4 : Serializable {
    @SerializedName("LANG_TYPE")
    var langType: String? = null
    @SerializedName("REG_NO")
    var regNo: String? = null
    @SerializedName("TIME")
    var time: String? = null
    @SerializedName("PATIENT_ID")
    var patientId: String? = null
    @SerializedName("SPECIMEN")
    var specimen: String? = null
    @SerializedName("OUTLET_ID")
    var outletId: String? = null
    @SerializedName("SEQ")
    var seq: String? = null
}