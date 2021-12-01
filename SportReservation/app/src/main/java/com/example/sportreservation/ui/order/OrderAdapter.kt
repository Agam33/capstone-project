package com.example.sportreservation.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sportreservation.R
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.databinding.ItemOrderBinding

class OrderAdapter: PagedListAdapter<OrderEntity, OrderAdapter.ViewHolder>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderEntity = getItem(position)
        if(orderEntity != null) {
            holder.bind(orderEntity)
        }
    }

    inner class ViewHolder(private val binding: ItemOrderBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(orderEntity: OrderEntity) = with(binding) {

            tvName.text = orderEntity.name
            tvSportName.text = orderEntity.sportName
            tvDate.text = orderEntity.date
            tvTime.text = String.format(root.context.getString(R.string.order_time), orderEntity.startTime, orderEntity.endTime)
            tvLocation.text = orderEntity.address
        }
    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<OrderEntity>() {
            override fun areItemsTheSame(oldItem: OrderEntity, newItem: OrderEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: OrderEntity,
                newItem: OrderEntity
            ): Boolean {
                return newItem == oldItem
            }
        }
    }
}