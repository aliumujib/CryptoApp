package com.aliumujib.cryptoapp.coredomain.repository

import com.aliumujib.cryptoapp.coremodels.Currency
import kotlinx.coroutines.flow.Flow

interface CurrenciesRepository {
    fun fetchCurrencies(): Flow<List<Currency>>
}
