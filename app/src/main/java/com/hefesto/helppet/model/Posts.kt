package com.hefesto.helppet.model

import com.google.firebase.database.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
data class Posts (
    val name: String = "",
    val time: Date? = null,
    val petPhotoUrl: String = "",
    val userphotoUrl: String = "",
    val description: String = "",
    var lat: Double = 0.0,
    val lng: Double = 0.0){
}