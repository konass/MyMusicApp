package com.example.mymusicapp.data.repository

import com.example.mymusicapp.data.firebaseDatabase.Database
import com.example.mymusicapp.data.firebaseStorage.Storage
import com.example.mymusicapp.domain.models.User
import com.example.mymusicapp.domain.repository.PostRepository
import com.example.mymusicapp.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val database: Database,
    private val auth: FirebaseAuth,
    private val storage: Storage
): UserRepository {
    override suspend fun getCurrentUserData(userID: String): User {
        return database.getCurrentUserData(userID)
    }
    override fun getUserId(): String {
        return auth.currentUser?.uid.toString()
    }
}