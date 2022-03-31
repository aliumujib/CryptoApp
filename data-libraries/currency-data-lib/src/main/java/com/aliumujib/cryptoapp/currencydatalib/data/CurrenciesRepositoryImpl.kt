package com.aliumujib.cryptoapp.currencydatalib.data

import com.aliumujib.cryptoapp.currencydatalib.cache.impl.CurrencyStore
import com.aliumujib.cryptoapp.currencydatalib.domain.CurrenciesRepository
import com.aliumujib.cryptoapp.currencydatalib.remote.impl.CurrencyDataSource
import com.aliumujib.cryptoapp.coremodels.Currency
import com.aliumujib.cryptoapp.data.flow.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


class CurrenciesRepositoryImpl @Inject constructor(
    private val currencyStore: CurrencyStore,
    private val currencyDataSource: CurrencyDataSource) : CurrenciesRepository {

    override fun streamCurrencies(): Flow<List<Currency>> {
        return networkBoundResource(queryCache = {
            currencyStore.stream()
        }, fetchRemote = {
            currencyDataSource.fetch()
        }, saveFetchResult = { currencies ->
            currencies?.let {
                currencyStore.save(it)
            }
        }, shouldFetch = {
            true
        }, onFetchFailed = {
            throw it
        })
    }

    override fun streamBaseFiatCurrencyCode(): Flow<String> {
        return flowOf("USD")
    }

}