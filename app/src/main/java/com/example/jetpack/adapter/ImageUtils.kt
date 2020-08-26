package com.example.jetpack.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.jetpack.R

/**
 * Description :
 * CreateTime  : 2020/7/27
 */
object ImageUtils {
    @BindingAdapter("imageFromUrl")
    @JvmStatic
    fun loadImageFromUrl(view: ImageView, imageUrl: String?) {
        if (!imageUrl.isNullOrEmpty()) {
            view.visibility = View.VISIBLE
            Glide.with(view.context)
                .load(imageUrl)
                .placeholder(R.drawable.default_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.image_view_error)
                .into(view)
        } else {
            view.visibility = View.GONE
        }
    }
}


