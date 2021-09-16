package com.example.prodia.qrcodeapps.model

import com.google.gson.annotations.SerializedName

class RecaptchaVerifyResponse {
    val success: Boolean = false
    val challenge_ts: String? = null
    val apk_package_name: String? = null

    @SerializedName("error-codes")
    private val errorCodes: List<String>? = null
}