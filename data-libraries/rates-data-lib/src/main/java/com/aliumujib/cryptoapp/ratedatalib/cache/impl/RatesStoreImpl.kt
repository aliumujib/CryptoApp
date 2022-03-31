package com.aliumujib.cryptoapp.ratedatalib.cache.impl

import com.aliumujib.cryptoapp.cache.contract.Store
import com.aliumujib.cryptoapp.cache.rates.dao.ExchangeRatesDao
import com.aliumujib.cryptoapp.cache.rates.models.ExchangeRateCacheModel
import com.aliumujib.cryptoapp.coremodels.ExchangeRate
import com.aliumujib.cryptoapp.ratedatalib.cache.mappers.ExchangeRateCacheMappers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface RatesStore : Store<ExchangeRate> {
   suspend fun fetchRateForPair(fromCurrency: String, toCurrency: String): ExchangeRate?
}

class RatesStoreImpl @Inject constructor(
    private val exchangeRatesDao: ExchangeRatesDao,
    private val rateCacheMappers: ExchangeRateCacheMappers
) : RatesStore {

    override suspend fun fetchRateForPair(fromCurrency: String, toCurrency: String): ExchangeRate? {
        return exchangeRatesDao.fetchExchangeRateForPair(fromCurrency, toCurrency)?.let {
            rateCacheMappers.mapToModel(it)
        }
    }

    override suspend fun save(items: List<ExchangeRate>) {
        exchangeRatesDao.saveExchanges(items.map(rateCacheMappers::mapToEntity))
    }

    override fun stream(): Flow<List<ExchangeRate>> {
        return exchangeRatesDao.streamExchangeRates().map(this::mapExchanges)
    }

    private fun mapExchanges(currencyList: List<ExchangeRateCacheModel>): List<ExchangeRate> {
        return currencyList.map {
            rateCacheMappers.mapToModel(it)
        }
    }

    override suspend fun isEmpty(): Boolean {
        return exchangeRatesDao.count() > 0
    }

}