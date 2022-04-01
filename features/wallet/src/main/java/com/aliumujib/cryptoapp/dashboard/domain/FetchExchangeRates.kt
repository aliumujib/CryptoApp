package com.aliumujib.cryptoapp.dashboard.domain

import com.aliumujib.cryptoapp.coredomain.utils.NoParamsException
import com.aliumujib.cryptoapp.coredomain.utils.PostExecutionThread
import com.aliumujib.cryptoapp.coredomain.utils.SuspendUseCase
import com.aliumujib.cryptoapp.dashboard.presentation.ExchangeRates
import com.aliumujib.cryptoapp.ratedatalib.domain.RatesRepository
import javax.inject.Inject

class FetchExchangeRates @Inject constructor(
    private val ratesDataRepository: RatesRepository,
    postExecutionThread: PostExecutionThread
) : SuspendUseCase<FetchExchangeRates.Params, ExchangeRates>(postExecutionThread) {

    data class Params constructor(
        val baseCoinIds: List<String>,
        val fiatCurrencyCode: String
    ) {
        companion object {
            fun make(baseCoinIds: List<String>, fiatCurrencyCode: String): Params {
                return Params(baseCoinIds, fiatCurrencyCode)
            }
        }
    }

    override suspend fun execute(params: Params?): ExchangeRates {
        if (params == null) {
            throw NoParamsException()
        }

        val result = params.baseCoinIds.fold(mutableMapOf<String, Double>()) { map, coinId ->
            map.apply {
                val exchangeRate = ratesDataRepository.fetchRateForPair(coinId, params.fiatCurrencyCode)
                val averageRate = exchangeRate?.rates?.map { rate -> rate.rate }?.average()
                averageRate?.let {
                    put(coinId, averageRate)
                }
            }
        }
        return result
    }
}
