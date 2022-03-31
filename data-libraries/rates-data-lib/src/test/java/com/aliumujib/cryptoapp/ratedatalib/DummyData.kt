package com.aliumujib.cryptoapp.ratedatalib

import com.aliumujib.cryptoapp.coremodels.ExchangeRate
import konveyor.base.randomBuild
import kotlin.random.Random

object DummyData {

    fun generateExchangeRate(from: String, to: String): ExchangeRate {
        return ExchangeRate(
            from,
            rates = listOf(randomBuild(), randomBuild()),
            Random.nextLong(),
            to
        )
    }

    fun generateFakeExchangeList(): List<ExchangeRate> {
        return listOf(
            generateExchangeRate("BTC", "USD"),
            generateExchangeRate("BUSD", "USD"),
            generateExchangeRate("USDT", "USD"),
            generateExchangeRate("BNB", "USD")
        )
    }
}
