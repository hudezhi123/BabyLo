package com.hdz.base.http.interceptor


import android.util.Log
import com.hdz.base.util.L
import okhttp3.Interceptor
import okhttp3.Response

class LogInterceptor : Interceptor {

    companion object{
       val TAG = "Request__Method"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.i("Animal__",request.url().toString());
        val methodName = request.method()
        val response = chain.proceed(request)
        return response
    }
}