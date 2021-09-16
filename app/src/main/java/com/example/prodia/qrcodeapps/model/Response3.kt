package com.example.prodia.qrcodeapps.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Response3 : Serializable {
    @SerializedName("ODOCTOR_ID")
    var oDoctorId: String? = null
    @SerializedName("RESULT_TO_ID")
    var resultToId: String? = null
    @SerializedName("LANG_TYPE")
    var langType: String? = null
    @SerializedName("REG_DATE")
    var reqDate: String? = null
    @SerializedName("REFEREE_ID")
    var refereeId: String? = null
    @SerializedName("FINAL_USER")
    var finalUser: String? = null
    @SerializedName("DOC_ADDRESS")
    var docAddress: String? = null
    @SerializedName("PROTOCOL")
    var protocol: String? = null
    @SerializedName("FLAG")
    var flag: String? = null
    @SerializedName("PRINT_DATE")
    var printDate: String? = null
    @SerializedName("NAME")
    var name: String? = null
    @SerializedName("CITY")
    var city: String? = null
    @SerializedName("VISIT")
    var visit: String? = null
    @SerializedName("REG_NO")
    var regNo: String? = null
    @SerializedName("DOCTOR_ID")
    var doctorId: String? = null
    @SerializedName("PATIENT_ID")
    var patientId: String? = null
    @SerializedName("OUTLET_ID")
    var outletId: String? = null
    @SerializedName("AGE")
    var age: String? = null
}