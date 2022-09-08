package com.example.mymusicapp.domain.models

data class Post(
    var postId : String = "",
    var imageUrl: String,
    //var music: Music,
    var author: User? = null,
    var time : Long?=0L,
    var userId: String="",
    var nLikes: Int? = null,
    var likes: List<String>? = null,
    var comments: List<String>? = null
)
