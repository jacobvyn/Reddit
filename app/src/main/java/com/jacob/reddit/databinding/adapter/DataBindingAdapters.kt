package com.jacob.reddit.databinding.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter

interface DataBindingAdapters {

    @BindingAdapter(value = ["imageUrl"])
    fun loadImage(view: ImageView, url: String?)

    @BindingAdapter(value = ["iconRes"])
    fun loadIcon(view: ImageView, iconRes: Int)
}