package com.example.prodia.qrcodeapps.apiservice

import com.example.prodia.qrcodeapps.model.QRCodeResponse
import com.example.prodia.qrcodeapps.model.RecaptchaVerifyResponse
import com.example.prodia.qrcodeapps.model.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST("ws_in_out")
    fun getResult(
            @Body() body: RequestBody?
    ): Call<QRCodeResponse?>?

    @Headers("Content-Type: application/x-www-form-urlencoded; charset=utf-8")
    @POST("/recaptcha/api/siteverify")
    fun verifyResponse(@QueryMap params: MutableMap<String?, String?>?): Call<RecaptchaVerifyResponse?>?
}