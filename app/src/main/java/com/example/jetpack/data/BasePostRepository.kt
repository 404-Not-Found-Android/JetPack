package com.example.jetpack.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

/**
 * Description :
 * CreateTime  : 2020/6/22
 */
interface BasePostRepository<R, T : Any> {
    fun postData(data: R, pageSize: Int): Flow<PagingData<T>>
}