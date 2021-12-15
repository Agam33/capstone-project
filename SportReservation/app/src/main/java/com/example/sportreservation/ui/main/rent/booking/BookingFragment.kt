package com.example.sportreservation.ui.main.rent.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.sportreservation.databinding.FragmentBookingBinding
import com.example.sportreservation.utils.DatePickerFragment
import com.example.sportreservation.utils.TimePickerFragment

class BookingFragment: DialogFragment(),
    TimePickerFragment.DialogTimeListener, DatePickerFragment.DialogDateListener {

    private var _bookingBinding: FragmentBookingBinding? = null
    private val  bookingBinding get() = _bookingBinding

    private var startTime = "00:00"
    private var endTime = ""
    private var startDate = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bookingBinding = FragmentBookingBinding.inflate(inflater, container, false)
        return bookingBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookingBinding?.btnBook?.setOnClickListener {

        }

        bookingBinding?.btnCancel?.setOnClickListener {
            onDestroy()
        }
    }

    private fun validateInput() {

    }


    override fun onDestroy() {
        super.onDestroy()
        _bookingBinding = null
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {

    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {

    }
}