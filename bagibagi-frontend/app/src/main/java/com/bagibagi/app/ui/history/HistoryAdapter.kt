package com.bagibagi.app.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bagibagi.app.data.model.HistoryModel
import com.bagibagi.app.data.model.NotificationModel
import com.bagibagi.app.databinding.ItemHistoryBinding
import com.bumptech.glide.Glide

class HistoryAdapter : ListAdapter<HistoryModel, HistoryAdapter.HistoryViewHolder>(DIFF_CALLBACK){
    class HistoryViewHolder(private val binding : ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : HistoryModel){
            with(binding){
                tvStatusHistory.text = item.status
                Glide.with(binding.root)
                    .load(item.barangRequester.linkFoto)
                    .fitCenter()
                    .into(imgItem)
                Glide.with(binding.root)
                    .load(item.barangRecipient.linkFoto)
                    .fitCenter()
                    .into(imgItem2)
                Username.text = item.requester.username
                Username2.text = item.recipient.username
                itemName1.text = item.barangRequester.namaProduk
                itemName2.text = item.barangRecipient.namaProduk
                itemQty.text = item.qtyBarangRequester.toString()
                itemQty2.text = item.qtyBarangRequested.toString()
                historyCode.text = item.id.toString()
                cardHistory.setOnClickListener {  }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HistoryModel>() {
            override fun areItemsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}