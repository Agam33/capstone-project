package com.example.sportreservation.ui.order.input

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sportreservation.R
import com.example.sportreservation.databinding.ActivityOrderInputBinding
import com.example.sportreservation.utils.DatePickerFragment
import com.example.sportreservation.utils.TimePickerFragment

class OrderInputActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener, DatePickerFragment.DialogDateListener {

    private var _orderInputBinding: ActivityOrderInputBinding? = null
    private val orderInputBinding get() = _orderInputBinding

    private var duration = 0
    private var startTime = 0
    private var date = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _orderInputBinding = ActivityOrderInputBinding.inflate(layoutInflater)
        setContentView(orderInputBinding?.root)

        orderInputBinding?.btnBook?.setOnClickListener {
            validateInput()
        }
    }

    private fun validateInput() {
        val durationInput = orderInputBinding?.edtHours?.text.toString()
        if(durationInput.toInt() < 1 || durationInput.toInt() > 5) {
            Toast.makeText(this, getString(R.string.txt_min_booking), Toast.LENGTH_SHORT).show()
            return
        }



    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {

    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {

    }
}