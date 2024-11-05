package com.example.countriesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val countryViewModel: CountryViewModel = viewModel() // Crear una instancia de CountryViewModel
            NavGraph(navController = navController, viewModel = countryViewModel) // Pasar el ViewModel al NavGraph
        }
    }
}
