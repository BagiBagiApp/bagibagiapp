package com.bagibagi.app.ui.barter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bagibagi.app.data.model.UserItemModel
import com.bagibagi.app.databinding.ItemProfileBinding
import com.bagibagi.app.ui.itemdetail.ItemDetailActivity
import com.bagibagi.app.ui.itemdetailuser.UserItemDetailActivity
import com.bagibagi.app.ui.profile.UserProductAdapter
import com.bumptech.glide.Glide

class ChooseItemAdapter(
    private val itemRequestedID : Int,
    private val context: Context
): ListAdapter<UserItemModel, ChooseItemAdapter.ChooseItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseItemViewHolder {
        val binding = ItemProfileBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ChooseItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChooseItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, itemRequestedID, context)
    }

    class ChooseItemViewHolder(private val binding : ItemProfileBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: UserItemModel,
                 itemRequestedID:Int,
                 context: Context) {
            Glide.with(binding.ivItem.context)
                .load(item.linkFoto)
                .fitCenter()
                .into(binding.ivItem)
            binding.tvItemName.text = item.namaProduk
            binding.tvItemStock.text = "Sisa Barang : ${item.qty}"
            binding.cardViewItem.setOnClickListener {
                val intent = Intent(binding.root.context, ConfirmActivity::class.java)
                intent.putExtra(EXTRA_USERITEM_ID, item.id)
                intent.putExtra(EXTRA_RECIPIENTITEM_ID,itemRequestedID)
                binding.root.context.startActivity(intent)
                if(context is Activity) { context.finish() }
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
        const val EXTRA_USERITEM_ID = "extra_useritem_id"
        const val EXTRA_RECIPIENTITEM_ID = "extra_recipientitem_id"
    }
}