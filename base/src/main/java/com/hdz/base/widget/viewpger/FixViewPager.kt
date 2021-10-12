package com.hdz.base.widget.viewpger

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * 设置ViewPager是否可以滑动换页，默认不可以滑动换页
 */
class FixViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    var mScrollable = false

    fun setScrollable(scrollable: Boolean) {
        mScrollable = scrollable
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return mScrollable && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return mScrollable && super.onInterceptTouchEvent(ev)
    }

}