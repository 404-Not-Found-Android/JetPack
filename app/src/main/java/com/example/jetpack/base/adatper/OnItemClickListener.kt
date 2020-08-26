package com.example.jetpack.base.adatper

/**
 * Description :
 * CreateTime  : 2020/8/26
 */
interface OnItemClickListener<T> {
    fun onClickListener(item: T? = null, position: Int? = null) {}
    fun onLongClickListener(item: T? = null, position: Int? = null) {}
}