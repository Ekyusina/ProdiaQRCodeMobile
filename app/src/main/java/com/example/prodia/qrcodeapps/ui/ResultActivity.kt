package com.example.prodia.qrcodeapps.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prodia.qrcodeapps.R
import com.example.prodia.qrcodeapps.model.Response2
import com.example.prodia.qrcodeapps.model.Response3
import com.example.prodia.qrcodeapps.model.Response5
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    var adapter: ResultAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val extras = intent.extras

        var dataPasien1: Response2? = null
        var dataPasien2: Response3? = null
        var resultQRCode: ArrayList<Response5>? = null

        if (extras != null) {
            dataPasien1 = extras.get("patientInfo1") as? Response2
            dataPasien2 = extras.get("patientInfo2") as? Response3
            resultQRCode = extras.get("result") as? ArrayList<Response5>
        }

        textViewName.text = dataPasien1?.name
        textViewDOB.text = dataPasien1?.dob
        textRegistrationDate.text = dataPasien2?.reqDate
        textRegNo.text = dataPasien2?.regNo

        adapter = ResultAdapter()
        val manager = LinearLayoutManager(this)
        recyclerViewResult.layoutManager = manager
        adapter?.mResults = resultQRCode
        recyclerViewResult?.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}