package com.hdz.base.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Animal(
    var name: String,
    var enName: String,
    var headUrl: String,
    var imgList: MutableList<String>,
    var hasVoice: Int,
    var hasVideo: Int
) : ItemBean(), Parcelable