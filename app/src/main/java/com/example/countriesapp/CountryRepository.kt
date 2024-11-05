package com.example.countriesapp

import retrofit2.Response

class CountryRepository {
    private val apiService = RetrofitInstance.api // Instancia de la API

    suspend fun getCountries(): List<Country> {
        return apiService.getCountries() // Llama a la API para obtener la lista de países
    }

    suspend fun addCountry(country: Country): Response<Unit> {
        return apiService.addCountry(country) // Llama a la API para agregar un país
    }
}
