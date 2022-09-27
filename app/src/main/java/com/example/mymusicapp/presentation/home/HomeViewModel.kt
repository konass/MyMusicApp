package com.example.mymusicapp.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymusicapp.domain.models.Post
import com.example.mymusicapp.domain.use_case.postUseCases.PostUseCases
import com.example.mymusicapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class HomeViewModel@Inject constructor(
    private val postUseCases: PostUseCases
): ViewModel()  {
    private val _getPostState = MutableStateFlow<Response<List<Post?>>>(Response.Success(emptyList()))
    val getPostState: StateFlow<Response<List<Post?>>> = _getPostState
    fun getPosts() {
        postUseCases.getPostsUseCase().onEach {
            when (it) {
                is Response.Loading -> _getPostState.value = Response.Loading
                is Response.Success -> _getPostState.value = Response.Success(it.data)
                is Response.Error ->  _getPostState.value = Response.Error(it.message)
            }

        }.launchIn(viewModelScope)
    }
}