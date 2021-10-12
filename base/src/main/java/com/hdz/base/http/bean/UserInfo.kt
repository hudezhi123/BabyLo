package com.hdz.base.http.bean

data class UserInfo(
    val userId: String,  //用户id
    val name: String,   //用户名称
    val sex: Int,   //性别  1：男     0：女
    val birthday: String,   // 生日
    val cell: String   // 账号：邮箱或者手机号
)