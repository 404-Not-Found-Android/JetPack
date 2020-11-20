package com.example.jetpack.mvvm.base.model

/**
 * Description :
 * CreateTime  : 2020/6/8
 */
abstract class BaseModel {
    var retry: (suspend () -> Any)? = null
    suspend fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.invoke()
    }
}