package com.aliumujib.cryptoapp.currencydatalib.cache.impl

import com.aliumujib.cryptoapp.currencydatalib.cache.mappers.CurrencyCacheMappers
import com.aliumujib.cryptoapp.cache.contract.Store
import com.aliumujib.cryptoapp.cache.currencies.dao.CurrenciesDao
import com.aliumujib.cryptoapp.cache.currencies.models.CurrencyCacheModel
import com.aliumujib.cryptoapp.coremodels.Currency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

typealias CurrencyStore = Store<Currency>

class CurrencyStoreImpl @Inject constructor(
    private val currenciesDao: CurrenciesDao,
    private val currencyMappers: CurrencyCacheMappers
) : CurrencyStore {

    override suspend fun save(items: List<Currency>) {
        currenciesDao.saveCurrencies(items.map(currencyMappers::mapToEntity))
    }

    override fun stream(): Flow<List<Currency>> {
        return currenciesDao.streamCurrencies().map(this::mapWalletsToModel)
    }

    override suspend fun isEmpty(): Boolean {
        return currenciesDao.count() == 0
    }

    private fun mapWalletsToModel(walletList: List<CurrencyCacheModel>): List<Currency> {
        return walletList.map {
            currencyMappers.mapToModel(it)
        }
    }

}