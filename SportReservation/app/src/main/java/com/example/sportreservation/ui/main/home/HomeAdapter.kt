package com.example.sportreservation.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sportreservation.R
import com.example.sportreservation.databinding.ItemPlaceBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback</*Data Class*/>() {
        override fun areItemsTheSame(oldItem: /*Data Class*/, newItem: /*Data Class*/): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: /*Data Class*/, newItem: /*Data Class*/): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var listPlaces: List</*Data Class*/>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = listPlaces[position]
        holder.bind(place)
    }

    override fun getItemCount(): Int = listPlaces.size

    inner class ViewHolder(private val binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(place: /*Data Class*/) {
            with(binding) {
                tvTitle.text = place

                Glide.with(itemView.context)
                    .load("from")
                    .centerCrop()
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPlace)
            }
        }
    }
}