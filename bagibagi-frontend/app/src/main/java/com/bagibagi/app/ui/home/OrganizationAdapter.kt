package com.bagibagi.app.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.datastore.preferences.protobuf.Internal
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bagibagi.app.R
import com.bagibagi.app.data.model.OrganizationModel
import com.bagibagi.app.data.model.UserItemModel
import com.bagibagi.app.databinding.ItemOrganizationBinding
import com.bagibagi.app.databinding.ItemRecommendedBinding
import com.bagibagi.app.ui.profile.UserProductAdapter
import com.bumptech.glide.Glide

class OrganizationAdapter() : ListAdapter<OrganizationModel, OrganizationAdapter.MyViewHolder>(DIFF_CALLBACK){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemOrganizationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
    class MyViewHolder(private val binding : ItemOrganizationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : OrganizationModel){
           Glide.with(binding.root)
               .load(item.link_foto)
               .fitCenter()
               .into(binding.organizationImage)
            binding.organizationName.text = item.nama
            binding.organizationLocation.text = item.alamat
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<OrganizationModel>() {
            override fun areItemsTheSame(oldItem: OrganizationModel, newItem: OrganizationModel): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: OrganizationModel, newItem: OrganizationModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}