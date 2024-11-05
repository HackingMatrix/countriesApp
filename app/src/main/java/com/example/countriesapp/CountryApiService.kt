package com.example.countriesapp

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CountryApiService {
    @GET("countries")
    suspend fun getCountries(): List<Country>

    @POST("countries")
    suspend fun addCountry(@Body country: Country): Response<Unit>
}
