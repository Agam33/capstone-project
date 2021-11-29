package com.example.sportreservation.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportreservation.databinding.FragmentHomeBinding
import com.example.sportreservation.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeFragmentViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

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
        showListBadminton()
        showListBasket()
    }

    private fun showListFutsal() {
        val homeAdapter = HomeAdapter()
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
            this?.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = homeAdapter
        }
    }

    private fun showListBadminton() {
        val homeAdapter = HomeAdapter()

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

    private fun showListBasket() {
        val homeAdapter = HomeAdapter()

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