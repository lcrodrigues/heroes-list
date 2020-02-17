package com.example.heroeslist.util

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String?) {
    Log.d("17022020", url)
    if (url != null) {
        Glide.with(view).load(url).into(view)
    }
}