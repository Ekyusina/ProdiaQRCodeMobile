package com.example.prodia.qrcodeapps.model

import com.google.gson.annotations.SerializedName

class Param1 {
    @SerializedName("TYPE")
    var type: String? = "01"
    @SerializedName("APPID")
    var appId: String? = "DB5BAEDCD0"
    @SerializedName("PARAMIN")
    var paramIn: String? = "1"
    @SerializedName("PARAMOUT")
    var paramOut: String? = "7"
    @SerializedName("ACTION")
    var action: String? = "get_result_qrcode"
}