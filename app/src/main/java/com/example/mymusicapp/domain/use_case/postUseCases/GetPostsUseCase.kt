package com.example.mymusicapp.domain.use_case.postUseCases

import com.example.mymusicapp.domain.models.Post
import com.example.mymusicapp.domain.models.User
import com.example.mymusicapp.domain.repository.PostRepository
import com.example.mymusicapp.utils.Response
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: PostRepository
){
  lateinit var   postList: List<Post?>
    operator fun invoke (): Flow<Response<List<Post?>>> = flow{
        try {
            emit(Response.Loading)
            repository.getAllPosts().forEach{
              postList = listOf(it)
            }
            emit(Response.Success(postList))
        }catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An Unexpected Error"))
        }
    }




}
