package com.example.jetpack.model

/**
 * Description :
 * CreateTime  : 2020/7/27
 */
class NewsModel {
    var uniquekey: String? = null
    var title: String? = null
    var date: String? = null
    var category: String? = null
    var author_name: String? = null
    var url: String? = null
    var thumbnail_pic_s: String? = null
    var thumbnail_pic_s02: String? = null
    var thumbnail_pic_s03: String? = null
    override fun toString(): String {
        return "NewsBean(uniquekey=$uniquekey, title=$title, date=$date, category=$category, author_name=$author_name, url=$url, thumbnail_pic_s=$thumbnail_pic_s, thumbnail_pic_s02=$thumbnail_pic_s02, thumbnail_pic_s03=$thumbnail_pic_s03)"
    }
}