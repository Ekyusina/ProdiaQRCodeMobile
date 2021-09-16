package com.example.prodia.qrcodeapps.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Response6 : Serializable {
    @SerializedName("REPORT_TYPE")
    var reportType: String? = null
    @SerializedName("NOTE")
    var note: String? = null
    @SerializedName("LANG_TYPE")
    var langType: String? = null
    @SerializedName("REG_NO")
    var regNo: String? = null
    @SerializedName("PATIENT_ID")
    var patientId: String? = null
    @SerializedName("OUTLET_ID")
    var outletId: String? = null
}