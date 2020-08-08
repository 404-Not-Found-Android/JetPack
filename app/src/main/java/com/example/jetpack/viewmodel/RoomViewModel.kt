package com.example.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.jetpack.room.dao.UserDao
import com.example.jetpack.room.entity.User

/**
 * Description :
 * CreateTime  : 2020/7/27
 */
class RoomViewModel(roomDao: UserDao) : ViewModel() {

    val userList: LiveData<PagedList<User>> = roomDao.queryUser().toLiveData(pageSize = 30)

}