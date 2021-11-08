package com.hdz.image.activity

import android.content.Intent
import com.hdz.base.base.BaseActivity
import com.hdz.base.bean.Animal
import com.hdz.base.fragment.ViewPagerFragment
import com.hdz.base.util.Constants
import com.hdz.base.util.JumpUtil
import com.hdz.base.util.L
import com.hdz.image.R

class AnimalDetailActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_animal_detail
    var animal: Animal? = null

    override fun init() {
        animal = intent.extras?.getParcelable(JumpUtil.KEY_DATA)
        val manager = supportFragmentManager
        manager.beginTransaction().add(
            R.id.frame_container_animal_detail,
            ViewPagerFragment.newInstance(
                Constants.TYPE_ANIMAL,
                animal?.imgList as ArrayList<String>
            )
        ).commit()

    }
}