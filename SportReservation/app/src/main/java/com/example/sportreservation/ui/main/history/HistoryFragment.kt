package com.example.sportreservation.ui.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportreservation.data.source.local.entity.HistoryEntity
import com.example.sportreservation.databinding.FragmentHistoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {

    private val viewModel: HistoryFragmentViewModel by viewModel()

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding

    private var historyAdapter = HistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getHistoryOrder().observe(viewLifecycleOwner, Observer(this::showListHistory))
    }

    private fun showListHistory(historyEntity: PagedList<HistoryEntity>) {
        if (historyEntity.isEmpty()) isEmptyList(true) else isEmptyList(false)

        historyAdapter.submitList(historyEntity)
        binding?.rvHistory?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = historyAdapter
        }
    }

    private fun isEmptyList(state: Boolean) {
        if (state) {
            binding?.rvHistory?.visibility = View.GONE
            binding?.emptyBox?.frameLayoutFavorite?.visibility = View.VISIBLE
        } else {
            binding?.rvHistory?.visibility = View.VISIBLE
            binding?.emptyBox?.frameLayoutFavorite?.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}