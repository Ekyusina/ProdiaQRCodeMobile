package com.example.prodia.qrcodeapps.ui

import com.example.prodia.qrcodeapps.model.RequestBody
import com.example.prodia.qrcodeapps.model.Response2
import com.example.prodia.qrcodeapps.model.Response3
import com.example.prodia.qrcodeapps.model.Response5

class ResultView {
    interface InitView {
        fun showLoading()
        fun hideLoading()
        fun showResultSuccess(patientInfo1: Response2?, patientInfo2: Response3?, result: ArrayList<Response5>?)
        fun showResultFailed(errorMessage: String?)
        fun reCaptchaVerified()
        fun reCaptchaFail(message: String)
    }

    interface Result {
        fun showResult(param: RequestBody)
        fun successListener(token: String, key: String)
        fun failureListener(message: String)
    }
}