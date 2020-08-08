package com.example.jetpack.net

/**
 * Desc:
 * Created by SunHang on 6/1.
 * Email：sunh@eetrust.com
 */
data class NetworkState private constructor(val status: Status, val msg: String? = null) {
    companion object {
        val LOADING =
            NetworkState(Status.LOADING)
        val SUCCESS =
            NetworkState(Status.SUCCESS)
        val FAILED =
            NetworkState(Status.FAILED)
        val EMPTY =
            NetworkState(Status.EMPTY)
        val SHOW_CONTENT =
            NetworkState(Status.SHOW_CONTENT)
        val NO_MORE =
            NetworkState(Status.NO_MORE)
        val REFRESH_ERROR =
            NetworkState(Status.REFRESH_ERROR)
        val LOAD_MORE_ERROR =
            NetworkState(Status.LOAD_MORE_ERROR)
        fun error(msg: String?) = NetworkState(
            Status.FAILED,
            msg
        )
    }
}

enum class Status {
    LOADING,
    SUCCESS,
    FAILED,
    EMPTY,
    SHOW_CONTENT,
    NO_MORE,
    REFRESH_ERROR,
    LOAD_MORE_ERROR
}