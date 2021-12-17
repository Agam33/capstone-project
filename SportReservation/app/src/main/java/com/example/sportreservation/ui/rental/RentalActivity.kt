package com.example.sportreservation.ui.rental

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportreservation.databinding.ActivityRentalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RentalActivity : AppCompatActivity() {

    private var _rentalBinding: ActivityRentalBinding? = null
    private val rentalBinding get() = _rentalBinding

    private val rentalViewModel by viewModels<RentalViewModel>()

    private lateinit var firebaseUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _rentalBinding = ActivityRentalBinding.inflate(layoutInflater)
        setContentView(rentalBinding?.root)

        supportActionBar?.title = RENTAL
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        firebaseUser = FirebaseAuth.getInstance().currentUser!!

        rentalViewModel.getRentalItem(firebaseUser.uid)

        rentalViewModel.rentalItem.observe(this@RentalActivity, {
            showRentItem(it)
        })
    }

    private fun showRentItem(listRentalItem: List<RentalModel>) {
        val rentalAdapter = RentalAdapter()
        rentalAdapter.setRentalItem(listRentalItem as ArrayList<RentalModel>)

        rentalBinding?.rvRental?.let {
            it.layoutManager = LinearLayoutManager(this@RentalActivity)
            it.adapter = rentalAdapter
            it.setHasFixedSize(true)
        }
    }

    companion object {
        const val RENTAL = "Rental"
    }
}