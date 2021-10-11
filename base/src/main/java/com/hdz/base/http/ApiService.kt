package com.hdz.base.http

import com.hdz.base.database.entity.Animal
import com.hdz.base.database.entity.Plant
import com.hdz.base.http.bean.ListBean

interface ApiService {

    companion object{
        val BASE_URL = "http://192.168.2.84:8080/BabyLo/"
    }
    

    suspend fun getAnimalList(): Data<ListBean<Animal>>

    suspend fun getPlantList(): Data<ListBean<Plant>>
}