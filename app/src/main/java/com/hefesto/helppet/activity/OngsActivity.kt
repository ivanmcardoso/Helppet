package com.hefesto.helppet.activity

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse
import com.hefesto.helppet.R
import com.hefesto.helppet.adapter.OngAdapter
import com.hefesto.helppet.model.Ong
import kotlinx.android.synthetic.main.activity_ongs.*

class OngsActivity : AppCompatActivity() {
    private var ongs: List<Ong> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ongs)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            12
        )

        ongs = createOngs()
        setupAdapter()
    }

    private fun setupAdapter() {
        var latLng: LatLng
        Places.initialize(this, resources.getString(R.string.map_key))
        val placesClient = Places.createClient(this)

        val placeFields: List<Place.Field> =
            listOf(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)


        val request = FindCurrentPlaceRequest.newInstance(placeFields)

        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            pbOngs.visibility = View.VISIBLE
            val placeResponse =
                placesClient.findCurrentPlace(request)
            placeResponse.addOnCompleteListener { task: Task<FindCurrentPlaceResponse?> ->
                if (task.isSuccessful) {
                    val response = task.result
                    response!!.placeLikelihoods.getOrNull(0)?.place?.let { place ->
                        place.latLng?.let {
                            pbOngs.visibility = View.GONE
                            rvOngs.adapter = OngAdapter(ongs, it)
                        }
                    }
                } else {
                    pbOngs.visibility = View.GONE
                }
            }
        }
    }

    private fun createOngs() = mutableListOf(
        Ong(
            "Anjos na Rua",
            -3.097267,
            -60.026103
        )
    )
}