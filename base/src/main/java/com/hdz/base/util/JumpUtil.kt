package com.hdz.base.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View

import java.io.Serializable

class JumpUtil {
    companion object {

        const val KEY_S = "Key"
        const val KEY_DATA = "Data"

        fun jump(activity: Activity, cls: Class<*>) {
            jump(activity, cls, false)
        }

        fun jump(activity: Activity, cls: Class<*>, isFinish: Boolean = false) {
            val intent = Intent(activity, cls)
            activity.startActivity(intent)
            if (isFinish) {
                activity.finish()
            }
        }

        fun jumpWithString(activity: Activity, cls: Class<*>, message: String) {
            val intent = Intent(activity, cls)
            intent.putExtra(KEY_S, message)
            activity.startActivity(intent)
        }


        fun <T : Parcelable> jumpWithData(
            activity: Activity,
            cls: Class<*>,
            data: T,
            isFinish: Boolean = false
        ) {
            val intent = Intent(activity, cls)
            val bundle = Bundle()
            bundle.putParcelable(KEY_DATA, data)
            intent.putExtras(bundle)
            activity.startActivity(intent)
            if (isFinish) activity.finish()
        }


        fun <U : View, T : Parcelable> jumpWithAnim(
            activity: Activity,
            cls: Class<*>,
            view: U,
            data: T
        ) {

        }

        fun <U : View, T : Serializable> jumpWithAnim(
            activity: Activity,
            cls: Class<*>,
            view: U,
            data: T
        ) {

        }
    }
}