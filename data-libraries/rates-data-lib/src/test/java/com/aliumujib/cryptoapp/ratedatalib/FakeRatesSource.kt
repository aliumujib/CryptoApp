package com.aliumujib.cryptoapp.ratedatalib

import com.aliumujib.cryptoapp.coremodels.ExchangeRate
import com.aliumujib.cryptoapp.ratedatalib.remote.impl.RatesDataSource

class FakeRatesSource(private val rates: List<ExchangeRate>) : RatesDataSource {

    override fun fetch(): List<ExchangeRate> {
        return rates
    }

}