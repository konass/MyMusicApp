package com.example.mymusicapp.domain.use_case.authenticationUseCases

import com.example.mymusicapp.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseAuthState @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke() = repository.getFirebaseAuthState()
}