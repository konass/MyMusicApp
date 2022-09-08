package com.example.mymusicapp.domain.models

data class User
    (
var userId: String = "",
var password: String= "",
var email: String= "",
var name: String= "",
var lastName: String= "",
var nickName: String= "",
var imageUrl: String= "",
var description: String="",
var following : List<String> = emptyList(),
var followers : List<String> = emptyList(),
var nFollowers: Int? = null
            )