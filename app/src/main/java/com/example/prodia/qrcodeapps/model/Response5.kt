package com.example.prodia.qrcodeapps.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Response5 : Serializable {
    @SerializedName("REPORT_TYPE")
    var reportType: String? = null
    @SerializedName("METHOD_NAME_E")
    var methodNameE: String? = null
    @SerializedName("LANG_TYPE")
    var langType: String? = null
    @SerializedName("PROTOCOL")
    var protocol: String? = null
    @SerializedName("TEST_ID")
    var testId: String? = null
    @SerializedName("FLAG")
    var flag: String? = null
    @SerializedName("COMMENT")
    var comment: String? = null
    @SerializedName("TEST_GROUP")
    var testGroup: String? = null
    @SerializedName("METHOD_ID")
    var methodId: String? = null
    @SerializedName("VISIT")
    var visit: String? = null
    @SerializedName("ID_REF_RANGE")
    var idRefRange: String? = null
    @SerializedName("REG_NO")
    var regNo: String? = null
    @SerializedName("TEST_NAME")
    var testName: String? = null
    @SerializedName("METHOD_NAME")
    var methodName: String? = null
    @SerializedName("PATIENT_ID")
    var patientId: String? = null
    @SerializedName("OUTLET_ID")
    var outletId: String? = null
    @SerializedName("RESULT")
    var result: String? = null
    @SerializedName("UNITS")
    var units: String? = null
    @SerializedName("SEQ")
    var seq: String? = null
}