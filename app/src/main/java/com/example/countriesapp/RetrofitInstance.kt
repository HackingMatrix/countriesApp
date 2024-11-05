package com.example.countriesapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://countrieshugo.netlify.app/" // URL base de tu API

    val api: CountryApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Conversor para JSON
            .build()
            .create(CountryApiService::class.java)
    }
}
