package com.example.prodia.qrcodeapps.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class QRCodeResponse : Serializable {
    @SerializedName("RESPONSE1")
    var response1: ArrayList<Response1>? = null
    @SerializedName("RESPONSE2")
    var response2: ArrayList<Response2>? = null
    @SerializedName("RESPONSE3")
    var response3: ArrayList<Response3>? = null
    @SerializedName("RESPONSE4")
    var response4: ArrayList<Response4>? = null
    @SerializedName("RESPONSE5")
    var response5: ArrayList<Response5>? = null
    @SerializedName("RESPONSE6")
    var response6: ArrayList<Response6>? = null
    @SerializedName("RESPONSE7")
    var response7: ArrayList<Response7>? = null
}