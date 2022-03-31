package com.aliumujib.cryptoapp.ratedatalib

import com.aliumujib.cryptoapp.coremodels.ExchangeRate
import com.aliumujib.cryptoapp.ratedatalib.cache.impl.RatesStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRatesStore : RatesStore {

    private val rates = mutableListOf<ExchangeRate>()

    override suspend fun fetchRateForPair(
        fromCurrency: String,
        toCurrency: String
    ): ExchangeRate? {
        return rates.firstOrNull {
            it.fromCurrency == fromCurrency && it.toCurrency == toCurrency
        }
    }

    override suspend fun save(items: List<ExchangeRate>) {
        rates.addAll(items)
    }

    override fun stream(): Flow<List<ExchangeRate>> {
        return flowOf(rates)
    }

    override suspend fun isEmpty(): Boolean {
        return rates.isEmpty()
    }
}
