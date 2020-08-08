package com.example.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpack.R
import com.example.jetpack.adapter.NewsAdapter
import com.example.jetpack.data.NewsRepository
import com.example.jetpack.databinding.FragmentNewsBinding
import com.example.jetpack.viewmodel.MainViewModel
import com.example.jetpack.viewmodel.NewsViewModelFactory
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class NewsFragment : Fragment() {
    companion object {
        fun newInstance(type: String): NewsFragment {
            val args = Bundle()
            args.putString("type", type)
            val fragment = NewsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var loadNewsJob: Job? = null
    private val adapter = NewsAdapter()
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            NewsViewModelFactory(NewsRepository())
        ).get(MainViewModel::class.java)
    }

    @ExperimentalPagingApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNewsBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.smartRefresh.setEnableLoadMore(false)
        binding.smartRefresh.setOnRefreshListener {
            adapter.refresh()
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter.addDataRefreshListener {
            binding.smartRefresh.finishRefresh()
        }
        adapter.addLoadStateListener { loadState ->
            when (loadState.refresh) {
                is LoadState.Loading -> {

                }
                is LoadState.Error -> {
                    binding.smartRefresh.finishRefresh()
                    binding.smartRefresh.finishLoadMore()
                }
                is LoadState.NotLoading -> {
                    binding.smartRefresh.finishRefresh()
                    binding.smartRefresh.finishLoadMore()
                }
            }
        }
        val type = arguments?.getString("type")
        type?.let { loadData(it) }
        return binding.root
    }

    private fun loadData(type: String) {
        loadNewsJob?.cancel()
        loadNewsJob = lifecycleScope.launch {
            viewModel.loadNews(type).collect {
                adapter.submitData(it)
            }
        }

//        viewModel.loadNewsFormLivData(type).observe(viewLifecycleOwner, Observer { pagingData ->
//            lifecycleScope.launch {
//                adapter.submitData(lifecycle, pagingData)
//            }
//        })
    }

    override fun onDestroy() {
        super.onDestroy()
        loadNewsJob?.cancel()
    }
}