package com.aliumujib.cryptoapp.dashboard.domain

import com.aliumujib.cryptoapp.coredomain.utils.FlowUseCase
import com.aliumujib.cryptoapp.coredomain.utils.PostExecutionThread
import com.aliumujib.cryptoapp.coremodels.BaseFiatCurrency
import com.aliumujib.cryptoapp.coremodels.Wallet
import com.aliumujib.cryptoapp.coremodels.WalletsWithExchangeRates
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class StreamWalletsAndExchangeRates @Inject constructor(
    private val streamWallets: StreamWallets,
    private val fetchExchangeRates: FetchExchangeRates,
    private val streamBaseFiat: StreamBaseFiat,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<Unit, WalletsWithExchangeRates>(postExecutionThread) {

    override fun build(params: Unit?): Flow<WalletsWithExchangeRates> {
        return combine(streamWallets.build(), streamBaseFiat.build()) { wallets, baseFiatCurrency ->
            return@combine Pair(wallets, baseFiatCurrency)
        }.map { data ->
            calculateExchangeRateForWallets(data)
        }
    }

    private suspend fun calculateExchangeRateForWallets(
        data: Pair<List<Wallet>, BaseFiatCurrency>
    ): WalletsWithExchangeRates {
        val wallets = data.first
        val walletCoinIds = wallets.map { it.currencyCode }
        val fiatCurrencyCode = data.second
        val exchangeRates =
            fetchExchangeRates(FetchExchangeRates.Params.make(walletCoinIds, fiatCurrencyCode)).orEmpty()
        return WalletsWithExchangeRates(data.first, exchangeRates, fiatCurrencyCode)
    }
}
