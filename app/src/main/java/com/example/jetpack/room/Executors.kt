package com.example.jetpack.room

import java.util.concurrent.Executors

/**
 * Description :
 * CreateTime  : 2020/7/22
 */
class Executors {
    private val executorService = Executors.newSingleThreadExecutor()
    fun ioThread(f: () -> Unit) {
        executorService.execute(f)
    }
}