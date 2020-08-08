package com.example.jetpack.utils

import com.example.jetpack.data.NewsRepository
import com.example.jetpack.viewmodel.NewsViewModelFactory

/**
 * Description :
 * CreateTime  : 2020/7/23
 */
object InjectorUtils {
    fun providerMainViewModelFactory(): NewsViewModelFactory {
        val repository = NewsRepository()
        return NewsViewModelFactory(repository)
    }
}