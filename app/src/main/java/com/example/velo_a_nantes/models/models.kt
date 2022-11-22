package com.example.velo_a_nantes.models

import android.location.Location
import kotlinx.serialization.*

var currentLocation : Location? = null
var stationSelected : Station? = null
var allStations : List<Station>? = null
var poolSelected: Pool? = null
var allPools : List<Pool>? = null

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

@Serializable
data class Pool (
    val id: Long,
    val nomComplet: String,
    val latitude: Double,
    val longitude: Double,
    val web: String,
    val adresse: String,
    val tel: String,
    val commune: String,
    val cp: String,

    val bassinLoisir: String,
    val bassinSportif: String,
    val bassinApprentissage: String,
    val pataugeoire: String,
    val plongeoir: String,
    val toboggan: String,

    val libreService: String,
    val accesTransportsCommun: String,
    val accessibiliteHandicap: String,
    val accesPmrEquipt: String,

){
    fun toLocation(): Location{
        val location = Location("")
        location.latitude = latitude
        location.longitude = longitude
        return location
    }
}