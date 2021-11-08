package com.hdz.base.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hdz.base.R
import com.hdz.base.util.L

class ImgPagerAdapter(context: Context?, imgList: ArrayList<String>) : PagerAdapter() {

    val options = RequestOptions.errorOf(R.drawable.icon_default_logo)

    val context: Context?
    val imgList: ArrayList<String>

    init {
        this.context = context
        this.imgList = imgList
    }

    override fun getCount(): Int = imgList.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imgView = ImageView(context)
        imgView.scaleType = ImageView.ScaleType.FIT_CENTER
        Glide.with(context!!).load(imgList.get(position)).apply(options).into(imgView)
        container.addView(imgView)
        return imgView
    }
}