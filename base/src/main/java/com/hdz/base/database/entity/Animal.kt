package com.hdz.base.database.entity

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Animal")
data class Animal(

    @PrimaryKey(autoGenerate = true)
    var id:Int?,
    var name:String,
    var image:String,
    var desc:String
)