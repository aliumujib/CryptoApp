package com.aliumujib.android.currencydatalib.domain

import com.aliumujib.cryptoapp.coremodels.Currency
import kotlinx.coroutines.flow.Flow

typealias HighSpeedCurrencyCache = HashMap<String, Currency>

interface CurrenciesRepository {

    fun streamCurrencies(): Flow<List<Currency>>

    fun getCurrency(currencyId: String) : Currency?
}