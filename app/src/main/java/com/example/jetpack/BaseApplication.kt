package com.example.jetpack

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

/**
 * Description :
 * CreateTime  : 2020/8/18
 */
@HiltAndroidApp
class BaseApplication : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}