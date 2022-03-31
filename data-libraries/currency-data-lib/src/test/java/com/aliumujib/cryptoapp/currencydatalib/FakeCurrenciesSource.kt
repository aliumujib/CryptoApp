package com.aliumujib.cryptoapp.currencydatalib

import com.aliumujib.cryptoapp.coremodels.Currency
import com.aliumujib.cryptoapp.currencydatalib.remote.impl.CurrencyDataSource

class FakeCurrenciesSource(private val currencies: List<Currency>) : CurrencyDataSource {

    override fun fetch(): List<Currency> {
        return currencies
    }
}
