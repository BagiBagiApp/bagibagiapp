package com.bagibagi.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bagibagi.app.R
import com.bagibagi.app.data.model.RecommendationItem
import com.bumptech.glide.Glide

class RecommendationAdapter(private val items: List<RecommendationItem>) : RecyclerView.Adapter<RecommendationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ImageView = view.findViewById(R.id.item_image)
        val itemName: TextView = view.findViewById(R.id.item_name)
        val itemStock: TextView = view.findViewById(R.id.item_qty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recommended, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.itemName.text = item.nama_produk
        holder.itemStock.text = "Sisa barang: ${item.qty}"
        Glide.with(holder.itemImage.context).load(item.link_foto).into(holder.itemImage)
    }

    override fun getItemCount(): Int = items.size
}