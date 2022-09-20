package com.example.mymusicapp.di

import com.example.mymusicapp.data.firebaseDatabase.Database
import com.example.mymusicapp.data.firebaseDatabase.DatabaseFromFirebase
import com.example.mymusicapp.data.repository.AuthenticationRepositoryImpl
import com.example.mymusicapp.data.repository.PostRepositoryImpl
import com.example.mymusicapp.domain.repository.AuthenticationRepository
import com.example.mymusicapp.domain.repository.PostRepository
import com.example.mymusicapp.domain.use_case.authenticationUseCases.*
import com.example.mymusicapp.domain.use_case.postUseCases.PostUseCases
import com.example.mymusicapp.domain.use_case.postUseCases.SetPostDataOnDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideFirebaseAuthentication(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
    @Singleton
    @Provides
    fun provideDatabaseReference() = FirebaseDatabase.getInstance().reference
    @Singleton
    @Provides
    fun provideDatabase(
        databaseRef: DatabaseReference,
        db: FirebaseFirestore
    ): Database = DatabaseFromFirebase(databaseRef, db)
    @Singleton
    @Provides
    fun provideFirebaseStorage() = FirebaseStorage.getInstance().reference
    @Provides
    @Singleton
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()
    @Singleton
    @Provides
    fun provideAuthenticationRepository(auth:FirebaseAuth, database: Database): AuthenticationRepository {
        return AuthenticationRepositoryImpl(auth = auth, database = database)
    }
    @Singleton
    @Provides
    fun provideAuthUseCase(repository: AuthenticationRepository) = AuthenticationUseCases(
        firebaseIsUserAuthenticated = FirebaseIsUserAuthenticated(repository = repository),
        firebaseAuthState = FirebaseAuthState(repository = repository),
        firebaseSignOut = FirebaseSignOut(repository = repository),
        firebaseSignIn = FirebaseSignIn(repository = repository),
        firebaseSignUp = FirebaseSignUp(repository = repository),
        getUserId = GetUserId(repository=repository)
    )
    @Singleton
    @Provides
    fun postRepository( database: Database): PostRepository {
        return PostRepositoryImpl( database = database)
    }
    @Singleton
    @Provides
    fun providePostUseCase(repository: PostRepository) = PostUseCases(
        setPostDataOnDatabase = SetPostDataOnDatabase(repository = repository)
    )
}