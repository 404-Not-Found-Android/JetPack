package com.example.jetpack.room.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpack.room.entity.DxNews

/**
 * Description :
 * CreateTime  : 2020/8/20
 */
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: MutableList<DxNews>)

    @Query("SELECT * FROM T_News WHERE type=:newsType")
    fun queryAllNewsPaging3(newsType: String): PagingSource<Int, DxNews>

    @Query("SELECT * FROM T_News WHERE type = :newsType")
    fun queryAllNews(newsType: String): MutableList<DxNews>

    @Query("select * from T_News where type = :newsType")
    fun queryAllNewsByLiveData(newsType: String): LiveData<DxNews>
}