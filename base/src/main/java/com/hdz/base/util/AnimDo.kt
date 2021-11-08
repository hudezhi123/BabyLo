package com.hdz.base.util

import android.animation.Animator
import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import com.hdz.base.callback.OnFinishListener

class AnimDo {

    companion object {

        /**
         * 抖动动画
         */
        fun shake(
            view: View,
            scaleSmall: Float,
            scaleLarge: Float,
            shakeDegree: Float,
            duration: Long,
            finish:OnFinishListener
        ) {
            //先变大后变小
            val scaleXValueHolder = PropertyValuesHolder.ofKeyframe(
                View.SCALE_X,
                Keyframe.ofFloat(0f, 1.0f),
                Keyframe.ofFloat(0.25f, scaleSmall),
                Keyframe.ofFloat(0.5f, scaleLarge),
                Keyframe.ofFloat(0.75f, scaleLarge),
                Keyframe.ofFloat(1.0f, 1.0f)
            )
            val scaleYValueHolder = PropertyValuesHolder.ofKeyframe(
                View.SCALE_Y,
                Keyframe.ofFloat(0f, 1.0f),
                Keyframe.ofFloat(0.25f, scaleSmall),
                Keyframe.ofFloat(0.5f, scaleLarge),
                Keyframe.ofFloat(0.75f, scaleLarge),
                Keyframe.ofFloat(1.0f, 1.0f)
            )
            //先往左后往右
            val rotateValueHolder = PropertyValuesHolder.ofKeyframe(
                View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(0.1f, -shakeDegree),
                Keyframe.ofFloat(0.2f, shakeDegree),
                Keyframe.ofFloat(0.3f, -shakeDegree),
                Keyframe.ofFloat(0.4f, shakeDegree),
                Keyframe.ofFloat(0.5f, -shakeDegree),
                Keyframe.ofFloat(0.6f, shakeDegree),
                Keyframe.ofFloat(0.7f, -shakeDegree),
                Keyframe.ofFloat(0.8f, shakeDegree),
                Keyframe.ofFloat(0.9f, -shakeDegree),
                Keyframe.ofFloat(1.0f, 0f)
            )
            val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                view,
                scaleXValueHolder,
                scaleYValueHolder,
                rotateValueHolder
            )
            objectAnimator.duration = duration
            objectAnimator.start()
            objectAnimator.addListener(object :Animator.AnimatorListener{
                override fun onAnimationStart(animation: Animator?) {

                }

                override fun onAnimationEnd(animation: Animator?) {
                    finish.onFinish()
                }

                override fun onAnimationCancel(animation: Animator?) {

                }

                override fun onAnimationRepeat(animation: Animator?) {

                }
            })
        }

    }
}