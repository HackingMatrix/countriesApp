package com.example.countriesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class CountryViewModel : ViewModel() {
    private val repository = CountryRepository()

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> get() = _countries

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun setCountriesForPreview(countries: List<Country>) {
        _countries.value = countries
    }
    // Método para cargar la lista de países desde la API
    fun loadCountries() {
        viewModelScope.launch {
            try {
                val countryList = repository.getCountries()
                _countries.value = countryList
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar países: ${e.message}"
            }
        }
    }

    // Método para añadir un nuevo país a la API
    fun addCountry(country: Country, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.addCountry(country)
                if (response.isSuccessful) {
                    onSuccess()
                    loadCountries() // Recargar la lista después de añadir
                } else {
                    _errorMessage.value = "Error al agregar país"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al agregar país: ${e.message}"
            }
        }
    }
}
