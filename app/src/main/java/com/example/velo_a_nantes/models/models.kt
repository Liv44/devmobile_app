package com.example.velo_a_nantes.models

import android.location.Location
import kotlinx.serialization.*

var currentLocation : Location? = null
var stationSelected : Station? = null
var allStations : List<Station>? = null

@Serializable
data class Station (
    val id: Long,
    val name: String,
    val status: String,
    val recordId: String,
    val latitude: Double,
    val longitude: Double,
    val bikeStands: Long,
    val address: String,
    val availableBikes: Long,
    val availableBikeStands: Long
){
    fun toLocation(): Location{
        val location = Location("")
        location.latitude = latitude
        location.longitude = longitude
        return location
    }

    fun showDetails(): CharSequence?{
        return "velos disponibles:${availableBikes} places libres:${availableBikeStands} total:${bikeStands}"
    }
}