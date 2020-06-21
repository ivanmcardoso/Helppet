package com.hefesto.helppet.model

import android.net.Uri
import com.google.android.gms.maps.model.LatLng
import java.util.*

class Post (
    val name: String,
    val time: Date,
    val petPhotoUrl: Uri,
    val userphotoUrl: Uri,
    val description: String,
    var latLng: LatLng)