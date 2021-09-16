package com.example.prodia.qrcodeapps.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RequestBody : Serializable {
    @SerializedName("PARAM1")
    var param1: Param1? = null
    @SerializedName("PARAM2")
    var param2: ArrayList<Param2>? = null
}