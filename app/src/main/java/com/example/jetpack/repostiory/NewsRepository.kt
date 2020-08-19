package com.example.jetpack.repostiory

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.jetpack.adapter.NewsBean
import kotlinx.coroutines.flow.Flow

/**
 * Description :
 * CreateTime  : 2020/8/19
 */
interface NewsRepository {
    suspend fun loadNewsFromNet(type: String): Flow<PagingData<NewsBean>>
}