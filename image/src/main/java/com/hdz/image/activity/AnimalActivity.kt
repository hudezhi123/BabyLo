package com.hdz.image.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.hdz.base.base.BaseActivity
import com.hdz.base.util.Constants
import com.hdz.base.util.L
import com.hdz.image.R
import com.hdz.image.adapter.AnimalAdapter
import com.hdz.image.viewmodel.AnimalViewModel
import kotlinx.android.synthetic.main.activity_animal.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AnimalActivity : BaseActivity() {

    private lateinit var viewModel: AnimalViewModel
    private lateinit var mAdapter: AnimalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal)
        GlobalScope.launch {
            delay(1000L)
        }
        mAdapter = AnimalAdapter(this)
        val manager = GridLayoutManager(this, 3)
        recycler_main_animal.layoutManager = manager
        recycler_main_animal.adapter = mAdapter
        //获取viewModel对象
        viewModel = ViewModelProvider(this).get(AnimalViewModel::class.java)
        //对animalList进行观察
        viewModel.animalList.observe(this, Observer {
            if (it.code == Constants.CODE_SUCCESS) {
                mAdapter.setData(it.data)
            } else {

            }
        })
        viewModel.animalList()
    }
}