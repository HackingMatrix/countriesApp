package com.example.countriesapp

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage

@Composable
@Preview(showBackground = true)
fun CountryListScreenPreview() {
    val mockCountries = listOf(
        Country(name = "Argentina", capital = "Buenos Aires", flag_url = "https://s1.significados.com/foto/bandera-de-argentina-og.jpg"),
        Country(name = "Brasil", capital = "Brasilia", flag_url = "https://upload.wikimedia.org/wikipedia/commons/0/05/Flag_of_Brazil.svg"),
        Country(name = "Chile", capital = "Santiago", flag_url = "https://upload.wikimedia.org/wikipedia/commons/7/71/Flag_of_Chile.svg")
    )

    // Crea el ViewModel y establece los países de prueba
    val viewModel = CountryViewModel()
    viewModel.setCountriesForPreview(mockCountries)

    // Simula un NavController
    val navController = rememberNavController()

    // Llama a la función composable
    CountryListScreen(viewModel = viewModel, navController = navController)
}


@Composable
fun CountryListScreen(viewModel: CountryViewModel, navController: NavController) {
    val countries by viewModel.countries.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCountries()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Destinations.CountryAdd) }) {
                Text("+")
            }
        }
    ) { paddingValues -> // Agregar paddingValues aquí
        Box(modifier = Modifier.padding(paddingValues)) { // Usar el padding de Scaffold aquí
            if (errorMessage != null) {
                Text(text = errorMessage ?: "", color = Color.Red, modifier = Modifier.align(Alignment.Center))
            } else if (countries.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) { // Centrar el indicador de carga
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else {
                LazyColumn {
                    items(countries.size) { index ->
                        val country = countries[index]
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Cargar imagen de bandera usando AsyncImage de Coil
                            AsyncImage(
                                model = country.flag_url,
                                contentDescription = "Bandera de ${country.name}",
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(end = 8.dp),
                                onSuccess = {
                                    Log.d("CountryList", "Imagen cargada exitosamente: ${country.flag_url}")
                                },
                                onError = {
                                    Log.e("CountryList", "Error al cargar la imagen: ${country.flag_url}")
                                }
                            )

                            Column {
                                Text(country.name, style = MaterialTheme.typography.headlineMedium)
                                Text(country.capital, style = MaterialTheme.typography.headlineSmall)
                            }
                        }
                    }
                }
            }
        }
    }
}

