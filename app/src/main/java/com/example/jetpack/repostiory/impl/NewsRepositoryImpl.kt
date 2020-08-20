package com.example.jetpack.repostiory.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetpack.model.NewsModel
import com.example.jetpack.data.NewsDataSource
import com.example.jetpack.repostiory.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Description :
 * CreateTime  : 2020/8/19
 */
class NewsRepositoryImpl @Inject constructor(): NewsRepository {
    override suspend fun loadNewsFromNet(type: String): Flow<PagingData<NewsModel>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 30),
            pagingSourceFactory = { NewsDataSource(type) }).flow
    }
}