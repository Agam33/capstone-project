package com.example.sportreservation.ui.order.input

import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sportreservation.R
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity
import com.example.sportreservation.databinding.ActivityOrderInputBinding
import com.example.sportreservation.utils.DatePickerFragment
import com.example.sportreservation.utils.OrderStatus
import com.example.sportreservation.utils.TimePickerFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class OrderInputActivity : AppCompatActivity(),
    TimePickerFragment.DialogTimeListener, DatePickerFragment.DialogDateListener {

    private var _orderInputBinding: ActivityOrderInputBinding? = null
    private val orderInputBinding get() = _orderInputBinding

    private val orderInputViewModel: OrderInputViewModel by viewModel()

    private var startTime = "00:00"
    private var endTime = ""
    private var startDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _orderInputBinding = ActivityOrderInputBinding.inflate(layoutInflater)
        setContentView(orderInputBinding?.root)

        setSupportActionBar(orderInputBinding?.toolbar)

        val sportPlaceEntity = intent.getParcelableExtra<SportPlaceEntity>(EXTRA_BUNDLE_PLACE) as SportPlaceEntity

        orderInputBinding?.tvSportPlace?.text = sportPlaceEntity.name
        orderInputBinding?.tvSportName?.text = sportPlaceEntity.sportName

        showTimePicker()
        showDatePicker()

        orderInputBinding?.btnBook?.setOnClickListener {
            validateInput(sportPlaceEntity)
        }
    }

    private fun validateInput(sportPlaceEntity: SportPlaceEntity) {
        /*
            input durasi booking harus 1 - 4 jam
         */
        var durationInput = orderInputBinding?.edtHours?.text.toString()
        durationInput = if( durationInput == "") "0" else durationInput

        if(durationInput.toInt() < 1 || durationInput.toInt() > 4) {
            Toast.makeText(this, getString(R.string.txt_min_booking), Toast.LENGTH_SHORT).show()
            return
        }

        val timeString = getString(R.string.txt_input_start_time)
        val arrTime = startTime.split(":").filter { it != ":" }.map { it.toInt() }
        val hourEndTime = arrTime[0] + durationInput.toInt()
        val endTimeText = String.format(timeString, hourEndTime, arrTime[1])

        val sportPlaceOpen = sportPlaceEntity.open.split(":").filter { it != ":" }.map { it.toInt() }
        val sportPlaceClose = sportPlaceEntity.close.split(":").filter { it != ":" }.map { it.toInt() }

        if(arrTime.first() < sportPlaceOpen[0]) {
            Toast.makeText(this, "Tempat kami buka jam ${String.format(timeString, sportPlaceOpen[0], sportPlaceOpen[1])}", Toast.LENGTH_LONG).show()
            return
        } else if((hourEndTime > sportPlaceClose[0])) {
            Toast.makeText(this, "Tempat kami tutup jam ${String.format(timeString, sportPlaceClose[0], sportPlaceClose[1])}", Toast.LENGTH_LONG).show()
            return
        }

        endTime = endTimeText

        /*
            Mengambil tanggal hari ini lalu di konversi kedalam time millis.
            Setelah itu input dari user dikonversi ke time millis.
            Dicek dengan kondisi jika yg di input telah melewati tanggal sekarang maka akan ditolak.
         */
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val currDateInMillis = Date().time
        val startDateInMillis = dateFormat.parse(startDate).time
        if(startDateInMillis < currDateInMillis) {
            Toast.makeText(this, getString(R.string.txt_min_date_booking), Toast.LENGTH_SHORT).show()
            return
        }

        orderInputViewModel.insertOrder(OrderEntity(
            sportPlaceEntity.id,
            sportPlaceEntity.name,
            sportPlaceEntity.sportName,
            sportPlaceEntity.address,
            startDate,
            startTime,
            endTime,
            OrderStatus.PESAN
        ))

        finish()
    }

    private fun showTimePicker() {
        orderInputBinding?.startTimePicker?.setOnClickListener {
            val dialogFragment = TimePickerFragment()
            dialogFragment.show(supportFragmentManager, START_TIME)
        }
    }

    private fun showDatePicker() {
        orderInputBinding?.datePicker?.setOnClickListener {
            val dialogFragment = DatePickerFragment()
            dialogFragment.show(supportFragmentManager, DATE)
        }
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateString = dateFormat.format(calendar.time)

        orderInputBinding?.tvDate?.text = dateString

        startDate = dateString
    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
        val timeString = getString(R.string.txt_input_start_time)

        val startTimeText = String.format(timeString, hourOfDay, minute)
        orderInputBinding?.tvTime?.text = startTimeText

        startTime = startTimeText

    }

    companion object {
        const val START_TIME = "start-time"
        const val DATE = "date"
        const val EXTRA_BUNDLE_PLACE = "sport-place"
    }
}