package com.example.countriesapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
@Preview(showBackground = true)
fun CountryAddScreenPreview() {
    var name by remember { mutableStateOf("") }
    var capital by remember { mutableStateOf("") }
    var flagUrl by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(value = name, onValueChange = { name = it }, label = { Text("Nombre del País") })
        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = capital, onValueChange = { capital = it }, label = { Text("Capital") })
        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = flagUrl, onValueChange = { flagUrl = it }, label = { Text("URL de la Bandera") })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Acción al guardar el país (aquí se deja vacío ya que es un preview)
        }) {
            Text("Guardar")
        }
    }
}


@Composable
fun CountryAddScreen(viewModel: CountryViewModel, navController: NavController) {
    var name by remember { mutableStateOf("") }
    var capital by remember { mutableStateOf("") }
    var flagUrl by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(value = name, onValueChange = { name = it }, label = { Text("Nombre del País") })
        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = capital, onValueChange = { capital = it }, label = { Text("Capital") })
        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = flagUrl, onValueChange = { flagUrl = it }, label = { Text("URL de la Bandera") })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val newCountry = Country(name, capital, flagUrl)
            viewModel.addCountry(newCountry) {
                navController.navigate(Destinations.CountryList)
            }
        }) {
            Text("Guardar")
        }
    }
}
