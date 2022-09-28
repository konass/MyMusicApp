package com.example.mymusicapp.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymusicapp.domain.models.User
import com.example.mymusicapp.domain.use_case.userUseCases.UserUseCases
import com.example.mymusicapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userUseCases: UserUseCases
): ViewModel() {
    private val _getUserState = MutableStateFlow<Response<User?>>(Response.Success(null))
    val getUserState: StateFlow<Response<User?>> = _getUserState
    fun getCurrentUser() {
       viewModelScope.launch {
           _getUserState.value = Response.Loading
           val result = userUseCases.getCurrentUserData.user
           _getUserState.value = Response.Success(result)
       }
    }
}