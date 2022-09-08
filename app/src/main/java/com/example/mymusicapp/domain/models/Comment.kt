package com.example.mymusicapp.domain.models

data class Comment(
    var id: String = "",
    var author: User? = null,
    val text: String = "",
    val creationTime: Long =0L
)
