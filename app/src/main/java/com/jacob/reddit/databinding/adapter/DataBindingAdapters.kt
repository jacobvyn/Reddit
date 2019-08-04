package com.jacob.reddit.databinding.adapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Transformation

/**
 * DataBinding instance adapters for layouts
 */
interface DataBindingAdapters {

    /**
     * Displaying image by url with optional circle transformation
     */
    @BindingAdapter(value = ["imageUrl", "placeholder", "error", "circle"], requireAll = false)
    fun loadImage(
        view: ImageView,
        url: String?,
        placeholder: Drawable?,
        error: Drawable?,
        circle: Boolean
    )


    /**
     * Displaying image by url with optional custom transformation
     */
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
}