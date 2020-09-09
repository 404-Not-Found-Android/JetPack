package com.example.jetpack.ui.news

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.BR
import com.example.jetpack.databinding.ItemNewsBinding
import com.example.jetpack.model.NewsModel
import com.example.mvvm.base.adatper.OnItemClickListener

/**
 * Description :
 * CreateTime  : 2020/7/27
 */
class NewsAdapter(context: Context) : PagingDataAdapter<NewsModel, RecyclerView.ViewHolder>(newsDiffCallback) {

    companion object {
        private val newsDiffCallback = object : DiffUtil.ItemCallback<NewsModel>() {
            override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                return oldItem.uniquekey == newItem.uniquekey
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    private val layoutInflater by lazy { LayoutInflater.from(context) }

    lateinit var onItemClickListener: OnItemClickListener<NewsModel>

    class ViewHolder<T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder<*>).run {
            val newsModel = getItem(position)
            binding.setVariable(BR.newBean,getItem(position))
            binding.setVariable(BR.clickListener,View.OnClickListener { onItemClickListener.onClickListener(item = newsModel) })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(ItemNewsBinding.inflate(layoutInflater, parent, false))
    }
}