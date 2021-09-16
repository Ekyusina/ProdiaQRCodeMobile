package com.example.prodia.qrcodeapps.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Response7 : Serializable {
    @SerializedName("REF_VALUE")
    var refValue: String? = null
    @SerializedName("ID_REF_RANGE")
    var idRefRange: String? = null
    @SerializedName("LANG_TYPE")
    var langType: String? = null
    @SerializedName("TEST_ID")
    var testId: String? = null
    @SerializedName("OUTLET_ID")
    var outletId: String? = null
    @SerializedName("DESCR")
    var Descr: String? = null
}