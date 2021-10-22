package com.hdz.image.viewmodel

import android.view.View
import com.hdz.base.bean.ItemBean

interface OnItemClickListener<T : ItemBean> {
    fun onItemClick(view: View, bean: T)
}