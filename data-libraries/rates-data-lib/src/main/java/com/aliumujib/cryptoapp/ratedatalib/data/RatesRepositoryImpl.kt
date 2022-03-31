package com.aliumujib.cryptoapp.ratedatalib.data

import com.aliumujib.cryptoapp.coremodels.ExchangeRate
import com.aliumujib.cryptoapp.data.flow.networkBoundResource
import com.aliumujib.cryptoapp.ratedatalib.cache.impl.RatesStore
import com.aliumujib.cryptoapp.ratedatalib.domain.RatesRepository
import com.aliumujib.cryptoapp.ratedatalib.remote.impl.RatesDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class RatesRepositoryImpl @Inject constructor(
    private val ratesStore: RatesStore,
    private val ratesDataSource: RatesDataSource
) : RatesRepository {

    override suspend fun fetchRateForPair(from: String, to: String): ExchangeRate? {
        return networkBoundResource(queryCache = {
            flow {
                emit(ratesStore.fetchRateForPair(from, to))
            }
        }, fetchRemote = {
            ratesDataSource.fetch()
        }, saveFetchResult = { rates ->
            rates?.let {
                ratesStore.save(it)
            }
        }, shouldFetch = {
            true
        }, onFetchFailed = {
            throw it
        }).first()
    }

}