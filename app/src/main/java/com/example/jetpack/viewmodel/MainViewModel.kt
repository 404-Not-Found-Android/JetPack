package com.example.jetpack.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetpack.adapter.NewsBean
import com.example.jetpack.data.NewsDataSource
import com.example.jetpack.data.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Description :
 * CreateTime  : 2020/4/10
 */
class MainViewModel constructor(private val repository: NewsRepository) :
    ViewModel() {

    private val liveData = MutableLiveData<NewsBean>()
    private val type = ""

    fun loadNewsFormLivData(type: String): LiveData<PagingData<NewsBean>> {
        return data
    }

    val data = liveData.switchMap {
        repository.loadNews(type).cachedIn(viewModelScope).asLiveData()
    }

    fun loadNews(type: String): Flow<PagingData<NewsBean>> {
        return repository.loadNews(type).cachedIn(viewModelScope)
//        Pager(
//            config = PagingConfig(pageSize = 20, prefetchDistance = 1),
//            pagingSourceFactory = { NewsDataSource(type) }).flow.cachedIn(viewModelScope)
    }


}