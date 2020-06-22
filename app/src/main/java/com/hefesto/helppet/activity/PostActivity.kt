package com.hefesto.helppet.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.hefesto.helppet.R
import com.hefesto.helppet.config.FirebaseConfig
import com.hefesto.helppet.fragment.UserFormFragment
import com.hefesto.helppet.model.Posts
import com.hefesto.helppet.utils.isFilled
import com.hefesto.helppet.utils.shortToast
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.fragment_user_form.*
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.util.*

class PostActivity : AppCompatActivity() {

    private lateinit var place: Place
    private var image: ByteArray = byteArrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        ivPostPetPhoto.setOnClickListener { openCamera() }
        tiPostLocation.setOnClickListener { openPlacesSort() }

        btPostDone.setOnClickListener { submit() }
    }

    private fun submit() {
        if (tiPostDescription.text.isFilled && image.isNotEmpty() && tiPostLocation.text.isFilled){
            val time = Date()
            pbPost.visibility = View.VISIBLE
            FirebaseConfig.getFireBaseStorage()
                .child("images")
                .child("posts")
                .child("${time.time}.jpeg")
                .apply {
                    putBytes(image).apply {
                        addOnSuccessListener { downloadUrl.apply {
                            addOnSuccessListener { photoUrl->
                                photoUrl?.toString()?.let {
                                    saveData(it, tiPostDescription.text.toString(), time )
                                }
                            }
                            addOnFailureListener { failure(it) }
                        } }
                        addOnFailureListener { failure(it) }
                    }}
                .also {
                    finish()
                }
        }
    }

    private fun openPlacesSort() {
        Places.initialize(this, resources.getString(R.string.map_key))
        val fields = listOf(Place.Field.ADDRESS, Place.Field.LAT_LNG)
        Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(this)
            .also { startActivityForResult(it,
                REQUEST_LOCATION
            ) }
    }

    private fun openCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(
                    takePictureIntent,
                    UserFormFragment.REQUEST_IMAGE_CAPTURE
                )
            }
        }
    }

    fun saveData(photoUrl: String, description: String, time: Date) {
        val posts = Posts(
            FirebaseConfig.getCurrentUser()?.displayName.toString(),
            time,
            photoUrl,
            FirebaseConfig.getCurrentUser()?.photoUrl.toString(),
            description,
            place.latLng!!.latitude,
            place.latLng!!.longitude
        )

        FirebaseConfig.getDatabase().child("Post").child(posts.time!!.time.toString()).setValue(posts)
    }

    private fun failure(exception: Exception) {
        pbPost.visibility = View.GONE
        shortToast("falha:${exception.message}")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if( resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
                    image = byteArrayOutputStream.toByteArray()
                    ivPostPetPhoto.setImageBitmap(imageBitmap)
                }

                REQUEST_LOCATION -> {
                    data?.let {
                        place = Autocomplete.getPlaceFromIntent(it)
                        tiPostLocation.setText(place.address)
                    }
                }
            }
        }
    }

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
        const val REQUEST_LOCATION = 2
    }
}