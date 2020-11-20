package com.example.jetpack.ui.news

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetpack.model.NewsModel
import com.example.jetpack.repostiory.impl.NewsRepositoryImpl
import com.example.jetpack.mvvm.base.viewmodel.BaseViewModel

/**
 * Description :
 * CreateTime  : 2020/8/19
 */
class NewsViewModel @ViewModelInject constructor(
    private val repository: NewsRepositoryImpl,
    application: Application
) : BaseViewModel(application) {

    private val _newsTypeData = MutableLiveData<String>(null)
    private val newsTypeData: LiveData<String> = _newsTypeData

    fun loadNews(type: String) {
        _newsTypeData.value = type
    }

    //    suspend fun loadNews(type: String): Flow<PagingData<NewsModel>> {
//        return repository.loadNewsFromNet(type).cachedIn(viewModelScope)
//    }
    val newsDataFromNet: LiveData<PagingData<NewsModel>> =
        Transformations.switchMap(newsTypeData) { type ->
            repository.loadNewsFromNet(type).cachedIn(viewModelScope).asLiveData()
        }

    val newsDataFromDb: LiveData<PagingData<NewsModel>> =
        Transformations.switchMap(newsTypeData) { type ->
            repository.loadNewsFromDb(type).cachedIn(viewModelScope).asLiveData()
        }

    val data: LiveData<NewsModel> = Transformations.switchMap(newsTypeData) { type ->
        repository.loadNewsByLiveData(type)
    }

}