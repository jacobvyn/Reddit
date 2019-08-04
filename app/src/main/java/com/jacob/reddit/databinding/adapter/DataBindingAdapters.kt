package com.jacob.reddit.databinding.adapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Transformation

/**
 * DataBinding instance adapters for layouts
 */
interface DataBindingAdapters {

    @BindingAdapter(value = ["imageUrl", "placeholder", "error", "circle"], requireAll = false)
    fun loadImage(
        view: ImageView,
        url: String?,
        placeholder: Drawable?,
        error: Drawable?,
        circle: Boolean
    )

    @BindingAdapter(
        value = ["imageUrl", "placeholder", "error", "transformation"],
        requireAll = false
    )
    fun loadImage(
        view: ImageView,
        url: String?,
        placeholder: Drawable?,
        error: Drawable?,
        transformation: Transformation?
    )

    @BindingAdapter(value = ["iconRes"])
    fun loadIcon(
        view: ImageView,
        iconRes: Int
    )
}