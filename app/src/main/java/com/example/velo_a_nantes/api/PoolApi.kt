package com.example.velo_a_nantes.api

import com.example.velo_a_nantes.models.Pool
import retrofit2.Response
import retrofit2.http.GET

interface PoolApi {

    @GET("api/pools")
    suspend fun getPools(): Response<List<Pool>>
}