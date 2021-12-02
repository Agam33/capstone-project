package com.example.sportreservation.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity
import com.example.sportreservation.databinding.FragmentHomeBinding
import com.example.sportreservation.ui.detailplace.DetailPlaceActivity
import com.example.sportreservation.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeFragmentViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val homeAdapter = HomeAdapter()
    //private val basketAdapter = BasketAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showListFutsal()
        showListBasket()
        showListBadminton()
    }

    private fun showListFutsal() {
        homeAdapter.setOnItemClickListener(object : HomeAdapter.OnItemClickListener {
            override fun onItemClicked(data: SportPlaceEntity) {
                val intent = Intent(context, DetailPlaceActivity::class.java)
                intent.putExtra(DetailPlaceActivity.EXTRA_PLACE, data.id)
                startActivity(intent)
            }
        })

        viewModel.getFutsalPlace().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    success()
                    it.data?.let { futsal ->
                        homeAdapter.submitList(futsal)
                    }
                }
                Status.ERROR -> {
                    error()
                }
                Status.LOADING -> {
                    loading()
                }
            }
        })

        with(binding?.rvFutsal) {
            this?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = homeAdapter
        }
    }

    private fun showListBasket() {
        homeAdapter.setOnItemClickListener(object : HomeAdapter.OnItemClickListener {
            override fun onItemClicked(data: SportPlaceEntity) {
                val intent = Intent(context, DetailPlaceActivity::class.java)
                intent.putExtra(DetailPlaceActivity.EXTRA_PLACE, data.id)
                startActivity(intent)
            }
        })

        viewModel.getBasketPlace().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    success()
                    it.data?.let { basket ->
                        homeAdapter.submitList(basket)
                    }
                }
                Status.ERROR -> {
                    error()
                }
                Status.LOADING -> {
                    loading()
                }
            }
        })
        with(binding?.rvBasket) {
            this?.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = homeAdapter
        }
    }

    private fun showListBadminton() {
        homeAdapter.setOnItemClickListener(object : HomeAdapter.OnItemClickListener {
            override fun onItemClicked(data: SportPlaceEntity) {
                val intent = Intent(context, DetailPlaceActivity::class.java)
                intent.putExtra(DetailPlaceActivity.EXTRA_PLACE, data.id)
                startActivity(intent)
            }
        })

        viewModel.getBadmintonPlace().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    success()
                    it.data?.let { badminton ->

                        homeAdapter.submitList(badminton)
                    }
                }
                Status.ERROR -> {
                    error()
                }
                Status.LOADING -> {
                    loading()
                }
            }
        })

        with(binding?.rvBadminton) {
            this?.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = homeAdapter
        }
    }

    private fun success() {

    }

    private fun loading() {

    }

    private fun error() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}