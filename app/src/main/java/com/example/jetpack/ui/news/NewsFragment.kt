package com.example.jetpack.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.jetpack.R
import com.example.jetpack.databinding.FragmentNewsBinding
import com.example.jetpack.model.NewsModel
import com.example.jetpack.mvvm.base.adatper.OnItemClickListener
import com.example.jetpack.mvvm.base.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>() {
    companion object {
        const val BUNDLE_TYPE = "bundle_type"
    }

    private lateinit var adapter: NewsPagingAdapter

    private val viewModel by viewModels<NewsViewModel>()


    @ExperimentalPagingApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentNewsBinding.inflate(inflater, container, false)
        adapter = NewsPagingAdapter(requireContext())
        dataBinding.smartRefresh.setEnableLoadMore(false)
        dataBinding.smartRefresh.setOnRefreshListener {
            adapter.refresh()
        }
        dataBinding.recyclerView.adapter = adapter
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider_item_decoration)?.apply {
            dividerItemDecoration.setDrawable(this)
        }
        dataBinding.recyclerView.addItemDecoration(dividerItemDecoration)
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
        viewModel.newsDataFromNet.observe(viewLifecycleOwner, Observer {
            adapter.submitData(pagingData = it, lifecycle = viewLifecycleOwner.lifecycle)
        })

//        viewModel.newsDataFromDb.observe(viewLifecycleOwner, Observer {
//            adapter.submitData(pagingData = it, lifecycle = viewLifecycleOwner.lifecycle)
//        })
    }

    override fun initListener() {
        adapter.onItemClickListener = object : OnItemClickListener<NewsModel> {
            override fun onClickListener(item: NewsModel?, position: Int?) {
                super.onClickListener(item, position)
                val bundle = bundleOf(LoadNewsPageFragment.BUNDLE_TITLE to item)
                findNavController().navigate(R.id.action_global_navigationNewsPageActivity, bundle)
            }
        }
    }
}