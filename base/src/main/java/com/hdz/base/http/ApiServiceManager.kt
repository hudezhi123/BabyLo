package com.hdz.base.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceManager private constructor() {

    var api: Api

    init {
        val retrofit = Retrofit.Builder()
            .client(ApiClient.getOkClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Api.BASE_URL)
            .build()
        api = retrofit.create(Api::class.java)
    }

    companion object {
        class Single {
            companion object {
                val instances by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
                    ApiServiceManager()
                }
            }
        }

        fun getApi(): Api = Single.instances.api
    }

}