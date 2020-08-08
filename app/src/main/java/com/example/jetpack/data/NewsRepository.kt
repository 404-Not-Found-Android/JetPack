package com.example.jetpack.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetpack.adapter.NewsBean
import kotlinx.coroutines.flow.Flow

/**
 * Description :
 * CreateTime  : 2020/6/22
 */
class NewsRepository {
    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }

    fun loadNews(type: String): Flow<PagingData<NewsBean>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { NewsDataSource(type) }).flow
    }
}