package com.example.sportreservation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.sportreservation.R

fun ImageView.loadImage(url: String?) {
    Glide
        .with(this.context)
        .load(url)
        .error(R.drawable.ic_launcher_background)
        .centerCrop()
        .into(this)
}