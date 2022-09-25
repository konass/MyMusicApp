package com.example.mymusicapp.domain.use_case.postUseCases

data class PostUseCases (
     val setPostDataOnDatabase: SetPostDataOnDatabase,
     val uploadImage: UploadImage,
     val getPostsUseCase: GetPostsUseCase
        )