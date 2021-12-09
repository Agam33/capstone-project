package com.example.sportreservation.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sportreservation.databinding.FragmentProfileBinding
import com.example.sportreservation.userpreferences.UserPreference
import com.example.sportreservation.utils.loadImage

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var userPreference = UserPreference(requireContext())
        val userModel = userPreference.getUser()

        with(binding) {
            this?.tvName?.text = userModel.name
            this?.tvEmail?.text = userModel.email
            this?.tvAddress?.text = userModel.address
            this?.tvPhone?.text = userModel.phone

            this?.imgUser?.loadImage("https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}