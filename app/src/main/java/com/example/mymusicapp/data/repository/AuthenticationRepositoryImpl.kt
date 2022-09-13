package com.example.mymusicapp.data.repository

import com.example.mymusicapp.data.firebaseDatabase.Database
import com.example.mymusicapp.domain.models.User
import com.example.mymusicapp.domain.repository.AuthenticationRepository
import com.example.mymusicapp.domain.use_case.authenticationUseCases.FirebaseAuthState
import com.example.mymusicapp.utils.Response
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseError
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(

    private var database: Database,
    private val auth: FirebaseAuth

) : AuthenticationRepository {
    override fun isUserAuthenticatedInFirebase(): Boolean {
        return auth.currentUser != null
    }

    override fun getFirebaseAuthState(): Flow<Boolean> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener {
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }


    override suspend fun signIn(email: String, password: String) : Task<AuthResult> {
return auth.signInWithEmailAndPassword(email, password)
    }

    override suspend fun signUp(
        email: String,
        password: String,
        name: String,
        lastName: String
    ):  Task<AuthResult>{
        return auth.createUserWithEmailAndPassword(email, password)
    }

    override suspend fun signOut() {
      return auth.signOut()
    }

    override fun getCurrentUserId(): String =
        auth.currentUser?.uid.toString()
    override suspend fun setUserDataInfoOnDatabase(user: User) {
        database.setUserDataInfoOnDatabase(user)
    }
}