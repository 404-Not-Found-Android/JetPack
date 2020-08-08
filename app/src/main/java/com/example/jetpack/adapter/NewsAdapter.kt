package com.example.jetpack.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.databinding.ItemNewsBinding

/**
 * Description :
 * CreateTime  : 2020/7/27
 */
class NewsAdapter : PagingDataAdapter<NewsBean, NewsAdapter.NewsViewHolder>(NewsDiffCallback()) {

    class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.newBean?.let {

                }
            }
        }

        fun bind(item: NewsBean) {
            binding.apply {
                newBean = item
                executePendingBindings()
            }
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

private class NewsDiffCallback : DiffUtil.ItemCallback<NewsBean>() {
    override fun areItemsTheSame(oldItem: NewsBean, newItem: NewsBean): Boolean {
        return oldItem.uniquekey == newItem.uniquekey
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: NewsBean, newItem: NewsBean): Boolean {
        return oldItem == newItem
    }

}