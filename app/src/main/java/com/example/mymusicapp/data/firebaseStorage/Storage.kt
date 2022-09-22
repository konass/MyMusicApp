package com.example.mymusicapp.data.firebaseStorage

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface Storage {

    suspend fun uploadImage(image: Uri) : String

}


class StorageByFireBase  @Inject constructor(
    private var storageRef: StorageReference,
    private var auth: FirebaseAuth

) : Storage {

    override suspend fun uploadImage(image: Uri): String {
        val imageRef = storageRef.child("images/${auth.currentUser?.uid!!}")
        val result = imageRef.putFile(image).await()
        var uriTask = result.storage.downloadUrl
        while (!uriTask.isSuccessful);
        return uriTask.result.toString()
    }



}
