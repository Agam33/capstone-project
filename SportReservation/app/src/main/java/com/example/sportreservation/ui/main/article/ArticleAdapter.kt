package com.example.sportreservation.ui.main.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sportreservation.R
import com.example.sportreservation.databinding.ItemArticleBinding

class ArticleAdapter: RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback</*Data Class*/>() {
        override fun areItemsTheSame(oldItem: /*Data Class*/, newItem: /*Data Class*/): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: /*Data Class*/, newItem: /*Data Class*/): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var listArticles: List</*Data Class*/>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = listArticles[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int = listArticles.size

    inner class ViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: /*Data Class*/) {
            with(binding) {
                tvTitle.text = article
                tvDokter.text = article

                Glide.with(itemView.context)
                    .load("from")
                    .centerCrop()
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgArticle)
            }
        }
    }
}