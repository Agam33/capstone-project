package com.example.sportreservation.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity
import com.example.sportreservation.databinding.ItemPlaceBinding
import com.example.sportreservation.utils.loadImage

class FutsalAdapter : PagedListAdapter<SportPlaceEntity, FutsalAdapter.ViewHolder>(DIFF_CALLBACK) {

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = getItem(position)
        if (place != null) {
            holder.bind(place)
        }
    }

    inner class ViewHolder(private val binding: ItemPlaceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(place: SportPlaceEntity) {
            with(binding) {
                itemView.setOnClickListener {
                    onItemClickListener?.onItemClicked(place)
                }
                tvTitle.text = place.name
                imgPlace.loadImage(place.imgUrl)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(data: SportPlaceEntity)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SportPlaceEntity>() {
            override fun areItemsTheSame(
                oldItem: SportPlaceEntity,
                newItem: SportPlaceEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: SportPlaceEntity,
                newItem: SportPlaceEntity
            ): Boolean {
                return newItem == oldItem
            }
        }
    }
}