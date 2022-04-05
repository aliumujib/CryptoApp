package com.aliumujib.cryptoapp.coremodels

typealias BaseFiatCurrency = String

typealias ExchangeRates = Map<String, Double>

data class WalletsWithExchangeRates(
    val wallets: List<Wallet>,
    val exchangeRates: ExchangeRates,
    val baseFiatCurrency: BaseFiatCurrency
)