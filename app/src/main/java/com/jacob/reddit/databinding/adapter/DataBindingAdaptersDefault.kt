package com.jacob.reddit.databinding.adapter

import android.widget.ImageView
import com.jacob.reddit.utils.show
import com.squareup.picasso.Picasso

class DataBindingAdaptersDefault : DataBindingAdapters {

    override fun loadImage(view: ImageView, url: String?) {
        if (url == null) {
            view.show(false)
            return
        }
        Picasso.get().load(url).into(view)
    }

    override fun loadIcon(view: ImageView, iconRes: Int) {
        view.setImageResource(iconRes)
    }
}




