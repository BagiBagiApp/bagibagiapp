package com.bagibagi.app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bagibagi.app.data.model.ItemModel
import com.bagibagi.app.databinding.ItemRecommendedBinding
import com.bumptech.glide.Glide

class ItemAdapter : ListAdapter<ItemModel, ItemAdapter.MyViewHolder>(DIFF_CALLBACK)  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRecommendedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
    class MyViewHolder(private val binding : ItemRecommendedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ItemModel){
            Glide.with(binding.root)
                .load(item.link_foto)
                .fitCenter()
                .into(binding.itemImage)
            binding.itemName.text = item.nama_produk
            binding.itemQty.text = "Sisa Barang : ${item.qty.toString()}"
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemModel>() {
            override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
                return oldItem == newItem
            }
        }
    }


}