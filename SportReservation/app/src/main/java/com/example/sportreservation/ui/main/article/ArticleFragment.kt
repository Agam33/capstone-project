package com.example.sportreservation.ui.main.article

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.databinding.FragmentArticleBinding
import com.example.sportreservation.ui.detailarticle.DetailArticleActivity
import com.example.sportreservation.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding

    private val articleAdapter = ArticleAdapter()
    private val viewModel: ArticleFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleAdapter.setOnItemClickListener(object : ArticleAdapter.OnItemClickListener {
            override fun onItemClicked(data: ArticleEntity) {
                val intent = Intent(context, DetailArticleActivity::class.java)
                intent.putExtra(DetailArticleActivity.EXTRA_ARTICLE, data.id)
                startActivity(intent)
            }
        })

        showListArticle()
    }

    private fun showListArticle() {
        viewModel.getArticle().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    showLoading(false)
                    it.data?.let { article ->
                        articleAdapter.submitList(article)
                    }
                }
                Status.ERROR -> {
                    showLoading(false)
                }
                Status.LOADING -> {
                    showLoading(true)
                }
            }
        })

        with(binding?.rvArticle) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = articleAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}