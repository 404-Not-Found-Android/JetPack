package com.example.jetpack.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import com.example.jetpack.R
import com.example.jetpack.adapter.NewsAdapter
import com.example.jetpack.base.fragment.BaseFragment
import com.example.jetpack.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>() {
    companion object {
        fun newInstance(type: String): NewsFragment {
            val args = Bundle()
            args.putString("type", type)
            val fragment = NewsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var adapter: NewsAdapter? = null

    private val viewModel by viewModels<NewsViewModel>()


    @ExperimentalPagingApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = FragmentNewsBinding.inflate(inflater, container, false)
        adapter = NewsAdapter(requireContext())
        dataBinding.smartRefresh.setEnableLoadMore(false)
        dataBinding.smartRefresh.setOnRefreshListener {
            adapter?.refresh()
        }
        dataBinding.recyclerView.adapter = adapter
        adapter?.addDataRefreshListener {
            dataBinding.smartRefresh.finishRefresh()
        }
        adapter?.addLoadStateListener { loadState ->
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
        val type = arguments?.getString("type")
        type?.let { loadData(it) }
        return dataBinding.root
    }

    private fun loadData(type: String) {
        lifecycleScope.launch {
            viewModel.loadNews(type).collect {
                adapter?.submitData(pagingData = it, lifecycle = viewLifecycleOwner.lifecycle)
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_news
    }

    override fun initView() {

    }

    override fun initViewModel() {

    }

    override fun initListener() {

    }
}