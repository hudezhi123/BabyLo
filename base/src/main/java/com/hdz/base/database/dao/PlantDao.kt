package com.hdz.base.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.hdz.base.database.BaseDao

@Dao
interface PlantDao : BaseDao<Plant> {

    @Query("select * from Plant")
    fun getAllPlants(): MutableList<Plant>

    @Query("select * from Plant where name = :plantName")
    fun getPlant(plantName:String):Plant
}