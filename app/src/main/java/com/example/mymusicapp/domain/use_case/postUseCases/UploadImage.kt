package com.example.mymusicapp.domain.use_case.postUseCases

import android.net.Uri
import com.example.mymusicapp.domain.repository.PostRepository
import javax.inject.Inject

class UploadImage @Inject constructor(
    private val repository: PostRepository
){
    suspend operator fun invoke(image: Uri) = repository.uploadPhoto(image)
}