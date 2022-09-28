package com.example.mymusicapp.domain.use_case.userUseCases

import com.example.mymusicapp.domain.models.Post
import com.example.mymusicapp.domain.models.User
import com.example.mymusicapp.domain.repository.UserRepository
import com.example.mymusicapp.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrentUserData @Inject constructor(
    private val repository: UserRepository
) {
    var user: User?= null
    operator fun invoke (): Flow<Response<User?>> = flow{
        try {
            emit(Response.Loading)
            val userID = repository.getUserId()
          user = repository.getCurrentUserData(userID)
            emit(Response.Success((user)))
        }catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An Unexpected Error"))
        }
    }
}