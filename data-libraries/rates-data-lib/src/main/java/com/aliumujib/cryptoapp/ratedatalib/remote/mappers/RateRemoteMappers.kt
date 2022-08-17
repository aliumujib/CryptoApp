package com.aliumujib.cryptoapp.ratedatalib.remote.mappers

import com.aliumujib.cryptoapp.coremodels.ExchangeRate
import com.aliumujib.cryptoapp.coremodels.Rate
import com.aliumujib.cryptoapp.ratedatalib.remote.model.ExchangeRateResponse
import com.aliumujib.cryptoapp.remote.mapper.RemoteModelMapper
import javax.inject.Inject

class RateRemoteMappers @Inject constructor() :
    RemoteModelMapper<ExchangeRate, ExchangeRateResponse> {

    override fun mapToModel(response: ExchangeRateResponse): ExchangeRate {
        return with(response) {
            ExchangeRate(
                fromCurrency,
                rates.map {
                    Rate(it.amount.toDouble(), it.rate.toDouble())
                },
                timeStamp,
                toCurrency
            )
        }
    }
}
