package com.example.sportreservation.ui.rental

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sportreservation.ui.rental.RentalActivity.Companion.RENTAL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RentalViewModel : ViewModel() {

    private var dbRef = FirebaseDatabase.getInstance().getReference(RENTAL)

    private var _rentalItem = MutableLiveData<List<RentalModel>>()
    val rentalItem: LiveData<List<RentalModel>> get() = _rentalItem

    fun getRentalItem(userId: String) {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val rentalModel = snapshot.getValue(RentalModel::class.java)
                if (rentalModel != null) {

                    val listItem = snapshot.children.map {
                        it.getValue(RentalModel::class.java)
                    }.filter {
                        it?.userId == userId
                    }

                    _rentalItem.postValue(listItem as List<RentalModel>?)

                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}