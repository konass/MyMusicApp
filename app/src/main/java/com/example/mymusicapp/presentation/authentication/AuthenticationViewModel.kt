package com.example.mymusicapp.presentation.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymusicapp.domain.use_case.authenticationUseCases.AuthenticationUseCases
import com.example.mymusicapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authUseCases: AuthenticationUseCases
) : ViewModel()
{
    val isUserAuthenticated get() = authUseCases.firebaseIsUserAuthenticated()
    private val _signInState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signInState: State<Response<Boolean>> = _signInState
    private val _signUpState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signUpState: State<Response<Boolean>> = _signUpState
    private val _signOutState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutState: State<Response<Boolean>> = _signOutState
    private val _firebaseAuthState = mutableStateOf<Boolean>(false)
    val firebaseAuthState: State<Boolean> = _firebaseAuthState
    fun signIn (email: String, password: String){
        authUseCases.firebaseSignIn(email, password).onEach {
            when(it){
               is Response.Loading-> _signInState.value = Response.Loading
                is Response.Success -> _signInState.value = Response.Success(true)
                is Response.Error -> _signInState.value = Response.Error("An unexpected error")
            }
        }.launchIn(viewModelScope)
    }
    fun signUp (email: String, password: String, name: String, lastName: String){
        authUseCases.firebaseSignUp(email, password, name, lastName).onEach {
            when(it){
                is Response.Loading-> _signUpState.value = Response.Loading
                is Response.Success -> _signUpState.value = Response.Success(true)
                is Response.Error -> _signUpState.value = Response.Error("An unexpected error")
            }
        }.launchIn(viewModelScope)
    }
    fun signOut (){
        authUseCases.firebaseSignOut().onEach {
            when(it){
                is Response.Loading-> _signOutState.value = Response.Loading
                is Response.Success -> _signOutState.value = Response.Success(true)
                is Response.Error -> _signOutState.value = Response.Error("An unexpected error")
            }
        }.launchIn(viewModelScope)
    }
   fun getFirebaseAuthState() {
            viewModelScope.launch{
                authUseCases.firebaseAuthState().collect{
                    _firebaseAuthState.value =it
                }
            }
    }
}