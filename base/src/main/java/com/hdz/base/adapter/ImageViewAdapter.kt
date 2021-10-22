package com.hdz.base.adapter

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.hdz.base.R
import com.hdz.base.util.L


open class ImageViewAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter("android:src")
        fun setSrc(view: ImageView, bitmap: Bitmap?) {
            view.setImageBitmap(bitmap)
        }

        @JvmStatic
        @BindingAdapter("android:src")
        fun setSrc(view: ImageView, resId: Int) {
            view.setImageResource(resId)
        }

        @JvmStatic
        @BindingAdapter("imgUrl")
        fun setSrc(imageView: ImageView, url: String?) {
            L.i("Animal__", url ?: "")
            Glide.with(imageView.context).load(url)
                .placeholder(R.drawable.icon_default_logo)
                .into(imageView)
        }
    }

}




