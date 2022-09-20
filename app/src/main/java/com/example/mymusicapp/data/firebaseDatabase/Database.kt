package com.example.mymusicapp.data.firebaseDatabase

import android.util.Log
import com.example.mymusicapp.domain.models.Music
import com.example.mymusicapp.domain.models.Post
import com.example.mymusicapp.domain.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

interface Database {

    suspend fun setUserDataInfoOnDatabase(user: User)
    suspend fun setPostDataInfoOnDatabase(post:Post): Task<Void>
    suspend fun getCurrentUserData(id: String): User
    suspend fun getAllUsers(): List<User>
    fun getPostId(): String
    fun getUserId():String
  //  suspend fun getAllPosts(user: User): List<Post>
}

class DatabaseFromFirebase @Inject constructor(
    private var databaseRef: DatabaseReference,
private var db: FirebaseFirestore
) : Database {

   /* override fun getAllPosts(user: User): List<Post> {
        databaseRef = Firebase.database.reference
      db.collection("users")
            .get()
            .addOnSuccessListener { querySnapshot ->
                querySnapshot.forEach { document ->

                    Log.d("MyLog", "Read document with ID ${document.id}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("MyLog", "Error getting documents $exception")
            }
    }*/

    override suspend fun setUserDataInfoOnDatabase(user: User) {
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("MyLog", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("MyLog", "Error adding document", e)
            }
    }

    override suspend fun setPostDataInfoOnDatabase(post: Post) : Task<Void> {
        val postId = db.collection("posts").document().id
      return  db.collection("post")
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
    override fun getUserId(): String {
        return db.collection("users").document().id
    }
}



