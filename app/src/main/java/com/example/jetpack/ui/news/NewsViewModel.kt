package com.example.jetpack.ui.news

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetpack.adapter.NewsBean
import com.example.jetpack.base.viewmodel.BaseViewModel
import com.example.jetpack.repostiory.impl.NewsRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Description :
 * CreateTime  : 2020/8/19
 */
class NewsViewModel @ViewModelInject constructor(
    private val repository: NewsRepositoryImpl,
    application: Application
) :
    BaseViewModel(application) {

    suspend fun loadNews(type: String): Flow<PagingData<NewsBean>> {
        return repository.loadNewsFromNet(type).cachedIn(viewModelScope)
    }
}