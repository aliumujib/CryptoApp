package com.aliumujib.cryptoapp.currencydatalib.domain

import com.aliumujib.cryptoapp.coremodels.Currency
import kotlinx.coroutines.flow.Flow

interface CurrenciesRepository {

    fun streamCurrencies(): Flow<List<Currency>>

    fun streamBaseFiatCurrencyCode(): Flow<String>

}