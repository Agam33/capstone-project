package com.example.sportreservation.ui.main.rent.referee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportreservation.databinding.FragmentRefereeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RefereeFragment : Fragment() {

    private var _binding: FragmentRefereeBinding? = null
    private val binding get() = _binding

    private val viewModel: RefereeFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRefereeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val refereeAdapter = RefereeAdapter()

        viewModel.getReferee().observe(viewLifecycleOwner, {
            refereeAdapter.setReferees(it)
        })

        with(binding?.rvReferee) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = refereeAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}