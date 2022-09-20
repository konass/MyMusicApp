package com.example.mymusicapp.data.repository

import com.example.mymusicapp.data.firebaseDatabase.Database
import com.example.mymusicapp.domain.models.Music
import com.example.mymusicapp.domain.models.Post
import com.example.mymusicapp.domain.models.User
import com.example.mymusicapp.domain.repository.PostRepository
import com.example.mymusicapp.utils.Response
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val database: Database
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
        return database.getUserId()
    }

}