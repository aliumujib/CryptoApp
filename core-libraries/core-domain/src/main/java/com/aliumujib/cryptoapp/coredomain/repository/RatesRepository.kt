package com.aliumujib.cryptoapp.coredomain.repository

import com.aliumujib.cryptoapp.coremodels.ExchangeRate
import kotlinx.coroutines.flow.Flow

interface RatesRepository {
    fun fetchCurrencies(): Flow<List<ExchangeRate>>
}
