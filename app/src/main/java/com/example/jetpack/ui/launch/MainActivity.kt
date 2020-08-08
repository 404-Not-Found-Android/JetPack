package com.example.jetpack.ui.launch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpack.R
import com.example.jetpack.adapter.NewsAdapter
import com.example.jetpack.data.NewsRepository
import com.example.jetpack.databinding.ActivityMainBinding
import com.example.jetpack.viewmodel.MainViewModel
import com.example.jetpack.viewmodel.NewsViewModelFactory
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val type = "新闻"
    private val adapter = NewsAdapter()

    private val viewModel by lazy {
        ViewModelProvider(this, NewsViewModelFactory(NewsRepository()))
            .get(MainViewModel::class.java)
    }

    @ExperimentalPagingApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        dataBinding.smartRefresh.setOnRefreshListener(onRefreshListener)
        dataBinding.smartRefresh.setEnableLoadMore(false)
        dataBinding.recyclerView.adapter = adapter
        dataBinding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter.addDataRefreshListener {
            dataBinding.smartRefresh.finishRefresh()
        }
        adapter.addLoadStateListener { loadState ->
            when (loadState.refresh) {
                is LoadState.Loading -> {

                }
                is LoadState.Error -> {
                    dataBinding.smartRefresh.finishRefresh()
                    dataBinding.smartRefresh.finishLoadMore()
                }
                is LoadState.NotLoading -> {
                    dataBinding.smartRefresh.finishRefresh()
                    dataBinding.smartRefresh.finishLoadMore()
                }
            }
        }
        loadNews(type)
    }

    private val onRefreshListener = OnRefreshListener {
        adapter.refresh()
    }

    private fun loadNews(type: String) {
//        lifecycleScope.launch {
//            viewModel.loadNews(type).collect {
//                adapter.submitData(it)
//            }
//        }
    }


}
