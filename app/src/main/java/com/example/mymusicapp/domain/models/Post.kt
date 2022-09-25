package com.example.mymusicapp.domain.models

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.DocumentId

data class Post(
    var postId : String? = null,
    var imageUrl: String? = null,
    //var music: Music,
    var author: User? = null,
    var postText: String? = null,
    var time : Long?=null,
    var userId: String? = null,
    var nLikes: Int? = null,
    var likes: List<String>? = null,
    var comments: List<String>? = null,
var music: Music? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        TODO("author"),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        TODO("music")) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(postId)
        parcel.writeString(imageUrl)
        parcel.writeString(postText)
        parcel.writeValue(time)
        parcel.writeString(userId)
        parcel.writeValue(nLikes)
        parcel.writeStringList(likes)
        parcel.writeStringList(comments)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }
}

