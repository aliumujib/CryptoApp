package com.aliumujib.cryptoapp.ratedatalib.cache.mappers

import com.aliumujib.cryptoapp.cache.mapper.CacheModelMapper
import com.aliumujib.cryptoapp.cache.rates.models.ExchangeRateCacheModel
import com.aliumujib.cryptoapp.cache.rates.models.RateCacheModel
import com.aliumujib.cryptoapp.coremodels.ExchangeRate
import com.aliumujib.cryptoapp.coremodels.Rate
import javax.inject.Inject

class ExchangeRateCacheMappers @Inject constructor() :
    CacheModelMapper<ExchangeRate, ExchangeRateCacheModel> {

    override fun mapToModel(entity: ExchangeRateCacheModel): ExchangeRate {
        return with(entity) {
            ExchangeRate(
                fromCurrency, rates.map {
                    Rate(it.amount, it.rate)
                }, timeStamp, toCurrency
            )
        }
    }

    override fun mapToEntity(model: ExchangeRate): ExchangeRateCacheModel {
        return with(model) {
            ExchangeRateCacheModel(
                "$fromCurrency/$toCurrency", fromCurrency, rates.map {
                    RateCacheModel(it.amount, it.rate)
                },
                timeStamp, toCurrency
            )
        }
    }
}
