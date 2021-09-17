package com.hdz.base.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.hdz.base.database.BaseDao
import com.hdz.base.database.entity.Animal

@Dao
interface AnimalDao : BaseDao<Animal> {

    @Query("select * from Animal")
    fun getAllAnimals(): MutableList<Animal>

    @Query("select * from Animal where name = :animalName")
    fun getAnimal(animalName: String):Animal

}