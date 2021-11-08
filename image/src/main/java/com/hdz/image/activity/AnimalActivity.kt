package com.hdz.image.activity

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.hdz.base.base.BaseActivity
import com.hdz.base.bean.Animal
import com.hdz.base.callback.OnFinishListener
import com.hdz.base.util.AnimDo
import com.hdz.base.util.Constants
import com.hdz.base.util.JumpUtil
import com.hdz.image.R
import com.hdz.image.adapter.AnimalAdapter
import com.hdz.image.util.VoiceDo
import com.hdz.image.viewmodel.AnimalViewModel
import com.hdz.image.viewmodel.OnItemClickListener
import kotlinx.android.synthetic.main.activity_animal.*

class AnimalActivity : BaseActivity() {

    private lateinit var viewModel: AnimalViewModel
    private lateinit var mAdapter: AnimalAdapter
    private lateinit var voiceDo: VoiceDo


    override fun getLayoutId(): Int = R.layout.activity_animal

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun init() {
        voiceDo = VoiceDo(this)
        mAdapter = AnimalAdapter(this, object : OnItemClickListener<Animal> {
            override fun onItemClick(view: View, bean: Animal) {
                voiceDo.play()
                AnimDo.shake(view, 0.8f, 1.1f, 40f, 200, object : OnFinishListener {
                    override fun onFinish() {
                        JumpUtil.jumpWithData(
                            this@AnimalActivity,
                            AnimalDetailActivity::class.java,
                            bean
                        )
                    }
                })

            }
        })
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
                showToast("获取数据失败！")
            }
        })
        viewModel.animalList()
    }
}