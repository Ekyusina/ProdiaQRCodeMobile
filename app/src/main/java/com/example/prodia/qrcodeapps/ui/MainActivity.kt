package com.example.prodia.qrcodeapps.ui

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.example.prodia.qrcodeapps.R
import com.example.prodia.qrcodeapps.apiservice.BuildConfig
import com.example.prodia.qrcodeapps.model.*
import com.google.android.gms.safetynet.SafetyNet
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ResultView.InitView, View.OnClickListener {
    var qrResult: String? = null
    var param: RequestBody? = null
    var param1: Param1? = null
    var param2: ArrayList<Param2>? = null
    var progressdialog : ProgressDialog? = null
    var isCaptchaVerified: Boolean = true
    var presenter: ResultPresenter? = null

    var isChecked = false

    var url : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar as Toolbar?)

        handleFirebaseDynamicLinks(intent)

        presenter = ResultPresenter(this)

        iv_qr_scanner.setOnClickListener(this)

        param = RequestBody()

        buttonSubmit.setOnClickListener(this)

        checkForPermission()

        try {
            val pInfo: PackageInfo = getPackageManager().getPackageInfo(getPackageName(), 0)
            val version = pInfo.versionName
            val appName = pInfo.applicationInfo.loadLabel(packageManager).toString()

            tvAppName.text = "$appName v.$version"
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        progressdialog = ProgressDialog(this@MainActivity)
        progressdialog?.setMessage("Please Wait...")

        layoutQRCodeValid.visibility = View.GONE

        checkbox.setMinFrame(20)
        checkbox.setMaxFrame(50)

        checkbox.setOnClickListener(this)

        buttonSubmit.isEnabled = qrResult!=null && isChecked
    }



    private fun handleFirebaseDynamicLinks(intent: Intent) {
        // 1
        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener { dynamicLinkData ->
                // 2
                if (dynamicLinkData != null) {
                    showDynamicLinkOffer(dynamicLinkData.link)
                }
            }
            // 3
            .addOnFailureListener(this) { e ->
                Log.d("DynamicLinkError", e.localizedMessage)
            }
    }

    private fun showDynamicLinkOffer(uri: Uri?) {
        val promotionCode = uri?.getQueryParameter("code")
        if (promotionCode.isNullOrBlank().not()) {
        } else {
        }
    }

    private fun checkForPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                requestPermission()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == QRSCANNER_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                // Get String data from Intent
                val returnString = data!!.getStringExtra("qrResult")
                qrResult = returnString

                buttonSubmit.isEnabled = qrResult!=null && isChecked
                layoutQRCodeValid.visibility = View.VISIBLE
            }
        }
    }

    override fun showLoading() {
        progressdialog?.show()
    }

    override fun hideLoading() {
        progressdialog?.dismiss()
    }

    override fun showResultSuccess(patientInfo1: Response2?, patientInfo2: Response3?, result: ArrayList<Response5>?) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("patientInfo1", patientInfo1)
        intent.putExtra("patientInfo2", patientInfo2)
        intent.putExtra("result", result)
        startActivity(intent)
        clearFindViewByIdCache()
    }

    override fun showResultFailed(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun reCaptchaVerified() {
        checkbox.setSpeed(1F)
        checkbox.playAnimation()
        buttonSubmit.isEnabled = qrResult!=null && isChecked
    }

    override fun reCaptchaFail(message: String) {
        showAlertWithButton(message)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.buttonSubmit -> {
                param1 = Param1()
                param1?.type = "01"
                param1?.appId = "DB5BAEDCD0"
                param1?.paramIn = "1"
                param1?.paramOut = "7"
                param1?.action = "get_result_qrcode"

                var code: Param2? = Param2()
                code?.p1 = qrResult

                param2 = ArrayList()
                code?.let { param2?.add(it) }

                param?.param1 = param1
                param?.param2 = param2

                param?.let { presenter?.showResult(it) }
            }
            R.id.iv_qr_scanner -> {
                if (qrResult!=null){
                    clearCaptcha()
                }
                val intent = Intent(this, QRScannerActivity::class.java)
                startActivityForResult(intent,
                    QRSCANNER_ACTIVITY_REQUEST_CODE
                )
            }
            R.id.checkbox -> {
                isChecked = if (!isChecked) {
                    SafetyNet.getClient(this@MainActivity)
                        .verifyWithRecaptcha(BuildConfig.PUBLIC_KEY)
                        .addOnSuccessListener {
                            val token = it.tokenResult
                            if (!token.isEmpty()){
                                presenter?.successListener(token, BuildConfig.PRIVATE_KEY)
                                isChecked = true
                            }
                        }
                        .addOnFailureListener {
                            it.message?.let { it1 -> showAlertWithButton(it1) }
                            isChecked = false
                            Log.e("Failure", it.message.toString())
                            Log.e("package name", applicationContext.packageName)
                        }
                    isChecked
                } else {
                    checkbox.setSpeed(-1F)
                    checkbox.playAnimation()
                    false
                }
            }
        }
    }

    private fun showAlertWithButton(message: String) {
        AlertDialog.Builder(this).setTitle(title).setMessage(message).setCancelable(false)
            .setPositiveButton("close", null).create().show()
    }

    fun clearCaptcha(){
        isChecked = false
        checkbox.setSpeed(-1F)
        checkbox.playAnimation()
    }

    companion object {
        private const val QRSCANNER_ACTIVITY_REQUEST_CODE = 101
        private const val CAMERA_PERMISSION_REQUEST_CODE = 102
    }
}