package com.example.prodia.qrcodeapps.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.prodia.qrcodeapps.R
import com.example.prodia.qrcodeapps.model.Response5
import kotlinx.android.synthetic.main.row_qrcode_result.view.*

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ViewHolder>()
{
    var mResults: ArrayList<Response5>? = null
        set(results) {
            field = results
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.row_qrcode_result, null))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) = viewHolder.bind(getItems(position))

    fun getItems(position: Int): Response5? = mResults?.get(position)

    override fun getItemCount(): Int = mResults?.size!!

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Response5?) = with(itemView) {
            textViewTestName.text = item?.testName
            textViewResult.text = item?.result
            textViewGOT.text = ""
            textViewGPT.text = ""
        }
    }
}