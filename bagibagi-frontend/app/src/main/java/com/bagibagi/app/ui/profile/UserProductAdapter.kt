package com.bagibagi.app.ui.profile

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bagibagi.app.data.model.UserItemModel
import com.bagibagi.app.data.response.ProdukItem
import com.bagibagi.app.databinding.ItemProfileBinding
import com.bagibagi.app.ui.itemdetailuser.UserItemDetailActivity
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
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
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
            binding.tvItemStock.text = "Sisa Barang : ${item.qty}"
            binding.cardViewItem.setOnClickListener {
                val intent = Intent(binding.root.context, UserItemDetailActivity::class.java)
                intent.putExtra(ITEM_ID, item.id)
                binding.root.context.startActivity(intent)
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
        const val ITEM_ID = "id"
    }
}