package com.example.jetpack.network.base

import com.example.network.interceptor.LogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Description :
 * CreateTime  : 2020/9/9
 */
abstract class NetworkApi {
    private lateinit var okHttpClient: OkHttpClient

    fun getRetrofit(baseUsl: String): Retrofit {
        val builder = Retrofit.Builder()
        builder.baseUrl(baseUsl)
        builder.client(getOkHttpClient())
        builder.addConverterFactory(GsonConverterFactory.create())
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        return builder.build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(LogInterceptor())
        okHttpClient = builder.build()
        return okHttpClient
    }
}