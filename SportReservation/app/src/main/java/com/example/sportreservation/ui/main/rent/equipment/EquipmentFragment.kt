package com.example.sportreservation.ui.main.rent.equipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportreservation.data.source.remote.response.EquipmentResponse
import com.example.sportreservation.databinding.FragmentEquipmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class EquipmentFragment : Fragment() {

    private var _binding: FragmentEquipmentBinding? = null
    private val binding get() = _binding

    private val viewModel: EquipmentFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEquipmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getEquipment().observe(viewLifecycleOwner, {
            showEquipment(it)
        })
    }

    private fun showEquipment(equipments: List<EquipmentResponse>) {
        val equipmentAdapter = EquipmentAdapter()
        equipmentAdapter.setEquipments(equipments)

        binding?.rvEquipment?.let {
            it.layoutManager = LinearLayoutManager(context)
            it.setHasFixedSize(true)
            it.adapter = equipmentAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}