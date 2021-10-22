package com.hdz.base.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceManager private constructor() {

    var api: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .client(ApiClient.getOkClient())
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(ApiService::class.java)
    }

    companion object {
        class Single {
            companion object {
                val instances by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
                    ApiServiceManager()
                }
            }
        }

        fun getApi(): ApiService = Single.instances.api
    }

}