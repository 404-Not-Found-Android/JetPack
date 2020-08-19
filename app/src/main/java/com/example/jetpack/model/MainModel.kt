package com.example.jetpack.model

import androidx.lifecycle.MutableLiveData
import com.example.jetpack.net.ApiRetrofit
import com.example.jetpack.net.NetworkState
import com.example.jetpack.net.response.NewsResponse
import com.example.jetpack.base.model.BaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Description :
 * CreateTime  : 2020/6/27
 */
class MainModel(val type: String) : BaseModel() {
    val networkStatus = MutableLiveData<NetworkState>()
    val liveData = MutableLiveData<MutableList<NewsResponse.ResultBean.DataBean>>()

    suspend fun loadNews() {
        withContext(Dispatchers.IO) {
            val loadNews = ApiRetrofit.getInstance().loadNews(type)
            networkStatus.postValue(NetworkState.LOADING)
            val news: MutableList<NewsResponse.ResultBean.DataBean> = mutableListOf()
            news.addAll(loadNews.result.data)
            liveData.postValue(news)
        }
    }
}