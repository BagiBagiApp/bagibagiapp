package com.bagibagi.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bagibagi.app.R

class BarterCardAdapter(private val barterItems: List<BarterItem>) :
    RecyclerView.Adapter<BarterCardAdapter.BarterCardViewHolder>() {

    // Data class to hold barter item information (replace with your actual data)
    data class BarterItem(val item1ImageResId: Int, val item2ImageResId: Int)

    // ViewHolder for individual card items
    class BarterCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item1ImageView: ImageView = itemView.findViewById(R.id.item1ImageView)
        val item2ImageView: ImageView = itemView.findViewById(R.id.item2ImageView)
        val acceptButton: Button = itemView.findViewById(R.id.acceptButton)
        val rejectButton: Button = itemView.findViewById(R.id.rejectButton)
    }

    // Called to create a new ViewHolder instance
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarterCardViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_barter, parent, false) // Inflate your card layout
        return BarterCardViewHolder(itemView)
    }

    // Called to bind data to a ViewHolder
    override fun onBindViewHolder(holder: BarterCardViewHolder, position: Int) {
        val currentItem = barterItems[position]

        // Set image resources (replace with actual image loading logic)
        holder.item1ImageView.setImageResource(currentItem.item1ImageResId)
        holder.item2ImageView.setImageResource(currentItem.item2ImageResId)

        // Set click listeners for buttons (implement your logic here)
        holder.acceptButton.setOnClickListener {
            // Handle accept action for the item at 'position'
        }
        holder.rejectButton.setOnClickListener {
            // Handle reject action for the item at 'position'
        }
    }

    // Returns the total number of items in the data set
    override fun getItemCount(): Int {
        return barterItems.size
    }
}