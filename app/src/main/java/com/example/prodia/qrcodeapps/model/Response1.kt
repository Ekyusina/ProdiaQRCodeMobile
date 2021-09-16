package com.example.prodia.qrcodeapps.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Response1 : Serializable{
    @SerializedName("CEK_STATUS")
    var cekStatus: String? = null
    @SerializedName("MESSAGE")
    var message: String? = null
    @SerializedName("APP_IP")
    var appip: String? = null
    @SerializedName("APP_ID")
    var appid: String? = null
}