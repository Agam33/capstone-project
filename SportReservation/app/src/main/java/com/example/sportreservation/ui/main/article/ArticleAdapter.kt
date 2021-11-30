package com.example.sportreservation.ui.main.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.databinding.ItemArticleBinding
import com.example.sportreservation.utils.loadImage

class ArticleAdapter : PagedListAdapter<ArticleEntity, ArticleAdapter.ViewHolder>(diffCallback) {

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            holder.bind(article)
        }
    }

    inner class ViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleEntity) {
            itemView.setOnClickListener {
                onItemClickListener?.onItemClicked(article)
            }
            with(binding) {
                tvTitle.text = article.title
                tvDokter.text = article.writer
                imgArticle.loadImage(article.imgUrl)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(data: ArticleEntity)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ArticleEntity>() {
            override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ArticleEntity,
                newItem: ArticleEntity
            ): Boolean {
                return newItem == oldItem
            }
        }
    }
}