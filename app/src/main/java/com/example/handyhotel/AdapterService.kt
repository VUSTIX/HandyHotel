package com.example.handyhotel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterService(private val context: Context, private val dataListServices: List<String>, private val dataListDescription: List<String>) :
    RecyclerView.Adapter<AdapterService.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.sample_service, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataServices = dataListServices[position]
        val dataDescription = dataListDescription[position]
        holder.textViewServices.text = dataServices
        holder.textViewDescription.text = dataDescription
    }

    override fun getItemCount(): Int {
        return dataListServices.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewServices: TextView = itemView.findViewById(R.id.textViewServiceName)
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
    }
}
