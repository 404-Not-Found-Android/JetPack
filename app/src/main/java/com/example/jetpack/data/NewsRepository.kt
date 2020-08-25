package com.example.jetpack.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetpack.data.source.NewsDataSource
import com.example.jetpack.model.NewsModel
import com.example.jetpack.room.database.RoomDaoDataBase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Description :
 * CreateTime  : 2020/6/22
 */
class NewsRepository @Inject constructor(private val dataBase: RoomDaoDataBase) {
    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }

    fun loadNews(type: String): Flow<PagingData<NewsModel>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                NewsDataSource(
                    type,
                    dataBase
                )
            }).flow
    }
}