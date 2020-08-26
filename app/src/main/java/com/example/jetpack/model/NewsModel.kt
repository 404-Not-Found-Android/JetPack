package com.example.jetpack.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Description :
 * CreateTime  : 2020/7/27
 */
class NewsModel() : Parcelable {
    var uniquekey: String? = null
    var title: String? = null
    var date: String? = null
    var category: String? = null
    var author_name: String? = null
    var url: String? = null
    var thumbnail_pic_s: String? = null
    var thumbnail_pic_s02: String? = null
    var thumbnail_pic_s03: String? = null

    constructor(parcel: Parcel) : this() {
        uniquekey = parcel.readString()
        title = parcel.readString()
        date = parcel.readString()
        category = parcel.readString()
        author_name = parcel.readString()
        url = parcel.readString()
        thumbnail_pic_s = parcel.readString()
        thumbnail_pic_s02 = parcel.readString()
        thumbnail_pic_s03 = parcel.readString()
    }


    override fun toString(): String {
        return "NewsBean(uniquekey=$uniquekey, title=$title, date=$date, category=$category, author_name=$author_name, url=$url, thumbnail_pic_s=$thumbnail_pic_s, thumbnail_pic_s02=$thumbnail_pic_s02, thumbnail_pic_s03=$thumbnail_pic_s03)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uniquekey)
        parcel.writeString(title)
        parcel.writeString(date)
        parcel.writeString(category)
        parcel.writeString(author_name)
        parcel.writeString(url)
        parcel.writeString(thumbnail_pic_s)
        parcel.writeString(thumbnail_pic_s02)
        parcel.writeString(thumbnail_pic_s03)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsModel> {
        override fun createFromParcel(parcel: Parcel): NewsModel {
            return NewsModel(parcel)
        }

        override fun newArray(size: Int): Array<NewsModel?> {
            return arrayOfNulls(size)
        }
    }
}