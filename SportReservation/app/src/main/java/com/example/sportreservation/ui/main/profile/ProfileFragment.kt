package com.example.sportreservation.ui.main.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sportreservation.databinding.FragmentProfileBinding
import com.example.sportreservation.ui.main.profile.updatedata.UpdateDataUserActivity
import com.example.sportreservation.userpreferences.UserPreference
import com.example.sportreservation.utils.loadImage

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding

    private lateinit var userPreference: UserPreference

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

        with(binding) {
            this?.tvName?.text = userModel.name
            this?.tvEmail?.text = userModel.email
            this?.tvAddress?.text = userModel.address
            this?.tvPhone?.text = userModel.phone
            this?.imgUser?.loadImage(userModel.imgUrl)
        }

        binding?.addImgBtn?.setOnClickListener {
            pickImageFromGallery()
        }

        binding?.fabEditProfile?.setOnClickListener {
            startActivity(Intent(context, UpdateDataUserActivity::class.java))
        }
    }

    private fun pickImageFromGallery() {
        val intentGallery = Intent(Intent.ACTION_PICK)
        intentGallery.type = "image/*"
        startActivityResult.launch(intentGallery)
    }

    private var startActivityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == AppCompatActivity.RESULT_OK) {
            binding?.imgUser?.loadImage(it.data?.dataString)
            userPreference.setImage(it.data?.dataString)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}