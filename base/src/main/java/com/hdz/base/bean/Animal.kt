package com.hdz.base.bean

data class Animal(
    var name: String,
    var enName: String,
    var headUrl: String,
    var imgList: MutableList<String>
) : ItemBean()