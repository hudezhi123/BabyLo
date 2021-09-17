package com.hdz.base.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Plant")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    var name: String,

    var image: Int,

    var desc: String
)