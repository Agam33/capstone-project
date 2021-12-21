package com.example.sportreservation.ui.main.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sportreservation.databinding.FragmentProfileBinding
import com.example.sportreservation.ui.login.LoginActivity
import com.example.sportreservation.ui.main.profile.updatedata.PhotoDetailActivity
import com.example.sportreservation.ui.main.profile.updatedata.PhotoDetailActivity.Companion.IMG_URL
import com.example.sportreservation.ui.main.profile.updatedata.PhotoDetailActivity.Companion.USERNAME
import com.example.sportreservation.ui.main.profile.updatedata.UpdateDataUserActivity
import com.example.sportreservation.userpreferences.UserModel
import com.example.sportreservation.userpreferences.UserPreference
import com.example.sportreservation.utils.getExtension
import com.example.sportreservation.utils.loadImage
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding

    private lateinit var userPreference: UserPreference

    private lateinit var firebaseUser: FirebaseUser
    private lateinit var firebaseStorage: FirebaseStorage

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

        firebaseUser = Firebase.auth.currentUser!!
        firebaseStorage = Firebase.storage

        profileFragmentViewModel.getUserById(firebaseUser.uid)

        profileFragmentViewModel.dataUser.observe(this, { user ->
            setupUser(user)
        })

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
                    val intent = Intent(requireContext(), LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    }
                    startActivity(intent)
                }
                .show()
        }
    }

    private fun setupUser(user: UserModel) = with(binding!!) {
        tvName.text = user.name
        tvEmail.text = user.email
        tvAddress.text = user.address
        tvPhone.text = user.phone
        imgUser.loadImage(user.imgUrl)
        imgUser.setOnClickListener {
            startActivity(Intent(requireContext(), PhotoDetailActivity::class.java).apply {
                putExtra(IMG_URL, user.imgUrl)
                putExtra(USERNAME, user.name)
            })
        }
    }

    private fun pickImageFromGallery() {
        val intentGallery = Intent(Intent.ACTION_PICK)
        intentGallery.type = "image/*"
        startActivityResult.launch(intentGallery)
    }

    private var startActivityResult =
        registerForActivityResult (ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                uploadFileToFirebase((result.data?.data ?: "") as Uri)
                binding?.imgUser?.loadImage(result.data?.dataString)
            }
        }

    private fun uploadFileToFirebase(uri: Uri) {
        val storageRef = firebaseStorage.reference
        val ref = storageRef.child("$IMAGE_BASE_PATH${firebaseUser.uid}.${getExtension(uri, requireContext())}")
        val uploadTask = ref.putFile(uri)

        uploadTask.continueWithTask {
            ref.downloadUrl
        }.addOnCompleteListener { task ->
            if(task.isSuccessful) {
                val uploadImageUrl = HashMap<String, String>()
                uploadImageUrl["imgUrl"] = task.result.toString()
                profileFragmentViewModel.uploadImageUrl(firebaseUser.uid, uploadImageUrl)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val USER = "Users"
        const val IMAGE_BASE_PATH = "image/"
    }
}