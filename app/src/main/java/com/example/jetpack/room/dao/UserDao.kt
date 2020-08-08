package com.example.jetpack.room.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.jetpack.room.entity.User

/**
 * Description :
 * CreateTime  : 2020/7/9
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: List<User>)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM user ORDER BY firstName")
    fun queryAllUser(): LiveData<List<User>>

    @Query("SELECT * FROM user")
    fun queryUser(): DataSource.Factory<Int, User>
}