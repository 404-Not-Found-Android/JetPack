package com.example.jetpack.di

import com.example.jetpack.room.database.RoomDaoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Description :
 * CreateTime  : 2020/8/20
 */
@Module
@InstallIn(ApplicationComponent::class)
object DataBaseModule {
    @Provides
    fun provideDatabase(): RoomDaoDataBase {
        return RoomDaoDataBase.getInstance()
    }
}