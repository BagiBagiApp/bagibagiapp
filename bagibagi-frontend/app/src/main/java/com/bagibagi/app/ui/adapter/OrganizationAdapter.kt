package com.bagibagi.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bagibagi.app.R
import com.bagibagi.app.data.model.OrganizationModel
import com.bumptech.glide.Glide

class OrganizationAdapter(private val items: List<OrganizationModel>) : RecyclerView.Adapter<OrganizationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val organizationImage: ImageView = view.findViewById(R.id.organization_image)
        val organizationName: TextView = view.findViewById(R.id.organization_name)
        val organizationLocation: TextView = view.findViewById(R.id.organization_location)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_organization, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.organizationName.text = item.nama
        holder.organizationLocation.text = item.alamat
        Glide.with(holder.organizationImage.context).load(item.link_foto).into(holder.organizationImage)
    }

    override fun getItemCount(): Int = items.size
}