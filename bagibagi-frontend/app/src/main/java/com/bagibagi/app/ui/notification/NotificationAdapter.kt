package com.bagibagi.app.ui.notification

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bagibagi.app.data.model.NotificationModel
import com.bagibagi.app.data.model.UserItemModel
import com.bagibagi.app.databinding.ItemBarterBinding
import com.bagibagi.app.ui.profile.UserProductAdapter
import com.bumptech.glide.Glide

class NotificationAdapter : ListAdapter<NotificationModel, NotificationAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemBarterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class MyViewHolder(private val binding : ItemBarterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : NotificationModel) {
            Glide.with(binding.root)
                .load(item.barangRequester.linkFoto)
                .fitCenter()
                .into(binding.imgItem)
            Glide.with(binding.root)
                .load(item.barangRecipient.linkFoto)
                .fitCenter()
                .into(binding.imgItem2)
            binding.Username.text = item.requester.username
            binding.Username2.text = item.recipient.username
            binding.itemName1.text = item.barangRequester.namaProduk
            binding.itemName2.text = item.barangRecipient.namaProduk
            binding.itemQty.text = "Jumlah: ${item.qtyBarangRequester.toString()}"
            binding.itemQty2.text = "Jumlah: ${item.qtyBarangRequested.toString()}"
            binding.tvStatusNotif.text = item.status
            if(item.status == "Accepted"){
                binding.acceptButton.visibility = View.GONE
                binding.rejectButton.visibility = View.GONE
            }
            binding.cardNotification.setOnClickListener {  }
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NotificationModel>() {
            override fun areItemsTheSame(oldItem: NotificationModel, newItem: NotificationModel): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: NotificationModel, newItem: NotificationModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}