package com.bagibagi.app.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bagibagi.app.data.model.UserItemModel
import com.bagibagi.app.data.response.ProdukItem
import com.bagibagi.app.databinding.ItemProfileBinding
import com.bumptech.glide.Glide

// TODO : ListAdapter -> PagingAdapter
class UserProductAdapter : ListAdapter<UserItemModel,UserProductAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemProfileBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: UserProductAdapter.MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)
    }
    class MyViewHolder(private val binding : ItemProfileBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserItemModel) {
            Glide.with(binding.ivItem.context)
                .load(item.linkFoto)
                .fitCenter()
                .into(binding.ivItem)
            binding.tvItemName.text = item.namaProduk
            binding.tvItemStock.text = "Stok : ${item.qty}"
            binding.cardViewItem.setOnClickListener { // TODO: ProductDetail }
            }
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItemModel>() {
            override fun areItemsTheSame(oldItem: UserItemModel, newItem: UserItemModel): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: UserItemModel, newItem: UserItemModel): Boolean {
                return oldItem == newItem
            }
        }
        const val EXTRA_ITEM = "extra_item"
    }
}