package com.example.jetpack.base.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.example.jetpack.net.NetworkState
import com.example.jetpack.net.Status
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

/**
 * Description :
 * CreateTime  : 2020/6/9
 */
abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity(), Observer<NetworkState> {
    private lateinit var viewDataBinding: V
    private lateinit var loadService: LoadService<*>
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun onReload()

    fun setLoadSir(view: View) {
        loadService = LoadSir.getDefault().register(view) { onReload() }
    }

    override fun onChanged(networkState: NetworkState) {
        when (networkState.status) {
            Status.LOADING -> {
            }
            Status.SUCCESS -> loadService.showSuccess()
            Status.FAILED -> Toast.makeText(this, networkState.msg, Toast.LENGTH_SHORT).show()
            Status.EMPTY -> Toast.makeText(this, networkState.msg, Toast.LENGTH_SHORT).show()
            Status.SHOW_CONTENT -> loadService.showSuccess()
            Status.REFRESH_ERROR -> Toast.makeText(this, networkState.msg, Toast.LENGTH_SHORT)
                .show()
            Status.LOAD_MORE_ERROR -> Toast.makeText(this, networkState.msg, Toast.LENGTH_SHORT)
                .show()
            Status.NO_MORE -> return
        }
    }
}