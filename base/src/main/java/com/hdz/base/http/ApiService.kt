package com.hdz.base.http

import com.hdz.base.database.entity.Animal
import com.hdz.base.database.entity.Plant
import com.hdz.base.http.bean.ListBean

interface ApiService {

    suspend fun getAnimalList(): Data<ListBean<Animal>>

    suspend fun getPlantList(): Data<ListBean<Plant>>
}