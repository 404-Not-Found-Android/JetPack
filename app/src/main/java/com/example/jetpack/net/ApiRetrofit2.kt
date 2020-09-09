package com.example.jetpack.net

import com.example.network.base.NetworkApi

/**
 * Description :
 * CreateTime  : 2020/9/9
 */
class ApiRetrofit2 : NetworkApi() {
    private object Holder {
        val instance = ApiRetrofit2()
    }

    companion object {
        fun getInstance() = Holder.instance
    }

    fun <T> getService(clazz: Class<T>): T {
        return getInstance().getRetrofit(clazz, AppConst.NET_URL).create(clazz)
    }

}