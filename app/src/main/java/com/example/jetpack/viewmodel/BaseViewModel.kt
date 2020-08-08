package com.example.jetpack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Description :
 * CreateTime  : 2020/6/13
 */
abstract class BaseViewModel<M : BaseModel>(application: Application) :
    AndroidViewModel(application) {
    val model: M by lazy {
        initModel()
    }

    abstract fun initModel(): M

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
                block
            }
        }
    }
}