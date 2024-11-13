package com.example.cryptoapp.model

data class Model(
    val name: String,
    val symbol: String,
    val price: Double,
    val changePercent: Double,
    val lineData: ArrayList<Int>,
    val propertyAmount: Double,
    val propertySize: Double
)
