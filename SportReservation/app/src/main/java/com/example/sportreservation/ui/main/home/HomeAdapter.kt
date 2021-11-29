package com.example.sportreservation.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity
import com.example.sportreservation.databinding.ItemPlaceBinding
import com.example.sportreservation.utils.loadImage

class HomeAdapter : PagedListAdapter<SportPlaceEntity, HomeAdapter.ViewHolder>(diffCallback) {

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
                tvTitle.text = place.name
                imgPlace.loadImage(place.imgUrl)
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<SportPlaceEntity>() {
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