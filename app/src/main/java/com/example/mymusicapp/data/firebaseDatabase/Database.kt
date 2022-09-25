package com.example.mymusicapp.data.firebaseDatabase

import android.util.Log
import com.example.mymusicapp.domain.models.Music
import com.example.mymusicapp.domain.models.Post
import com.example.mymusicapp.domain.models.User
import com.example.mymusicapp.utils.Response
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface Database {

    suspend fun setUserDataInfoOnDatabase(user: User)
    suspend fun setPostDataInfoOnDatabase(post:Post): Task<Void>
    suspend fun getCurrentUserData(id: String): User
    suspend fun getAllUsers(): List<User>
    fun getPostId(): String
//    fun getUserId():String
   suspend fun getAllPosts(): MutableList<Post>
}

class DatabaseFromFirebase @Inject constructor(
    private var databaseRef: DatabaseReference,
private var db: FirebaseFirestore
) : Database {

   override suspend fun getAllPosts(): MutableList<Post> {
       return db.collection("posts").get().await().toObjects(Post::class.java)

   }

    override suspend fun setUserDataInfoOnDatabase(user: User) {
        db.collection("users")
            .document(user.userId).set(user)
    }

    override suspend fun setPostDataInfoOnDatabase(post: Post) : Task<Void> {
        val postId = db.collection("posts").document().id
      return  db.collection("posts")
            .document(postId).set(post)

    }

    override suspend fun getCurrentUserData(id: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override fun getPostId(): String {
      return db.collection("posts").document().id
    }
//    override fun getUserId(): String {
//        return db.collection("users").document().id
//    }
}



