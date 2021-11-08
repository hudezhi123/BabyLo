package com.hdz.image.util

import android.content.Context
import android.media.SoundPool
import android.os.Build
import androidx.annotation.RequiresApi
import com.hdz.base.util.L
import com.hdz.image.R

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class VoiceDo(context: Context) {
    val context: Context
    val mSoundPool: SoundPool
    val mSoundIdMap = HashMap<String, Int>()
    val mSoundId: Int

    init {
        this.context = context
//        mSoundPool = SoundPool(2, AudioManager.STREAM_SYSTEM, 5)
//        mSoundIdMap.put("cat", mSoundPool.load(context, R.raw.cat, 1))
//        mSoundIdMap.put("dog", mSoundPool.load(context, R.raw.dog, 1))
//        mSoundIdMap.put("duck", mSoundPool.load(context, R.raw.duck, 1))
//        mSoundIdMap.put("frog", mSoundPool.load(context, R.raw.frog, 1))
//        mSoundIdMap.put("key", mSoundPool.load(context, R.raw.key, 1))
//        mSoundIdMap.put("lion", mSoundPool.load(context, R.raw.lion, 1))
//        mSoundIdMap.put("panda", mSoundPool.load(context, R.raw.panda, 1))
//        mSoundIdMap.put("peacock", mSoundPool.load(context, R.raw.peacock, 1))
//        mSoundIdMap.put("tiger", mSoundPool.load(context, R.raw.tiger, 1))

        val spb = SoundPool.Builder()
        spb.setMaxStreams(10)
        mSoundPool = spb.build() //创建SoundPool对象
        mSoundId = mSoundPool.load(context, R.raw.key, 1)
    }

    fun play() {
        mSoundPool.play(mSoundId, 1f, 1f, 0, 0, 1f)
    }

}