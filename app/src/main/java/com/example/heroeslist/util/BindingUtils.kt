package com.example.heroeslist.util

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String?) {
    if (url != null) {
        Log.d("17022020", url)
        GlideApp.with(view.context).load(url).into(view)
    }
}