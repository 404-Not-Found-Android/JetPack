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
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNewsBinding.inflate(inflater, container, false)
        context ?: return binding.root
        adapter = NewsAdapter(requireContext())
        binding.smartRefresh.setEnableLoadMore(false)
        binding.smartRefresh.setOnRefreshListener {
            adapter?.refresh()
        }
        binding.recyclerView.adapter = adapter
        adapter?.addDataRefreshListener {
            binding.smartRefresh.finishRefresh()
        }
        adapter?.addLoadStateListener { loadState ->
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
//        loadNewsJob?.cancel()
//        loadNewsJob =
        lifecycleScope.launch {
            viewModel.loadNews(type).collect {
                adapter?.submitData(pagingData = it, lifecycle = viewLifecycleOwner.lifecycle)
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
//        loadNewsJob?.cancel()
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