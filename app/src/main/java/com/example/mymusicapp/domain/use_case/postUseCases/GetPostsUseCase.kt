package com.example.mymusicapp.domain.use_case.postUseCases

import com.example.mymusicapp.domain.models.Post
import com.example.mymusicapp.domain.repository.PostRepository
import com.example.mymusicapp.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: PostRepository
){
   private var   postList: MutableList<Post?> = emptyArray<Post?>().toMutableList()
    operator fun invoke (): Flow<Response<List<Post?>>> = flow{
        try {
            emit(Response.Loading)
            repository.getAllPosts().forEach{
              postList.add(it)
            }
            emit(Response.Success(postList))
        }catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An Unexpected Error"))
        }
    }




}
