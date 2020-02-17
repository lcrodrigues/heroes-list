package com.example.heroeslist.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String?) {
    if (url != null) {
        GlideApp.with(view.context).load(url).into(view)
    }
}