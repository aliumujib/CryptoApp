package com.aliumujib.cryptoapp.sharedtestutils

import com.aliumujib.cryptoapp.coremodels.ExchangeRate
import com.aliumujib.cryptoapp.coremodels.Rate
import konveyor.base.randomBuild
import kotlin.random.Random

object RatesDummyData {

    fun generateExchangeRate(from: String, to: String, rate: Double = 1.0): ExchangeRate {
        return ExchangeRate(
            from,
            rates = listOf(
                randomBuild<Rate>().copy(rate = rate),
                randomBuild<Rate>().copy(rate = rate),
                randomBuild<Rate>().copy(rate = rate),
                randomBuild<Rate>().copy(rate = rate)
            ),
            Random.nextLong(),
            to
        )
    }

    fun generateFakeExchangeRateList(pairList: List<Triple<String, String, Double>>): List<ExchangeRate> {
        return pairList.map {
            generateExchangeRate(it.first, it.second, it.third)
        }
    }


}
