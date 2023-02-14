package com.example.tradeapp.data

import androidx.annotation.DrawableRes
import com.example.tradeapp.R
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val ticker: String = "DFLT",

    @SerialName("c")
    val currentPrice: Double,

    @SerialName("dp")
    val change: Double,

    val area: String = "IT",

    @DrawableRes
    val image: Int = R.drawable.loading_img
)
