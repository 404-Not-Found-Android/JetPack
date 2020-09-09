package com.example.jetpack.repostiory.impl

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.jetpack.model.NewsModel
import com.example.jetpack.data.source.NewsDataSource
import com.example.jetpack.repostiory.NewsRepository
import com.example.jetpack.room.database.RoomDaoDataBase
import com.example.jetpack.room.entity.DxNews
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Description :
 * CreateTime  : 2020/8/19
 */
class NewsRepositoryImpl @Inject constructor(private val dataBase: RoomDaoDataBase) :
    NewsRepository {
    override fun loadNewsFromNet(type: String): Flow<PagingData<NewsModel>> {
        return Pager(config = PagingConfig(enablePlaceholders = false, pageSize = 30),
            pagingSourceFactory = { NewsDataSource(type, dataBase) }).flow
    }

    override fun loadNewsFromDb(type: String): Flow<PagingData<NewsModel>> {
        return Pager(config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { dataBase.newsDao().queryAllNewsPaging3(type) })
            .flow.map { pagingData ->
                pagingData.map { dxNews ->
                    convertDxNewsToNewsModel(dxNews)
                }
            }
    }

//    override fun loadNewsFromDb(type: String): LiveData<PagedList<NewsModel>> {
//        return dataBase.newsDao().queryAllNews(type).map {
//            return@map convertDxNewsToNewsModel(it)
//        }.toLiveData(30)
//    }

    private fun convertDxNewsToNewsModel(dxNews: DxNews): NewsModel {
        val newsModel = NewsModel()
        newsModel.uniquekey = dxNews.uuid
        newsModel.author_name = dxNews.authorName
        newsModel.title = dxNews.title
        newsModel.category = dxNews.category
        newsModel.date = dxNews.date
        newsModel.thumbnail_pic_s = dxNews.thumbnail_pic_s
        newsModel.thumbnail_pic_s02 = dxNews.thumbnail_pic_s02
        newsModel.thumbnail_pic_s03 = dxNews.thumbnail_pic_s03
        newsModel.url = dxNews.url
        return newsModel
    }
}