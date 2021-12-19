package com.example.sportreservation.ui.main.profile.updatedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sportreservation.databinding.ActivityPhotoDetailBinding
import com.example.sportreservation.utils.loadImage

class PhotoDetailActivity : AppCompatActivity() {

    private var _photoDetailBinding: ActivityPhotoDetailBinding? = null
    private val photoDetailBinding get() = _photoDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _photoDetailBinding = ActivityPhotoDetailBinding.inflate(layoutInflater)
        setContentView(photoDetailBinding?.root)

        val imgUrl = intent.getStringExtra(IMG_URL)
        val username = intent.getStringExtra(USERNAME)

        supportActionBar?.title = username
        photoDetailBinding?.imgUser?.loadImage(imgUrl)
    }

    companion object {
        const val IMG_URL = "imgUrl"
        const val USERNAME = "username"
    }
}