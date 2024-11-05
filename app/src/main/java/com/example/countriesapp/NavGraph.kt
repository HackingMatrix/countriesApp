package com.example.countriesapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController, viewModel: CountryViewModel = viewModel()) {
    NavHost(navController, startDestination = Destinations.CountryList) {
        composable(Destinations.CountryList) {
            CountryListScreen(viewModel = viewModel, navController = navController)
        }
        composable(Destinations.CountryAdd) {
            CountryAddScreen(viewModel = viewModel, navController = navController)
        }
    }
}

object Destinations {
    const val CountryList = "country_list"
    const val CountryAdd = "country_add"
}
