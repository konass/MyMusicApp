package com.example.mymusicapp.domain.use_case.postUseCases

import com.example.mymusicapp.domain.models.Music
import com.example.mymusicapp.domain.models.Post
import com.example.mymusicapp.domain.models.User
import com.example.mymusicapp.domain.repository.PostRepository
import com.example.mymusicapp.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SetPostDataOnDatabase @Inject constructor(
    private val repository: PostRepository
){
    private var operationSuccessful: Boolean = false
    operator fun invoke (
                         imageUrl: String,
                         author: User?,
                         postText: String,
                         time: Long?,
                         nLikes: Int?,
                         likes: List<String>?,
                         comments: List<String>?,
                         music: Music?
    ) : Flow<Response<Boolean>> = flow {
val post = Post(
    postId= repository.getPostId(),
    imageUrl = imageUrl,
    author=author,
    postText=postText,
    time=time,
    userId=repository.getUserId(),
    nLikes=nLikes,
    likes=likes,
    comments=comments,
    music=music
)
        operationSuccessful=false
        try {
            emit(Response.Loading)
            repository.setPostDataOnDatabase(post).addOnSuccessListener {
                operationSuccessful = true
            }.await()
            if(operationSuccessful){
                emit(Response.Success(operationSuccessful))
            }
            else{
                emit(Response.Error("Post Upload Unsuccessful"))
            }
        }catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An Unexpected Error"))
        }
    }
}