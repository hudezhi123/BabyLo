package com.hdz.base.util

import android.util.Log
import com.hdz.base.util.Constants.Companion.DEBUG

class L {

   companion object{
       fun i(tag: String = "BabyLo", tips: String) {
           if (DEBUG) {
               Log.i(tag, tips)
           }
       }
   }

}