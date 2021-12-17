package com.example.sportreservation.ui.main.rent.booking

import android.content.ContentValues.TAG
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.sportreservation.R
import com.example.sportreservation.data.source.remote.response.EquipmentResponse
import com.example.sportreservation.data.source.remote.response.RefereeResponse
import com.example.sportreservation.databinding.FragmentBookingBinding
import com.example.sportreservation.ui.main.rent.equipment.EquipmentFragment.Companion.BOOKING_EQUIPMENT
import com.example.sportreservation.ui.main.rent.referee.RefereeFragment.Companion.BOOKING_REFEREE
import com.example.sportreservation.userpreferences.UserPreference
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.YearMonth
import java.util.*
import kotlin.collections.HashMap

class BookingFragment : DialogFragment() {

    private var _bookingBinding: FragmentBookingBinding? = null
    private val bookingBinding get() = _bookingBinding

    private var startTime = "99:99"
    private var endTime = ""
    private var startDate = ""

    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference

    private lateinit var userPreference: UserPreference

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

        dbRef = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        userPreference = UserPreference(requireContext())

        val bookingType = arguments?.getString(BOOKING_TYPE)

        bookingBinding?.btnBook?.setOnClickListener {
            if (bookingType == BOOKING_EQUIPMENT) {
                validateInput(
                    "EQUIPMENT",
                    null,
                    arguments?.getParcelable(BOOKING_DATA) as? EquipmentResponse
                )
            } else if (bookingType == BOOKING_REFEREE) {
                validateInput(
                    "REFEREE",
                    arguments?.getParcelable(BOOKING_DATA) as? RefereeResponse,
                    null
                )
            }
        }

        bookingBinding?.btnCancel?.setBackgroundColor(requireActivity().getColor(R.color.red))

        bookingBinding?.btnCancel?.setOnClickListener {
            dismiss()
        }
    }

    private fun validateInput(
        tag: String,
        referee: RefereeResponse?,
        equipment: EquipmentResponse?
    ) {
        val hourOfDay = bookingBinding?.edtHour?.text.toString()
        val minute = bookingBinding?.edtMinute?.text.toString()

        if (hourOfDay.isEmpty() || minute.isEmpty() || hourOfDay.toInt() > 24 || minute.toInt() > 59) {
            Toast.makeText(
                requireContext(),
                "Jam atau menit yang Anda masukan salah",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val timeString = context?.getString(R.string.txt_input_start_time)!!

        val startTimeText = String.format(timeString, hourOfDay.toInt(), minute.toInt())

        startTime = startTimeText

        val dayOfMonth = bookingBinding?.edtDay?.text.toString()

        val calendar = Calendar.getInstance()
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), dayOfMonth.toInt())
        val yearOfMonth =
            YearMonth.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1)
        val daysIntMonth = yearOfMonth.lengthOfMonth()

        if (dayOfMonth.toInt() > daysIntMonth) {
            Toast.makeText(requireContext(), "Tanggal yang Anda masukan salah", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateString = dateFormat.format(calendar.time)

        startDate = dateString

        Log.d(TAG, "validateInput: $startDate - $daysIntMonth ($dateString)")

        val rentName = referee?.name ?: equipment?.name
        val rentId = referee?.id ?: equipment?.id

        /*
           input durasi booking harus 1 - 4 jam
        */
        var durationInput = bookingBinding?.edtHours?.text.toString()
        durationInput = if (durationInput == "") "0" else durationInput

        if (durationInput.toInt() < 1 || durationInput.toInt() > 4) {
            Toast.makeText(
                requireContext(),
                getString(R.string.txt_min_booking),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val arrTime = startTime.split(":").filter { it != ":" }.map { it.toInt() }

        if (arrTime[0] > 24 || arrTime[1] > 59) {
            Toast.makeText(
                requireContext(),
                "Jam atau menit yang Anda masukan salah.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val hourEndTime = arrTime[0] + durationInput.toInt()
        val endTimeText = String.format(timeString, hourEndTime, arrTime[1])

        endTime = endTimeText

        /*
           Mengambil tanggal hari ini lalu di konversi kedalam time millis.
           Setelah itu input dari user dikonversi ke time millis.
           Dicek dengan kondisi jika yg di input telah melewati tanggal sekarang maka akan ditolak.
        */

        val currDateInMillis = Date().time
        val startDateInMillis = dateFormat.parse(startDate).time
        if (startDateInMillis < currDateInMillis) {
            Toast.makeText(
                requireContext(),
                getString(R.string.txt_min_date_booking),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (userPreference.getUser().name!!.isEmpty()
            || userPreference.getUser().phone!!.isEmpty()
        ) {
            Toast.makeText(
                requireContext(),
                getString(R.string.txt_complete_profile),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val packet = HashMap<String, String>()
        packet[USER_ID] = auth.uid!!
        packet[USERNAME] = userPreference.getUser().name!!
        packet[USER_EMAIL] = userPreference.getUser().email!!
        packet[USER_PHONE] = userPreference.getUser().phone!!
        packet[START_DATE] = startDate
        packet[USER_START_TIME] = startTime
        packet[USER_END_TIME] = endTime
        packet["rentType"] = tag
        packet["rentId"] = rentId!!.toString()
        packet["rentName"] = rentName!!
        dbRef.child("Rental").push().setValue(packet)

        dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        _bookingBinding = null
    }

    companion object {
        const val BOOKING_DATA = "data"
        const val BOOKING_TYPE = "type"
        private const val START_DATE = "date"
        private const val USER_ID = "userId"
        private const val USER_START_TIME = "startTime"
        private const val USER_END_TIME = "endTime"
        private const val USERNAME = "username"
        private const val USER_EMAIL = "email"
        private const val USER_PHONE = "phone"
    }
}