package com.example.mvvm.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Description :
 * CreateTime  : 2020/6/13
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    fun runOnIO(block: () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                block()
            }
        }
    }

    fun runOnUI(block: () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                block()
            }
        }
    }
}