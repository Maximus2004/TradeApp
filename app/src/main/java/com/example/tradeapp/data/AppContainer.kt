package com.example.tradeapp.data

import com.example.tradeapp.network.TradeApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType

interface AppContainer {
    val tradeRepository : TradeRepository
}

class DefaultAppContainer() : AppContainer {
    // основа URl, к которому будем обращаться
    private val BASE_URL =
        "https://finnhub.io/api/v1/"

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    // "ленивая" инициализация API сервиса
    private val retrofitService: TradeApiService by lazy {
        retrofit.create(TradeApiService::class.java)
    }

    // передаём API, с которым будем работать - Dependency Injection (DI)
    override val tradeRepository: TradeRepository = DefaultTradeRepository(retrofitService)
}