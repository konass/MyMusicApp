package com.example.mymusicapp.presentation.post

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymusicapp.domain.models.Music
import com.example.mymusicapp.domain.models.Post
import com.example.mymusicapp.domain.models.User
import com.example.mymusicapp.domain.use_case.postUseCases.PostUseCases
import com.example.mymusicapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postUseCases: PostUseCases
): ViewModel() {
    private val _setPostState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val setPostState: State<Response<Boolean>> = _setPostState

    fun setPostData (
                     imageUrl: String,
                     author: User?,
                     postText: String,
                     time: Long?,
                     nLikes: Int?,
                     likes: List<String>?,
                     comments: List<String>?,
                     music: Music?
    ){
        postUseCases.setPostDataOnDatabase(
            imageUrl = imageUrl,
            author=author,
            postText=postText,
            time=time,
            nLikes=nLikes,
            likes=likes,
            comments=comments,
            music=music).onEach {
            when(it){
                is Response.Loading-> _setPostState.value = Response.Loading
                is Response.Success -> _setPostState.value = Response.Success(true)
                is Response.Error -> _setPostState.value = Response.Error("An unexpected error")
            }
        }.launchIn(viewModelScope)
    }
    fun uploadImage(image: Uri){
        viewModelScope.launch {
            postUseCases.uploadImage.invoke(image)
        }
    }

}
