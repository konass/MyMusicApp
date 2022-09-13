package com.example.mymusicapp.data.firebaseDatabase

import com.example.mymusicapp.domain.models.Post
import com.example.mymusicapp.domain.models.User
import com.example.mymusicapp.utils.Constants
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface Database {

    suspend fun setUserDataInfoOnDatabase(user: User)

    suspend fun getCurrentUserData(id: String): User
    suspend fun getAllUsers(): List<User>
    suspend fun getAllPosts(): List<Post>
}

class DatabaseFromFirebase @Inject constructor(
    private var databaseRef: DatabaseReference,
) : Database {

    override suspend fun getAllPosts(): List<Post> {
        return databaseRef.child(Constants.POSTS).get().await()
            .children.map {
                it.getValue(Post::class.java)!!
            }
    }
    override suspend fun setUserDataInfoOnDatabase(user: User) {
        databaseRef.child("users").child(user.userId).setValue(user).await()
    }

    override suspend fun getCurrentUserData(id: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsers(): List<User> {
        TODO("Not yet implemented")
    }
}



