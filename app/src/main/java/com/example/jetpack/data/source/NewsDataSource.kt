package com.example.jetpack.data.source

import androidx.paging.PagingSource
import com.example.jetpack.model.NewsModel
import com.example.jetpack.net.ApiRetrofit
import com.example.jetpack.net.response.NewsResponse
import com.example.jetpack.room.database.RoomDaoDataBase
import com.example.jetpack.room.entity.DxNews

/**
 * Description :
 * CreateTime  : 2020/6/16
 */
class NewsDataSource constructor(private val type: String, private val daoDataBase: RoomDaoDataBase) : PagingSource<Int, NewsModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsModel> {
        return try {
            val newsModels: MutableList<NewsModel> = mutableListOf()
            val loadNews = ApiRetrofit.getInstance().loadNews(type)
            newsModels.addAll(getNewsBean(loadNews.result.data))
            saveNews(newsModels)
            val queryAllNews = daoDataBase.newsDao().queryAllNews(type)
            queryAllNews.forEach { dxNews ->
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
                newsModels.add(newsModel)
            }
            LoadResult.Page(data = newsModels, prevKey = null, nextKey = null)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

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

    private fun getNewsBean(data: List<NewsResponse.ResultBean.DataBean>): MutableList<NewsModel> {
        val newsBeanList = mutableListOf<NewsModel>()
        if (data.isNotEmpty()) {
            data.forEach {
                val newsBean = NewsModel()
                newsBean.author_name = it.author_name
                newsBean.category = it.category
                newsBean.date = it.date
                newsBean.thumbnail_pic_s = it.thumbnail_pic_s
                newsBean.thumbnail_pic_s02 = it.thumbnail_pic_s02
                newsBean.thumbnail_pic_s03 = it.thumbnail_pic_s03
                newsBean.title = it.title
                newsBean.url = it.url
                newsBean.uniquekey = it.uniquekey
                newsBeanList.add(newsBean)
            }
        }
        return newsBeanList
    }

    private fun saveNews(newsBean: MutableList<NewsModel>) {
        val newsList = mutableListOf<DxNews>()
        newsBean.forEach {
            val dxNews = DxNews()
            dxNews.uuid = it.uniquekey.toString()
            dxNews.type = type
            dxNews.title = it.title
            dxNews.authorName = it.author_name
            dxNews.category = it.category
            dxNews.date = it.date
            dxNews.url = it.url
            dxNews.thumbnail_pic_s = it.thumbnail_pic_s
            dxNews.thumbnail_pic_s02 = it.thumbnail_pic_s02
            dxNews.thumbnail_pic_s03 = it.thumbnail_pic_s03
            newsList.add(dxNews)
        }
        daoDataBase.newsDao().insertNews(newsList)
    }


}