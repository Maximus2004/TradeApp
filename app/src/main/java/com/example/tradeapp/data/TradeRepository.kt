package com.example.tradeapp.data

import com.example.tradeapp.R
import com.example.tradeapp.network.TradeApiService

interface TradeRepository {
    suspend fun getStocks(): List<Item>
}

// инициализация конкретных функций происходит через конкретный интерфейс, который в свою очередь реализуется с помощью API
// также передаём сюда экземпляр того API, с которым планируем работать
class DefaultTradeRepository(private val tradeApiService: TradeApiService) : TradeRepository {
    override suspend fun getStocks(): List<Item> {
        return listOf(
            tradeApiService.getStockAdobe().copy(ticker = "ADBE", image = R.drawable.adobe),
            tradeApiService.getStockAmd().copy(ticker = "AMD", image = R.drawable.amd),
            tradeApiService.getStockAmazon().copy(ticker = "AMZN", image = R.drawable.amazon),
            tradeApiService.getStockApple().copy(ticker = "AAPL", image = R.drawable.apple),
            tradeApiService.getStockDolby().copy(ticker = "DLB", image = R.drawable.dolby),
            tradeApiService.getStockAirbnb().copy(ticker = "ABNB", image = R.drawable.airbnb),
            tradeApiService.getStockDropbox().copy(ticker = "DPX", image = R.drawable.dropbox),
            tradeApiService.getStockHP().copy(ticker = "HPQ", image = R.drawable.hp),
            tradeApiService.getStockIBM().copy(ticker = "IBM", image = R.drawable.ibm),
            tradeApiService.getStockGitlab().copy(ticker = "GTLB", image = R.drawable.gitlab)
        )
    }
}