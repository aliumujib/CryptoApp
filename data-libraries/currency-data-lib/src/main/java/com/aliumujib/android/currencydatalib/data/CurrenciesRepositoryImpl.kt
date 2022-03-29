package com.aliumujib.android.currencydatalib.data

import com.aliumujib.android.currencydatalib.cache.impl.CurrencyStore
import com.aliumujib.android.currencydatalib.domain.CurrenciesRepository
import com.aliumujib.android.currencydatalib.domain.HighSpeedCurrencyCache
import com.aliumujib.android.currencydatalib.remote.impl.CurrencyDataSource
import com.aliumujib.cryptoapp.coremodels.Currency
import com.aliumujib.cryptoapp.data.flow.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class CurrenciesRepositoryImpl @Inject constructor(
    private val currencyStore: CurrencyStore,
    private val currencyDataSource: CurrencyDataSource,
    private val currencyCache: HighSpeedCurrencyCache
) : CurrenciesRepository {

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
        }).onEach {
            it.forEach { currency ->
                currencyCache[currency.coinId] = currency
            }
        }
    }

    override fun getCurrency(currencyId: String): Currency? {
        return currencyCache[currencyId]
    }

}