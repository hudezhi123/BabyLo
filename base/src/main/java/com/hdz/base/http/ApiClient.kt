package com.hdz.base.http

import android.os.Build
import com.hdz.base.http.interceptor.LogInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {
        private const val DEFAULT_CONNECT_TIMEOUT: Long = 5
        private const val DEFAULT_WRITE_TIMEOUT: Long = 10
        private const val DEFAULT_READ_TIMEOUT: Long = 10
        fun getOkClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            builder.writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
            builder.readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
            builder.addInterceptor(LogInterceptor())
            return builder.build()
        }
    }
}