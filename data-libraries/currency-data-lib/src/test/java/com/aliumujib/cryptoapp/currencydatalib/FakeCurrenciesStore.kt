package com.aliumujib.cryptoapp.currencydatalib

import com.aliumujib.cryptoapp.coremodels.Currency
import com.aliumujib.cryptoapp.currencydatalib.cache.impl.CurrencyStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCurrenciesStore : CurrencyStore {

    private val currencies = mutableListOf<Currency>()

    override suspend fun save(items: List<Currency>) {
        currencies.addAll(items)
    }

    override fun stream(): Flow<List<Currency>> {
        return flowOf(currencies)
    }

    override suspend fun isEmpty(): Boolean {
        return currencies.isEmpty()
    }

}