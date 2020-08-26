package com.example.jetpack.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import com.example.jetpack.R
import com.example.jetpack.base.adatper.OnItemClickListener
import com.example.jetpack.base.fragment.BaseFragment
import com.example.jetpack.databinding.FragmentNewsBinding
import com.example.jetpack.model.NewsModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>() {
    companion object {
        const val BUNDLE_TYPE = "bundle_type"
    }

    private var adapter: NewsAdapter? = null

    private val viewModel by viewModels<NewsViewModel>()


    @ExperimentalPagingApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        arguments?.takeIf { it.containsKey(BUNDLE_TYPE) }?.apply {
            getString(BUNDLE_TYPE)?.let {
                viewModel.loadNews(it)
            }

        }
        return dataBinding.root
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_news
    }

    override fun initView() {

    }

    override fun initViewModel() {
//        viewModel.newsDataFromNet.observe(viewLifecycleOwner, Observer {
//            adapter?.submitData(pagingData = it, lifecycle = viewLifecycleOwner.lifecycle)
//        })

        viewModel.newsDataFromDb.observe(viewLifecycleOwner, Observer {
            adapter?.submitData(pagingData = it, lifecycle = viewLifecycleOwner.lifecycle)
        })
    }

    override fun initListener() {
        adapter?.onItemClickListener = object : OnItemClickListener<NewsModel> {
            override fun onClickListener(item: NewsModel?, position: Int?) {
                super.onClickListener(item, position)
                val bundle = bundleOf(LoadNewsPageFragment.BUNDLE_TITLE to item)
                findNavController().navigate(R.id.action_global_navigationLoadNewsPageFragment,bundle)
            }
        }
    }
}