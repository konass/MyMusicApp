package com.example.mymusicapp.domain.models

data class User
    (
var userId: String,
var password: String,
var email: String,
var name: String,
var lastName: String,
var nickName: String,
var imageUrl: String,
var post: Post,
var music: Music,
var description: String,
var chat: Chat,
var notifications: Notifications
            )