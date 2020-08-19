package com.example.jetpack.data

import androidx.paging.PagingSource
import com.example.jetpack.model.NewsModel
import com.example.jetpack.net.ApiRetrofit
import com.example.jetpack.net.response.NewsResponse

/**
 * Description :
 * CreateTime  : 2020/6/16
 */
class NewsDataSource(private val type: String) :
    PagingSource<Int, NewsModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsModel> {
        return try {
            val loadNews = ApiRetrofit.getInstance().loadNews(type)
            val newsBean = getNewsBean(loadNews.result.data)
            LoadResult.Page(
                data = newsBean,
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
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


}