package com.aliumujib.cryptoapp.coremodels

data class ExchangeRate(
    val from_currency: String,
    val rate: Rate,
    val time_stamp: Int,
    val to_currency: String
)

data class Rate(
    val amount: String,
    val rate: String
)