package com.example.jetpack.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Description :
 * CreateTime  : 2020/7/9
 */
@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var firstName: String,
    var lastName: String
)