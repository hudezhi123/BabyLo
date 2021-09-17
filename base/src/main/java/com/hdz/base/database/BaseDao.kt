package com.hdz.base.database

import androidx.room.*

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(element: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list:MutableList<T>)

    @Delete
    fun delete(element:T)

    @Delete
    fun deleteList(list:MutableList<T>)

    @Update
    fun update(element: T)

}