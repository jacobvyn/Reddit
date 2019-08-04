package com.jacob.reddit.databinding.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import com.jacob.reddit.databinding.transformer.CircleTransformation
import com.jacob.reddit.utils.show

class DataBindingAdaptersDefault : DataBindingAdapters {
    override fun loadImage(
        view: ImageView,
        url: String?,
        placeholder: Drawable?,
        error: Drawable?,
        circle: Boolean
    ) {
        if (url == null) return
        val circleTransformation = if (circle) CircleTransformation() else null
        loadImage(view, url, placeholder, error, circleTransformation)
    }

    override fun loadImage(
        view: ImageView,
        url: String?,
        placeholder: Drawable?,
        error: Drawable?,
        transformation: Transformation?
    ) {
        if (url == null) {
            view.show(false)
            return
        }
        val builder = Picasso.get().load(url)
        transformation?.run { builder.transform(this) }
        placeholder?.run { builder.placeholder(this) }
        error?.run { builder.error(this) }
        builder.into(view)
    }

    override fun loadIcon(view: ImageView, iconRes: Int) {
        view.setImageResource(iconRes)
    }
}




