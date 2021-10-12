package com.hdz.base.http.bean

data class ApkInfoInfo(
    val appId: String,   //应用id
    val appName: String, //应用名称
    var appType: Int,  //应用类型
    val versionName: String,  //版本名称
    val versionCode: Int,  //版本号
    val apkUrl: String,  //apk路径，下载更新的地址
    val forceStatus: Int,  //是否强制下载
    val tips: String   //更新内容
)