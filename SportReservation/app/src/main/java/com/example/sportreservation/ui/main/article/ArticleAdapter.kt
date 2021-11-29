package com.example.sportreservation.ui.main.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sportreservation.R
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.databinding.ItemArticleBinding
import com.example.sportreservation.utils.loadImage

class ArticleAdapter : PagedListAdapter<ArticleEntity, ArticleAdapter.ViewHolder>(diffCallback) {

    private val differ = AsyncListDiffer(this, diffCallback)

    var listArticles: List<ArticleEntity>
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

    inner class ViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleEntity) {
            with(binding) {
                tvTitle.text = article.title
                tvDokter.text = article.writer

                imgArticle.loadImage(article.imgUrl)
            }
        }
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