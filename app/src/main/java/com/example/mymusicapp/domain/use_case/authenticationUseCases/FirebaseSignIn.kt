package com.example.mymusicapp.domain.use_case.authenticationUseCases

import com.example.mymusicapp.domain.repository.AuthenticationRepository
import com.example.mymusicapp.utils.Response
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseSignIn @Inject constructor(
    private val repository: AuthenticationRepository
) {
    var operationSuccessful: Boolean = false
    operator fun invoke(email: String, password: String): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
        try {
            emit(Response.Loading)
            repository.signIn(email, password).addOnSuccessListener {
                operationSuccessful = true
            }.await()
            emit(Response.Success(operationSuccessful))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An Unexpected Error"))
        }
    }
}