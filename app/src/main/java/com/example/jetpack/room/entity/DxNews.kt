package com.example.jetpack.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Description :
 * CreateTime  : 2020/8/20
 */
@Entity(tableName = "T_News")
class DxNews {
    @PrimaryKey
    var uuid: String = ""
    var type: String? = null
    var title: String? = null
    var date: String? = null
    var category: String? = null
    var authorName: String? = null
    var url: String? = null
    var thumbnail_pic_s: String? = null
    var thumbnail_pic_s02: String? = null
    var thumbnail_pic_s03: String? = null

    override fun toString(): String {
        return "DxNews(uuid='$uuid', type=$type, title=$title, date=$date, category=$category, authorName=$authorName, url=$url, thumbnail_pic_s=$thumbnail_pic_s, thumbnail_pic_s02=$thumbnail_pic_s02, thumbnail_pic_s03=$thumbnail_pic_s03)"
    }
}