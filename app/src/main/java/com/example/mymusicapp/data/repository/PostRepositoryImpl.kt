package com.example.mymusicapp.data.repository

import android.net.Uri
import com.example.mymusicapp.data.firebaseDatabase.Database
import com.example.mymusicapp.data.firebaseStorage.Storage
import com.example.mymusicapp.domain.models.Music
import com.example.mymusicapp.domain.models.Post
import com.example.mymusicapp.domain.models.User
import com.example.mymusicapp.domain.repository.PostRepository
import com.example.mymusicapp.utils.Response
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val database: Database,
    private val auth: FirebaseAuth,
    private val storage: Storage
):PostRepository {
   // override fun getAllPosts(userid: String): Flow<Response<List<Post>>> {
   // }



    override suspend fun setPostDataOnDatabase(post: Post): Task<Void> {
      return  database.setPostDataInfoOnDatabase(post)
    }

    override fun getPostId(): String {
        return database.getPostId()
    }

    override fun getUserId(): String {
        return auth.currentUser?.uid.toString()
    }

    override suspend fun uploadPhoto(image: Uri): String {
        return storage.uploadImage(image)
    }

}