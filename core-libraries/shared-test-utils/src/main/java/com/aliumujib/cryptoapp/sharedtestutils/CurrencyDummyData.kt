package com.aliumujib.cryptoapp.sharedtestutils

import com.aliumujib.cryptoapp.coremodels.Currency
import konveyor.base.randomBuild
import kotlin.random.Random

object CurrencyDummyData {

    fun generateFakeCurrency(coinId: String): Currency {
        return Currency(
            coinId,
            coinId,
            coinId,
            randomBuild(),
            randomBuild(),
            randomBuild(),
            randomBuild(),
            Random.nextInt(),
            randomBuild(),
            Random.nextInt(),
            randomBuild(),
            randomBuild(),
            randomBuild(),
            Random.nextInt(),
            randomBuild(),
            Random.nextInt(),
            randomBuild(),
            randomBuild(),
            Random.nextInt(),
            randomBuild(),
            randomBuild(),
            listOf(randomBuild(), randomBuild())
        )
    }

    fun generateFakeCurrencyList(): List<Currency> {
        return listOf(
            generateFakeCurrency("BTC"),
            generateFakeCurrency("BUSD"),
            generateFakeCurrency("USDT"),
            generateFakeCurrency("BNB")
        )
    }
}
