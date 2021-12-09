package com.example.sportreservation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sportreservation.R

fun ImageView.loadImage(url: String?) {
    Glide
        .with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(R.drawable.ic_launcher_background)
        .centerCrop()
        .into(this)
}