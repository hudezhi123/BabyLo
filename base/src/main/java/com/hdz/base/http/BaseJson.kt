package com.hdz.base.http

data class BaseJson<T>(var code: Int, var data: T, var msg: String, var version: String)



