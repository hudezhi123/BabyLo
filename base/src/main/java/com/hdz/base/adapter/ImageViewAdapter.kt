package com.hdz.base.adapter

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.hdz.base.R

object ImageViewAdapter {

    @BindingAdapter("android:src")
    fun setSrc(view: ImageView, bitmap: Bitmap?) {
        view.setImageBitmap(bitmap)
    }

    @BindingAdapter("android:src")
    fun setSrc(view: ImageView, resId: Int) {
        view.setImageResource(resId)
    }

    @BindingAdapter("imageUrl")
    fun setSrc(imageView: ImageView, url: String?) {
        Glide.with(imageView.context).load(url)
            .placeholder(R.drawable.icon_default_logo)
            .into(imageView)
    }


}




