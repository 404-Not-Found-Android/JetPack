package com.example.jetpack.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetpack.model.NewsModel
import com.example.jetpack.data.NewsRepository
import com.example.jetpack.data.source.NewsDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Description :
 * CreateTime  : 2020/4/10
 */
class MainViewModel constructor(private val repository: NewsRepository) :
    ViewModel() {

    private val liveData = MutableLiveData<NewsModel>()
    private val type = ""

    fun loadNewsFormLivData(type: String): LiveData<PagingData<NewsModel>> {
        return data
    }

    val data = liveData.switchMap {
        repository.loadNews(type).cachedIn(viewModelScope).asLiveData()
    }

    fun loadNews(type: String): Flow<PagingData<NewsModel>> {
        return repository.loadNews(type).cachedIn(viewModelScope)
//        Pager(
//            config = PagingConfig(pageSize = 20, prefetchDistance = 1),
//            pagingSourceFactory = { NewsDataSource(type) }).flow.cachedIn(viewModelScope)
    }


}