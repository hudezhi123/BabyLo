package com.hdz.image.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.hdz.image.R
import com.hdz.image.adapter.AnimalAdapter

class ImageActivity : AppCompatActivity() {

    lateinit var mAdapter: AnimalAdapter
    private val binding by lazy {
//        DataBindingUtil.setContentView(this, R.layout.activity_img)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_img)
        val manager = GridLayoutManager(this, 4)

    }
}