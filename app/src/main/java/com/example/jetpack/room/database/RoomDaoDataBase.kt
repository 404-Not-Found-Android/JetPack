package com.example.jetpack.room.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetpack.BaseApplication
import com.example.jetpack.room.dao.NewsDao
import com.example.jetpack.room.dao.UserDao
import com.example.jetpack.room.entity.DxNews
import com.example.jetpack.room.entity.User

/**
 * Description :
 * CreateTime  : 2020/7/9
 */
@Database(entities = [User::class,DxNews::class], version = 1, exportSchema = false)
abstract class RoomDaoDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun newsDao(): NewsDao

    companion object {
        private const val DB_NAME = "JetPack_DB"

        @Volatile
        private var instance: RoomDaoDataBase? = null

        fun init(): RoomDaoDataBase {
            synchronized(this) {
                instance = null
                return buildDatabase().also { instance = it }
            }
        }

        fun getInstance(): RoomDaoDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase().also { instance = it }
            }
        }

        private fun buildDatabase(): RoomDaoDataBase {
            val context = BaseApplication.context
            return Room.databaseBuilder(context, RoomDaoDataBase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .build()
        }

    }

}