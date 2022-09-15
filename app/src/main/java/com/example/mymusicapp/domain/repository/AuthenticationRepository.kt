package com.example.mymusicapp.domain.repository

import com.example.mymusicapp.domain.models.User
import com.example.mymusicapp.domain.use_case.authenticationUseCases.FirebaseAuthState
import com.example.mymusicapp.utils.Response
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun isUserAuthenticatedInFirebase() : Boolean
    fun getFirebaseAuthState(): Flow<Boolean>
   suspend fun signIn(email: String, password: String): Task<AuthResult>
   suspend fun signUp(email: String, password: String, name: String, lastName: String): Task<AuthResult>
   suspend fun signOut()
   fun getCurrentUserId():String
    suspend fun setUserDataInfoOnDatabase(user: User)
}