package com.bagibagi.app.ui.adapter

import com.bagibagi.app.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class RecommendedItem(val name: String, val quantity: Int)

class RecommendedItemsAdapter(private val items: List<RecommendedItem>) :
    RecyclerView.Adapter<RecommendedItemsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.item_name)
        val quantityTextView: TextView = view.findViewById(R.id.item_quantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommendation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nameTextView.text = item.name
        holder.quantityTextView.text = "Sisa barang: ${item.quantity}"
    }

    override fun getItemCount(): Int = items.size
}
