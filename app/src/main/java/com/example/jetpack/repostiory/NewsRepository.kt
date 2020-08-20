package com.example.jetpack.repostiory

import androidx.paging.PagingData
import com.example.jetpack.model.NewsModel
import kotlinx.coroutines.flow.Flow

/**
 * Description :
 * CreateTime  : 2020/8/19
 */
interface NewsRepository {
    suspend fun loadNewsFromNet(type: String): Flow<PagingData<NewsModel>>
}