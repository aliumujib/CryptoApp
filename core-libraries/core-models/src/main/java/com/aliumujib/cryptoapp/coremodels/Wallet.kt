package com.aliumujib.cryptoapp.coremodels

data class Wallet(
    val currencyCode: String,
    val currency: Currency?,
    val amount: Double
)
