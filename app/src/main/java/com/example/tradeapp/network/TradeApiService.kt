package com.example.tradeapp.network

import com.example.tradeapp.data.Item
import retrofit2.http.GET

// правила того, как Retrofit будет общаться с Web Server
interface TradeApiService {
    @GET("quote?symbol=AAPL&token=cflkfk9r01qqm9m4hilgcflkfk9r01qqm9m4him0")
    suspend fun getStockApple(): Item

    @GET("quote?symbol=AMZN&token=cflkfk9r01qqm9m4hilgcflkfk9r01qqm9m4him0")
    suspend fun getStockAmazon(): Item

    @GET("quote?symbol=ADBE&token=cflkfk9r01qqm9m4hilgcflkfk9r01qqm9m4him0")
    suspend fun getStockAdobe(): Item

    @GET("quote?symbol=ABNB&token=cflkfk9r01qqm9m4hilgcflkfk9r01qqm9m4him0")
    suspend fun getStockAirbnb(): Item

    @GET("quote?symbol=AMD&token=cflkfk9r01qqm9m4hilgcflkfk9r01qqm9m4him0")
    suspend fun getStockAmd(): Item

    @GET("quote?symbol=DLB&token=cflkfk9r01qqm9m4hilgcflkfk9r01qqm9m4him0")
    suspend fun getStockDolby(): Item

    @GET("quote?symbol=DBX&token=cflkfk9r01qqm9m4hilgcflkfk9r01qqm9m4him0")
    suspend fun getStockDropbox(): Item

    @GET("quote?symbol=GTLB&token=cflkfk9r01qqm9m4hilgcflkfk9r01qqm9m4him0")
    suspend fun getStockGitlab(): Item

    @GET("quote?symbol=HPQ&token=cflkfk9r01qqm9m4hilgcflkfk9r01qqm9m4him0")
    suspend fun getStockHP(): Item

    @GET("quote?symbol=IBM&token=cflkfk9r01qqm9m4hilgcflkfk9r01qqm9m4him0")
    suspend fun getStockIBM(): Item
}