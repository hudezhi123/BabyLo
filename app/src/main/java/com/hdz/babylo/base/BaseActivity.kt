package com.hdz.babylo.base

import android.os.Bundle
import android.view.DisplayCutout
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    /**
     * 初始化状态
     */
    abstract fun init()

    /**
     * 加载数据
     */
    abstract fun loadData()

    /**
     * 获取 layoutId
     */
    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()
        loadData()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

    }


    fun setFullScreen(expandFringe:Boolean,hideStatus: Boolean, hideNav: Boolean) {

    }
}