package com.example.sportreservation.ui.detailplace

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sportreservation.R
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity
import com.example.sportreservation.databinding.ActivityDetailPlaceBinding
import com.example.sportreservation.ui.order.input.OrderInputActivity
import com.example.sportreservation.ui.order.input.OrderInputActivity.Companion.EXTRA_BUNDLE_PLACE
import com.example.sportreservation.utils.OrderStatus
import com.example.sportreservation.utils.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPlaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPlaceBinding

    private val viewModel: DetailPlaceViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getInt(EXTRA_PLACE)
            viewModel.getById(id)
            viewModel.detailPlace.observe(this, {
                populatePlace(it)
                setOrder(it)
            })
        }
    }

    private fun populatePlace(detailPlace: SportPlaceEntity) = with(binding) {
        tvName.text = detailPlace.name
        tvOpen.text = String.format(getString(R.string.open), detailPlace.open, detailPlace.close)
        tvPrice.text = String.format(getString(R.string.price), detailPlace.cost)
        tvPhone.text = String.format(getString(R.string.phone), detailPlace.phone)
        tvFacility.text = String.format(getString(R.string.facility), detailPlace.facility)
        tvAddress.text = String.format(getString(R.string.address), detailPlace.address)
        imgPlace.loadImage(detailPlace.imgUrl)
    }

    private fun setOrder(detailPlace: SportPlaceEntity) {
        viewModel.getOrderById(detailPlace.id).observe(this, { order ->
            if (order == null) {
                binding.btnBook.text = getString(R.string.txt_booking)
                binding.btnBook.setBackgroundColor(getColor(R.color.green_500))
                binding.btnBook.isEnabled = true
                binding.btnBook.setOnClickListener {
                    startActivity(Intent(this, OrderInputActivity::class.java).apply {
                        putExtra(EXTRA_BUNDLE_PLACE, detailPlace)
                    })
                }
            } else {
                when (order.orderStatus) {
                    OrderStatus.PESAN -> {
                        binding.btnBook.text = getString(R.string.txt_already_booked)
                        binding.btnBook.setBackgroundColor(getColor(R.color.red))
                        binding.btnBook.isEnabled = false
                    }
                    OrderStatus.BATALKAN -> {}
                    OrderStatus.SELESAI -> {}
                }
            }
        })
    }

    companion object {
        const val EXTRA_PLACE = "extra_place"
    }
}