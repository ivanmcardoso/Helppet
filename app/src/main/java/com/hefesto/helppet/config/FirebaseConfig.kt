package com.hefesto.helppet.config

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

object FirebaseConfig {
    fun getFireBaseStorage (): StorageReference {
        return FirebaseStorage.getInstance().reference
    }

    fun getFireBaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    fun getCurrentUser(): FirebaseUser? {
        return getFireBaseAuth().currentUser
    }

    fun updateUser(uri: Uri): Task<Void>? {
        UserProfileChangeRequest.Builder().setPhotoUri(uri).build()
            .also { return getCurrentUser()?.updateProfile(it) }
    }

    fun updateUser(name: String, uri: Uri): Task<Void>? {
        UserProfileChangeRequest.Builder().setDisplayName(name).setPhotoUri(uri).build()
            .also { return getCurrentUser()?.updateProfile(it) }
    }

    fun updateUser(name: String): Task<Void>? {
        UserProfileChangeRequest.Builder().setDisplayName(name).build()
            .also { return getCurrentUser()?.updateProfile(it) }
    }
}