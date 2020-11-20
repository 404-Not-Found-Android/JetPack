package com.example.jetpack.view.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

/**
 * Desc:
 * Created by SunHang on 8/19.
 * Email：sunh@eetrust.com
 */
abstract class BaseAdapter<D : Any> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal val data: MutableList<D> = mutableListOf()

    @LayoutRes
    abstract fun getEmpty(): Int?

    @LayoutRes
    abstract fun getHeader(): Int?

    @LayoutRes
    abstract fun getFooter(): Int?

    abstract fun getItemViewTypeCustom(position: Int): Int

    abstract fun onCreateViewHolderCustom(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    abstract fun onBindViewHolderCustom(holder: RecyclerView.ViewHolder, position: Int)

    override fun getItemViewType(position: Int): Int {
        return when {
            isEmpty(position) -> {
                getEmpty()?: throw Exception("The empty view is Null, have you forgotten to set the empty view ?")
            }
            isHeader(position) -> {
                getHeader()?: throw Exception("The header view is Null, have you forgotten to set the header view ?")
            }
            isFooter(position) -> {
                getFooter()?: throw Exception("The footer view is Null, have you forgotten to set the footer view ?")
            }
            else -> {
                getItemViewTypeCustom(position - getHeaderCount())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            getEmpty() -> {
                EmptyViewHolder(LayoutInflater.from(parent.context)
                    .inflate(viewType, parent, false))
            }
            else -> {onCreateViewHolderCustom(parent, viewType)}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (!isEmpty(position)) {
            onBindViewHolderCustom(holder, position - getHeaderCount())
        }
    }

    override fun getItemCount(): Int {
        return if (data.isEmpty()) {
            getHeaderCount() + getFooterCount() + getEmptyCount()
        } else {
            getHeaderCount() + getFooterCount() + getRealItemCount()
        }
    }

    fun getItem(position: Int): D? {
        if (position in data.indices) return data[position]
        return null
    }

    private fun getEmptyCount(): Int {
        return if (getEmpty() == null) 0 else 1
    }

    private fun getRealItemCount(): Int {
        return data.size
    }

    private fun getFooterCount(): Int {
        return if (getFooter() == null) 0 else 1
    }

    private fun getHeaderCount(): Int {
        return if (getHeader() == null) 0 else 1
    }

    private fun isEmpty(position: Int): Boolean {
        return data.size == 0 && position == getHeaderCount()
    }

    private fun isHeader(position: Int): Boolean {
        return position < getHeaderCount()
    }

    private fun isFooter(position: Int): Boolean {
        return position >= getHeaderCount() + getRealItemCount()
    }

    inner class EmptyViewHolder(emptyView: View) : RecyclerView.ViewHolder(emptyView)


}