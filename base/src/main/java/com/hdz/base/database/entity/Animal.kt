package com.hdz.base.database.entity

import androidx.room.PrimaryKey

data class Animal(

    @PrimaryKey(autoGenerate = true)
    var id:Int?,
    var name:String,
    var image:Int,
    var desc:String
)