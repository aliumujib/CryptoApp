package com.aliumujib.cryptoapp.dashboard.domain

import com.aliumujib.cryptoapp.coremodels.ExchangeRate
import com.aliumujib.cryptoapp.ratedatalib.domain.RatesRepository
import java.net.SocketTimeoutException

class FakeRatesRepository(private val rates: List<ExchangeRate>) : RatesRepository {

    var responseType: ResponseType = ResponseType.DATA

    override suspend fun fetchRateForPair(from: String, to: String): ExchangeRate? {
        return makeResponse(responseType, from, to)
    }

    private fun makeResponse(type: ResponseType, from: String, to: String): ExchangeRate? {
        return when (type) {
            ResponseType.DATA -> {
                return rates.firstOrNull {
                    it.fromCurrency == from && it.toCurrency == to
                }
            }
            ResponseType.EMPTY -> {
                null
            }
            ResponseType.ERROR -> throw SocketTimeoutException("An error occurred")
        }
    }
}

enum class ResponseType {
    DATA,
    EMPTY,
    ERROR
}