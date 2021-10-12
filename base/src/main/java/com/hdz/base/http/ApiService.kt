package com.hdz.base.http

import com.hdz.base.database.entity.Animal
import com.hdz.base.database.entity.Plant
import com.hdz.base.http.bean.ListBean
import com.hdz.base.http.bean.LoginInfo
import retrofit2.http.POST

interface ApiService {




    @POST(Api.LOGIN)
    suspend fun login():BaseJson<LoginInfo>

    /**
     * 获取动物列表
     */
    @POST(Api.ANIMAL_LIST)
    suspend fun getAnimalList(): BaseJson<ListBean<Animal>>

    /**
     * 获取植物列表
     */
    @POST(Api.PLANT_LIST)
    suspend fun getPlantList(): BaseJson<ListBean<Plant>>
}