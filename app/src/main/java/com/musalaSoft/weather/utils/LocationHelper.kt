package com.musalaSoft.weather.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.location.LocationManager
import android.content.pm.PackageManager
import android.os.Bundle
import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.musalaSoft.weather.db.entity.CoordEntity

class LocationHelper {

    private val LOCATION_REFRESH_TIME = 3000 // 3 seconds. The Minimum Time to get location update
    private val LOCATION_REFRESH_DISTANCE = 30 // 30 meters. The Minimum Distance to be changed to get location update
    private val PERMISSIONS_REQUEST_LOCATION = 100

    var locationListener: LocationListener? = null

    interface LocationListener {
        fun onLocationEnabled(location: Location)
    }

    fun startListeningUserLocation(context: Context, listener: LocationListener) {
        locationListener = listener

        val mLocationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager

        val mLocationListener = object : android.location.LocationListener {
            override fun onLocationChanged(location: Location) {
                locationListener!!.onLocationEnabled(location) // calling listener to inform that updated location is available
            }
            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {

            }
            override fun onProviderDisabled(provider: String) {}
        }
        // check for permissions
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME.toLong(),LOCATION_REFRESH_DISTANCE.toFloat(), mLocationListener)
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, Manifest.permission.ACCESS_FINE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // permission is denined by user, you can show your alert dialog here to send user to App settings to enable permission
            } else {
                ActivityCompat.requestPermissions(context,arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),PERMISSIONS_REQUEST_LOCATION)

            }
        }
    }

}