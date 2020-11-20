package com.example.jetpack.repostiory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.PagingData
import com.example.jetpack.model.NewsModel
import com.example.jetpack.room.entity.DxNews
import kotlinx.coroutines.flow.Flow

/**
 * Description :
 * CreateTime  : 2020/8/19
 */
interface NewsRepository {
    fun loadNewsFromNet(type: String): Flow<PagingData<NewsModel>>
    fun loadNewsFromDb(type: String): Flow<PagingData<NewsModel>>
//    fun loadNewsFromDb(type: String): LiveData<PagedList<NewsModel>>
    fun loadNewsByLiveData(type: String):LiveData<NewsModel>
}