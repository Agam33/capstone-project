package com.example.sportreservation.ui.main.rent.referee


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportreservation.data.source.remote.response.RefereeResponse
import com.example.sportreservation.databinding.FragmentRefereeBinding
import com.example.sportreservation.ui.main.rent.booking.BookingFragment
import com.example.sportreservation.ui.main.rent.booking.BookingFragment.Companion.BOOKING_DATA
import com.example.sportreservation.ui.main.rent.booking.BookingFragment.Companion.BOOKING_TYPE
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

        viewModel.getReferee().observe(viewLifecycleOwner, {
            showReferee(it)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showReferee(referees: List<RefereeResponse>) {
        val refereeAdapter = RefereeAdapter()
        refereeAdapter.setReferees(referees)

        binding?.rvReferee?.let {
            it.adapter = refereeAdapter
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(context)
        }

        refereeAdapter.setOnItemClickCallback(object : RefereeAdapter.OnItemClickCallback {
            override fun openBookingDialog(referee: RefereeResponse) {
                val bookingFragment = BookingFragment()
                val bundle = Bundle().apply {
                    this.putString(BOOKING_TYPE, BOOKING_REFEREE)
                    this.putParcelable(BOOKING_DATA, referee)
                }
                bookingFragment.arguments = bundle
                bookingFragment.show(childFragmentManager, BOOKING_REFEREE)
            }
        })
    }

    companion object {
        const val BOOKING_REFEREE = "rent-referee"
    }
}