package com.example.handyhotel

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterHotel(private val context: Context, private val dataListHotels: List<String>, private val dataListPrice: List<String>, private val dataListPhotos: List<Int>) :
    RecyclerView.Adapter<AdapterHotel.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.sample_hotel, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataHotels = dataListHotels[position]
        val dataPrice = dataListPrice[position]
        val dataPhoto = dataListPhotos[position]
        holder.textViewHotels.text = dataHotels
        holder.textViewPrice.text = dataPrice
        holder.imageViewPhoto.setImageResource(dataPhoto)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, HotelPageActivity::class.java)
            intent.putExtra("dataHotels", dataHotels)
            intent.putExtra("dataPrice", dataPrice)
            intent.putExtra("dataPhoto", dataPhoto)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataListHotels.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewHotels: TextView = itemView.findViewById(R.id.textView29)
        val textViewPrice: TextView = itemView.findViewById(R.id.textView30)
        val imageViewPhoto: ImageView = itemView.findViewById(R.id.imageViewHotel)
    }
}
