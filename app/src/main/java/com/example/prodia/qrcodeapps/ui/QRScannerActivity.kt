package com.example.prodia.qrcodeapps.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.prodia.qrcodeapps.R
import kotlinx.android.synthetic.main.activity_qrscanner.*
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

class QRScannerActivity : AppCompatActivity(), ZBarScannerView.ResultHandler {
    lateinit var scannerView: ZBarScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrscanner)

        flashToggle.setOnClickListener {
            if (flashToggle.isSelected){
                offFlashLight()
            }else{
                onFlashLight()
            }
        }

        initializeQRCamera()
    }

    private fun initializeQRCamera() {
        scannerView = ZBarScannerView(this)
        scannerView.setResultHandler(this)
        scannerView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTranslucent))
        scannerView.setBorderColor(ContextCompat.getColor(this, R.color.colorPrimary))
        scannerView.setLaserColor(ContextCompat.getColor(this, R.color.colorPrimary))
        scannerView.setBorderStrokeWidth(10)
        scannerView.setSquareViewFinder(true)
        scannerView.setupScanner()
        scannerView.setAutoFocus(true)
        startQRCamera()
        containerScanner.addView(scannerView)
    }

    private fun startQRCamera() {
        scannerView.startCamera()
    }

    private fun onFlashLight() {
        flashToggle.isSelected = true
        scannerView.flash = true
    }

    private fun offFlashLight() {
        flashToggle.isSelected = false
        scannerView.flash = false
    }

    override fun handleResult(rawResult: Result?) {
        onQrResult(rawResult?.contents)
        rawResult?.contents?.let { Log.d("qrresult", it) }
    }

    private fun onQrResult(contents: String?) {
        if (contents.isNullOrEmpty())
            Toast.makeText(this, "Empty Qr Result", Toast.LENGTH_SHORT).show()
        else if (!contents.startsWith("PRODIA")){
            alertClick()
        }else{
            val stringToPassBack = contents
            // Put the String to pass back into an Intent and close this activity
            val intent = Intent()
            intent.putExtra("qrResult", stringToPassBack)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    fun alertClick(){
        AlertDialog.Builder(this)
            // Judul
            .setTitle("QRCode Invalid")
            // Pesan yang di tamopilkan
            .setMessage("Pastikan QRCode yang dibaca adalah QRCode dari laporan hasil pemeriksaan Prodia")
            .setPositiveButton("Try Again", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
                initializeQRCamera()
            })
            .setCancelable(false)
            .show()
    }
}