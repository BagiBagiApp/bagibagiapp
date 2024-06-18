package com.bagibagi.app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bagibagi.app.data.model.RecommendationItemModel
import com.bagibagi.app.databinding.ItemRecommendedBinding
import com.bumptech.glide.Glide

class ItemAdapter : ListAdapter<RecommendationItemModel, ItemAdapter.MyViewHolder>(DIFF_CALLBACK)  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRecommendedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
    class MyViewHolder(private val binding : ItemRecommendedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : RecommendationItemModel){
            Glide.with(binding.root)
                .load(item.link_foto)
                .fitCenter()
                .into(binding.itemImage)
            binding.itemName.text = item.nama_produk
            binding.itemQty.text = item.qty.toString()
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecommendationItemModel>() {
            override fun areItemsTheSame(oldItem: RecommendationItemModel, newItem: RecommendationItemModel): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: RecommendationItemModel, newItem: RecommendationItemModel): Boolean {
                return oldItem == newItem
            }
        }
    }


}