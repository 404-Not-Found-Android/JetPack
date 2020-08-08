package com.example.jetpack.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.jetpack.room.Executors
import com.example.jetpack.room.dao.UserDao
import com.example.jetpack.room.entity.User
import com.example.jetpack.workers.RoomDataBaseWorker

/**
 * Description :
 * CreateTime  : 2020/7/9
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class RoomDaoDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private const val DB_NAME = "JetPack_DB"

        @Volatile
        private var instance: RoomDaoDataBase? = null

        @Synchronized
        fun getInstance(context: Context): RoomDaoDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDaoDataBase::class.java,
                    DB_NAME
                ).allowMainThreadQueries().build()
            }
            return instance as RoomDaoDataBase
        }

    }

    fun buildDatabase(context: Context): RoomDaoDataBase {
        return Room.databaseBuilder(context, RoomDaoDataBase::class.java, DB_NAME)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val request = OneTimeWorkRequestBuilder<RoomDataBaseWorker>().build()
                    WorkManager.getInstance(context).enqueue(request)
                }
            }).build()
    }


}