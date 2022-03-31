package com.aliumujib.cryptoapp.ratedatalib.domain

import com.aliumujib.cryptoapp.coremodels.ExchangeRate

interface RatesRepository {

    suspend fun fetchRateForPair(from: String, to: String): ExchangeRate?
}
