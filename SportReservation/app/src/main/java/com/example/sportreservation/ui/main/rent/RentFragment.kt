package com.example.sportreservation.ui.main.rent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.sportreservation.R
import com.example.sportreservation.databinding.FragmentRentBinding
import com.google.android.material.tabs.TabLayoutMediator

class RentFragment : Fragment() {

    private var _binding: FragmentRentBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionsPagerAdapter = SectionsPagerAdapter(this@RentFragment)
        with(binding) {
            this?.viewPager?.adapter = sectionsPagerAdapter
            this?.let {
                TabLayoutMediator(it.tabs, it.viewPager) { tab, position ->
                    tab.text = resources.getString(TAB_TITLES[position])
                }.attach()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.title_referee, R.string.title_equipment)
    }

}