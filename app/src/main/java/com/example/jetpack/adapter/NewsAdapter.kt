package com.example.jetpack.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.BR
import com.example.jetpack.databinding.ItemNewsBinding

/**
 * Description :
 * CreateTime  : 2020/7/27
 */
class NewsAdapter(context: Context) : PagingDataAdapter<NewsBean, RecyclerView.ViewHolder>(newsDiffCallback) {

    companion object {
        private val newsDiffCallback = object : DiffUtil.ItemCallback<NewsBean>() {
            override fun areItemsTheSame(oldItem: NewsBean, newItem: NewsBean): Boolean {
                return oldItem.uniquekey == newItem.uniquekey
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: NewsBean, newItem: NewsBean): Boolean {
                return oldItem == newItem
            }
        }
    }

    private val layoutInflater by lazy { LayoutInflater.from(context) }

    class ViewHolder<T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder<*>).binding.setVariable(BR.newBean,getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(ItemNewsBinding.inflate(layoutInflater, parent, false))
    }
}