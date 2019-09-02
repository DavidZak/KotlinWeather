package com.mradmin.yks13.kotlinweather.service

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.*
import com.mradmin.yks13.kotlinweather.base.Service
import com.mradmin.yks13.kotlinweather.util.PermissionUtils
import java.io.IOException
import java.lang.IllegalArgumentException

interface GeolocationServiceListener {
    fun onLocation(location: Location)
}

class GeolocationService: Service() {

    private val updateIntervalInMilliseconds: Long = 5000

    lateinit var client: FusedLocationProviderClient
    lateinit var locationCallback: LocationCallback
    val locationRequest: LocationRequest = LocationRequest()

    fun <T: GeolocationServiceListener> getCurrentLocation(context: Context, listener: T) {
        val client: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        startLocationUpdates(client, listener)
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates(client: FusedLocationProviderClient, listener: GeolocationServiceListener) {
        //val locationRequest = LocationRequest()
        setLocationRequest(locationRequest)
        locationCallback = setLocationCallback(listener)
        client.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
    }

    fun stopLocationUpdates(context: Context) {
        if (!PermissionUtils.hasLocationPermission(context))
            return

        client.removeLocationUpdates(locationCallback)
    }

    private fun setLocationRequest(locationRequest: LocationRequest) {
        locationRequest.interval = updateIntervalInMilliseconds
        locationRequest.fastestInterval = updateIntervalInMilliseconds
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    private fun setLocationCallback(listener: GeolocationServiceListener): LocationCallback {
        return object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                if (locationResult != null) {
                    val location = locationResult.lastLocation
                    dispatchNewLocation(location, listener)
                }
            }
        }
    }

    private fun dispatchNewLocation(lastLocation: Location, listener: GeolocationServiceListener) {
        Log.e("___ new location", "$lastLocation")
        listener.onLocation(lastLocation)
    }

    fun getLocationLocale(context: Context, location: Location): String {
        return getLocationLocale(context, location.latitude, location.longitude)
    }

    fun getLocationLocale(context: Context, latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(context)

        var addresses: List<Address> = emptyList()

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 10)
        } catch (ioException: IOException) {
            return ""
        } catch (illegalArgumentException: IllegalArgumentException) {
            return ""
        }

        if (addresses.isNotEmpty()) {
            val address = addresses[0]
            if (address.locality != null)
                return address.locality
            else if (address.countryName != null)
                return address.countryName
        }

        return ""
    }

}