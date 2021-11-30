package com.example.sportreservation.ui.detailarticle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.databinding.ActivityDetailArticleBinding
import com.example.sportreservation.utils.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailArticleBinding

    private val viewModel: DetailArticleViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getInt(EXTRA_ARTICLE)
            viewModel.getById(id)
            viewModel.detailArticle.observe(this, {
                populateArticle(it)
            })
        }
    }

    private fun populateArticle(detailArticle: ArticleEntity) {
        with(binding) {
            tvTitle.text = detailArticle.title
            tvDokter.text = detailArticle.writer
            tvContent.text = detailArticle.content
            imgArticle.loadImage(detailArticle.imgUrl)
        }
    }

    companion object {
        const val EXTRA_ARTICLE = "extra_article"
    }
}