package com.aliumujib.cryptoapp.dashboard.presentation.model

import com.aliumujib.cryptoapp.coremodels.Currency

data class WalletUIModel(
    val coinId: String,
    val currency: Currency?,
    val coinAmount: Double,
    val fiatAmount: Double,
    val fiatCurrencyCode: String?
)
