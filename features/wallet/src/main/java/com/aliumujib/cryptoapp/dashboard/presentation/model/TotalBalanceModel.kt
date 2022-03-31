package com.aliumujib.cryptoapp.dashboard.presentation.model

import com.aliumujib.cryptoapp.coremodels.Currency

data class TotalBalanceModel(
    val currencyCode: String,
    val currency: Currency?,
    val coinAmount: Double,
    val fiatAmount: Double,
    val fiatCurrency: String?
)
