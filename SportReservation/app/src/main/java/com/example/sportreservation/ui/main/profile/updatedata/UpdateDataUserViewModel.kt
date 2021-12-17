package com.example.sportreservation.ui.main.profile.updatedata

import androidx.lifecycle.ViewModel
import com.example.sportreservation.ui.main.profile.ProfileFragment.Companion.USER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class UpdateDataUserViewModel: ViewModel() {

    private var dbRef = FirebaseDatabase.getInstance().getReference(USER)
    private var firebaseUser = FirebaseAuth.getInstance().currentUser!!

    fun updateDataUserById(data: HashMap<String, *>) =
        dbRef.child(firebaseUser.uid).updateChildren(data)

}