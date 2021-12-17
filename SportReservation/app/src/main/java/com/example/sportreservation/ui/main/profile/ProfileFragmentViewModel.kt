package com.example.sportreservation.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sportreservation.ui.main.profile.ProfileFragment.Companion.USER
import com.example.sportreservation.userpreferences.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileFragmentViewModel: ViewModel() {

    private var dbRef = FirebaseDatabase.getInstance().getReference(USER)

    private var _dataUser = MutableLiveData<UserModel>()
    val dataUser: LiveData<UserModel> get() = _dataUser

    fun getUserById(userId: String) {
        dbRef.child(userId).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataUser = snapshot.getValue(UserModel::class.java)
                if(dataUser != null) {
                    _dataUser.postValue(dataUser!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}