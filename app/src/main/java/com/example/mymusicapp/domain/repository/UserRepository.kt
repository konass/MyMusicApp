package com.example.mymusicapp.domain.repository

import com.example.mymusicapp.domain.models.User

interface UserRepository{
    suspend fun getCurrentUserData(userID:String): User
    fun getUserId(): String
}