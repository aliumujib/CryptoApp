package com.aliumujib.cryptoapp.coremodels

data class ExchangeRate(
    val fromCurrency: String,
    val rates: List<Rate>,
    val timeStamp: Long,
    val toCurrency: String
)

data class Rate(
    val amount: Double,
    val rate: Double
)
