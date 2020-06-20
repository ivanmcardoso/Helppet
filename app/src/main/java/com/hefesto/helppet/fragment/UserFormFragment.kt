package com.hefesto.helppet.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.UserProfileChangeRequest
import com.hefesto.helppet.R
import com.hefesto.helppet.config.FirebaseConfig
import com.hefesto.helppet.utils.isFilled
import com.hefesto.helppet.utils.shortToast
import kotlinx.android.synthetic.main.fragment_perfil.*
import kotlinx.android.synthetic.main.fragment_user_form.*
import kotlinx.android.synthetic.main.list_item_post.*
import java.io.ByteArrayOutputStream
import java.net.URI

class UserFormFragment : Fragment() {
    private var image: ByteArray = byteArrayOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_user_form, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val packageManager = activity!!.packageManager

        ivUserFormProfilePhoto.setOnClickListener {

            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
        btUserFormSubmit.setOnClickListener {
            if (tiUserFormUserName.text.isFilled && tiUserFormPassword.text.isFilled && image.isNotEmpty()) {
                FirebaseConfig.getFireBaseAuth()
                    .createUserWithEmailAndPassword(
                        tiUserFormUserName.text.toString(),
                        tiUserFormPassword.text.toString()
                    ).apply {
                        addOnSuccessListener(activity!!) {
                            FirebaseConfig.getFireBaseStorage()
                                .child("images")
                                .child("profile")
                                .child("${tiUserFormUserName.text.toString()}.jpeg")
                                .apply {
                                    putBytes(image).also {
                                        addOnSuccessListener {
                                            downloadUrl.apply {
                                                addOnSuccessListener {photoUrl ->
                                                    FirebaseConfig.updateUser(
                                                        tiUserFormDescription.text.toString(),
                                                        photoUrl
                                                    )?.also {
                                                        addOnSuccessListener {
                                                            shortToast("Sucesso")
                                                            fragmentManager?.beginTransaction()
                                                                ?.replace(
                                                                    R.id.flLogin,
                                                                    LoginFragment()
                                                                )
                                                                ?.commit()
                                                        }
                                                        addOnFailureListener { shortToast("falha") }
                                                    }
                                                }
                                                addOnFailureListener { shortToast("falha") }
                                            }
                                        }

                                        addOnFailureListener { shortToast("falha") }
                                    }
                                }
                        }
                        addOnFailureListener { shortToast("falha") }

                    }

            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val byteArrayOutputStream = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            image = byteArrayOutputStream.toByteArray()

            ivUserFormProfilePhoto.setImageBitmap(imageBitmap)
        }
    }

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
    }
}