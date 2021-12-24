package com.example.sportreservation.ui.main.rent.equipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportreservation.data.source.remote.response.EquipmentResponse
import com.example.sportreservation.databinding.FragmentEquipmentBinding
import com.example.sportreservation.ui.main.rent.booking.BookingFragment
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showEquipment(equipments: List<EquipmentResponse>) {
        val equipmentAdapter = EquipmentAdapter()
        equipmentAdapter.setEquipments(equipments)

        binding?.rvEquipment?.let {
            it.layoutManager = LinearLayoutManager(context)
            it.setHasFixedSize(true)
            it.adapter = equipmentAdapter
        }
        equipmentAdapter.setOnItemClickCallback(object : EquipmentAdapter.OnItemClickCallback {
            override fun openBookingDialog(equipment: EquipmentResponse) {
                val bookingFragment = BookingFragment()
                val bundle = Bundle().apply {
                    this.putString(BookingFragment.BOOKING_TYPE, BOOKING_EQUIPMENT)
                    this.putParcelable(BookingFragment.BOOKING_DATA, equipment)
                }
                bookingFragment.arguments = bundle
                bookingFragment.show(childFragmentManager, BOOKING_EQUIPMENT)
            }
        })
    }

    companion object {
        const val BOOKING_EQUIPMENT = "booking-equipment"
    }
}