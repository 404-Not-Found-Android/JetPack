package com.example.jetpack.di

import com.example.jetpack.repostiory.NewsRepository
import com.example.jetpack.repostiory.impl.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * Description :
 * CreateTime  : 2020/8/19
 */
@Module
@InstallIn(ActivityComponent::class)
abstract class NewsModule {
    @ActivityScoped
    @Binds
    abstract fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}