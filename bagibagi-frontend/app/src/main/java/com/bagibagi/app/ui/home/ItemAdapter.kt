package com.bagibagi.app.ui.home

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bagibagi.app.data.model.ItemModel
import com.bagibagi.app.databinding.ItemRecommendedBinding
import com.bagibagi.app.ui.itemdetail.ItemDetailActivity
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
                .into(binding.ivItemConfirm2)
            binding.itemName.text = item.nama_produk
            binding.itemQty.text = "Sisa Barang : ${item.qty.toString()}"
            binding.cardViewItem.setOnClickListener {
                val intent = Intent(binding.root.context,ItemDetailActivity::class.java)
                intent.putExtra(EXTRA_ITEM_ID,item.id)
                binding.root.context.startActivity(intent)
            }
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
        const val EXTRA_ITEM_ID = "extra_item_id"
    }


}