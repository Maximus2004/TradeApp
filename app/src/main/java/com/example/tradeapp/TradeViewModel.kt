package com.example.tradeapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tradeapp.data.Item
import com.example.tradeapp.data.TradeRepository
import kotlinx.coroutines.launch
import java.io.IOException

class TradeViewModel(private val tradeRepository: TradeRepository) : ViewModel() {

    // The mutable State that stores the status of the most recent request
    // Придаём три возможных состояния: загрузка, успех, крах
    var tradeUiState: TradeUiState by mutableStateOf(TradeUiState.Loading)
        private set

    //Call getStock() on init so we can display status immediately
    init {
        getStock()
    }

    // Gets Mars photos information from the Mars API
    fun getStock() {
        // scope, в которой запускаются функции ViewModel
        // если ViewModel canceled, то весь viewModelScope тоже cancelled
        viewModelScope.launch {
            // чтобы приложение не крашилось из-за всякой фигни
            try {
                // получаем данные при помощи репозитория в соответсвии с хорошими практиками
                val result = tradeRepository.getStocks()
                // он сам понимает, что нужно получить все данные в формате JSON с файла
                tradeUiState = TradeUiState.Success(result)
            } catch (e: IOException) {
                tradeUiState = TradeUiState.Error
            }
        }
    }

    // позволяет во время создания ViewModel передать данные в конструктор
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // APPLICATION_KEY помогает найти MarsPhotosApplication, который содержит container
                val application = (this[APPLICATION_KEY] as TradeApplication)
                // container содержит marsPhotosRepository, который нужен MarsViewModel для реализации Dependency Injection
                val tradeRepository = application.container.tradeRepository
                // наконец-то передаём repository во ViewModel
                TradeViewModel(tradeRepository = tradeRepository)
            }
        }
    }
}

// что-то по типу data класса, но более расширяемый, может содержать объекты своего типа,
// тоже содержит определённый константный набор возможных значений
// data class обязательно должен иметь конструктор, этой структуре он не нужен
sealed interface TradeUiState {
    data class Success(val stocks: List<Item>) : TradeUiState
    object Error : TradeUiState
    object Loading : TradeUiState
}