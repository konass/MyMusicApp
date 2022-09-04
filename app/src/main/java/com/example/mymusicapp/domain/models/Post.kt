package com.example.mymusicapp.domain.models

data class Post(
    var imageUrl: String,
    var music: Music,
    var like: Int,
    var comment: Comment,
    var userId: String
)
