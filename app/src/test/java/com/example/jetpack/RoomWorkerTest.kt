package com.example.jetpack

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import org.junit.Test

/**
 * Description :
 * CreateTime  : 2020/7/23
 */
class RoomWorkerTest {
    private lateinit var context: Context

    @Test
    fun testRoomDatabaseWorker(){
        context = ApplicationProvider.getApplicationContext()
        val request = OneTimeWorkRequestBuilder<RoomDataBaseWorker>().build()
        WorkManager.getInstance(context).enqueue(request)
    }
}