package com.example.sportreservation.ui.main.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sportreservation.databinding.FragmentProfileBinding
import com.example.sportreservation.ui.AuthActivity
import com.example.sportreservation.ui.main.profile.updatedata.UpdateDataUserActivity
import com.example.sportreservation.userpreferences.UserPreference
import com.example.sportreservation.utils.loadImage
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding

    private lateinit var userPreference: UserPreference

    private lateinit var firebaseUser: FirebaseUser

    private val profileFragmentViewModel by viewModels<ProfileFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userPreference = UserPreference(requireContext())
        val userModel = userPreference.getUser()

        firebaseUser = FirebaseAuth.getInstance().currentUser!!

        profileFragmentViewModel.getUserById(firebaseUser.uid)

        profileFragmentViewModel.dataUser.observe(this, { user ->
            binding?.tvName?.text = user.name
            binding?.tvEmail?.text = user.email
            binding?.tvAddress?.text = user.address
            binding?.tvPhone?.text = user.phone
        })

        with(binding) {
//            this?.tvName?.text = userModel.name
//            this?.tvEmail?.text = userModel.email
//            this?.tvAddress?.text = userModel.address
//            this?.tvPhone?.text = userModel.phone
            this?.imgUser?.loadImage(userModel.imgUrl)
        }

        binding?.addImgBtn?.setOnClickListener {
            pickImageFromGallery()
        }

        binding?.fabEditProfile?.setOnClickListener {
            startActivity(Intent(context, UpdateDataUserActivity::class.java))
        }

        binding?.btnSignOut?.setOnClickListener {
            Snackbar.make(binding?.root!!, "Anda yakin ini keluar aplikasi?", Snackbar.LENGTH_SHORT)
                .setAction("Yes") {
                    Firebase.auth.signOut()
                    val intent = Intent(requireContext(), AuthActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    }
                    startActivity(intent)
                }
                .show()
        }
    }

    private fun pickImageFromGallery() {
        val intentGallery = Intent(Intent.ACTION_PICK)
        intentGallery.type = "image/*"
        startActivityResult.launch(intentGallery)
    }

    private var startActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == AppCompatActivity.RESULT_OK) {
                binding?.imgUser?.loadImage(it.data?.dataString)
                userPreference.setImage(it.data?.dataString)
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val USER = "Users"
    }
}