package com.example.smokeforecastvisualizer

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.smokeforecastvisualizer.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.GroundOverlayOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Center the map on Los Angeles
        val losAngeles = LatLng(34.0522, -118.2437)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(losAngeles, 14f))

        // Create a 1x1 pixel bitmap with translucent green
        val bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
        bitmap.setPixel(0, 0, Color.argb(128, 0, 255, 0)) // 128 = 50% transparency

        // Add it to the map using a GroundOverlay
        val overlay = GroundOverlayOptions()
            .image(BitmapDescriptorFactory.fromBitmap(bitmap))
            .position(losAngeles, 200f, 200f) // 20m x 20m area
        map.addGroundOverlay(overlay)
    }
}
