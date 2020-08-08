package com.example.jetpack.net

import com.example.jetpack.net.response.NewsResponse
import retrofit2.Call
import retrofit2.http.*

/**
 * Description :
 * CreateTime  : 2020/6/12
 */
interface ApiService {
    //    @FormUrlEncoded
    @GET("/toutiao/index?")
    suspend fun loadNews(
        @Query("type") type: String,
        @Query("key") key: String = AppConst.API_KEY
    ): NewsResponse
}