package com.example.prodia.qrcodeapps.ui

import com.example.prodia.qrcodeapps.apiservice.ApiClient
import com.example.prodia.qrcodeapps.apiservice.ApiInterface
import com.example.prodia.qrcodeapps.apiservice.BuildConfig
import com.example.prodia.qrcodeapps.model.*
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*


class ResultPresenter(initView: ResultView.InitView) : ResultView.Result {
    private val initView: ResultView.InitView = initView

    override fun showResult(param: RequestBody) {
        initView.showLoading()
        val apiInterface: ApiInterface? = ApiClient().getRetrofitInstance(BuildConfig.BASE_URL)?.create(ApiInterface::class.java)
        val call: Call<QRCodeResponse?>? = apiInterface?.getResult(param)
        call?.enqueue(object : Callback<QRCodeResponse?> {

            override fun onResponse(call: Call<QRCodeResponse?>, response: Response<QRCodeResponse?>) {
                initView.hideLoading()

                if (response.isSuccessful) {
                    val response1: ArrayList<Response1>? = response?.body()?.response1
                    val response2: ArrayList<Response2>? = response?.body()?.response2
                    val response3: ArrayList<Response3>? = response?.body()?.response3
                    val response5: ArrayList<Response5>? = response?.body()?.response5

                    if (response1 != null && response1?.get(0).cekStatus == "SUCCESS"){
                        initView.showResultSuccess(response2?.get(0), response3?.get(0), response5)
                    }else if (response1?.get(0)?.cekStatus == "FAILED" || response1?.get(0)?.cekStatus == "ERROR"){
                        initView.showResultFailed(response1?.get(0)?.message)
                    }
                } else {
                    val gson = Gson()
                    val adapter = gson.getAdapter(QRCodeResponse::class.java)
                    try {
                        if (response.errorBody() != null){
                            var response = adapter.fromJson(
                                    response.errorBody()!!.string())
                            initView.showResultFailed(response?.response1?.get(0)?.message)
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<QRCodeResponse?>, t: Throwable) {
                initView.showResultFailed(t.toString())
                initView.hideLoading()
                t.printStackTrace()
            }
        })
    }

    override fun successListener(token: String, key: String) {
        val params: MutableMap<String?, String?> = HashMap()
        params["response"] = token
        params["secret"] = key

        val apiInterface: ApiInterface? = ApiClient().getRetrofitInstance(BuildConfig.URL_RECAPTCHA)?.create(
            ApiInterface::class.java)
        val call: Call<RecaptchaVerifyResponse?>? = apiInterface?.verifyResponse(params)
        call?.enqueue(object : Callback<RecaptchaVerifyResponse?> {

            override fun onResponse(call: Call<RecaptchaVerifyResponse?>, response: Response<RecaptchaVerifyResponse?>) {
                val recaptchaVerifyResponse = response?.body()
                if (response.isSuccessful) {
                    if (recaptchaVerifyResponse != null && recaptchaVerifyResponse.success){
                        initView.reCaptchaVerified()
                    }
                }else{
                    val gson = Gson()
                    val adapter = gson.getAdapter(QRCodeResponse::class.java)
                    try {
                        if (response.errorBody() != null){
                            var response = adapter.fromJson(
                                response.errorBody()!!.string())
                            response?.response1?.get(0)?.message?.let { initView.reCaptchaFail(it) }
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<RecaptchaVerifyResponse?>, t: Throwable) {
                initView.reCaptchaFail(t.toString())
                t.printStackTrace()
            }
        })
    }

    override fun failureListener(message: String) {
        initView.reCaptchaFail(message)
    }

}