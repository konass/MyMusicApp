package com.example.mymusicapp.domain.repository

import android.net.Uri
import com.example.mymusicapp.domain.models.Music
import com.example.mymusicapp.domain.models.Post
import com.example.mymusicapp.domain.models.User
import com.example.mymusicapp.utils.Response
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow

interface PostRepository {
   //
    // fun getAllPosts(userid : String): Flow<Response<List<Post>>>


    suspend fun setPostDataOnDatabase(post: Post): Task<Void>
    fun getPostId() : String
    fun getUserId() : String
   suspend fun uploadPhoto(image: Uri) : String
}