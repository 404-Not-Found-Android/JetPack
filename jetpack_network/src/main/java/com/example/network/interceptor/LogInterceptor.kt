package com.example.network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.nio.charset.Charset

/**
 * Description :
 * CreateTime  : 2020/9/9
 */
class LogInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        val request = chain.request()
        val t1 = System.nanoTime()//请求发起的时间
        Log.d(
            "TAG", "(BaseApiRetrofit.kt:53)------>" + String.format(
                "发送请求: %s on %s%n%s%s",
                request.url, chain.connection(), request.headers, request.body.toString()
            )
        )
        val response = chain.proceed(request)
        val t2 = System.nanoTime()//收到响应的时间
        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        Log.d(
            "TAG", "(BaseApiRetrofit.kt:64)------>" + String.format(
                "响应码:[%d]接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
                response.code,
                response.request.url,
                getResponseString(response),
                (t2 - t1) / 1e6,
                response.headers
            )
        )
        return response
    }

    private fun getResponseString(response: Response?): String? {
        return try {
            val responseBody = response?.body
            val source = responseBody?.source()
            source?.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
            val buffer = source?.buffer
            buffer?.clone()?.readString(Charset.forName("UTF-8"))
        } catch (t: Throwable) {
            t.printStackTrace()
            null
        }
    }
}