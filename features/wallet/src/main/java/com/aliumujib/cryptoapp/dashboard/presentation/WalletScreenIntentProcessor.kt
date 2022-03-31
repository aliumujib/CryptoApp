package com.aliumujib.cryptoapp.dashboard.presentation

import com.aliumujib.cryptoapp.presentation.base.InvalidViewIntentException
import com.aliumujib.cryptoapp.presentation.base.ViewIntent
import com.aliumujib.cryptoapp.coremodels.Wallet
import com.aliumujib.cryptoapp.dashboard.domain.StreamBaseFiat
import com.aliumujib.cryptoapp.dashboard.domain.FetchExchangeRates
import com.aliumujib.cryptoapp.dashboard.domain.StreamWallets
import com.aliumujib.cryptoapp.uicommons.orEmpty
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class WalletScreenIntentProcessor @Inject constructor(
    private val streamWallets: StreamWallets,
    private val streamBaseFiat: StreamBaseFiat,
    private val fetchExchangeRate: FetchExchangeRates
) : WalletIntentProcessor {

    override fun intentToResult(viewIntent: ViewIntent): Flow<WalletScreenResult> {
        return when (viewIntent) {
            is FetchWalletsIntent -> streamCryptoWallets()
            else -> throw InvalidViewIntentException(viewIntent)
        }
    }

    private fun streamCryptoWallets(): Flow<WalletScreenResult> {
        return combine(streamWallets.build(), streamBaseFiat.build()) { wallets, baseFiatCurrency ->
            return@combine Pair(wallets, baseFiatCurrency)
        }.map<Pair<List<Wallet>, BaseFiatCurrency>, WalletScreenResult.LoadWalletsResult> { data ->
            calculateResults(data)
        }.onStart {
            emit(WalletScreenResult.LoadWalletsResult.Loading)
        }.catch { throwable ->
            emit(WalletScreenResult.LoadWalletsResult.LoadingError(throwable))
        }
    }

    private suspend fun calculateResults(
        data: Pair<List<Wallet>, BaseFiatCurrency>
    ): WalletScreenResult.LoadWalletsResult.LoadedWalletsResult {
        val wallets = data.first
        val walletCoinIds = wallets.map { it.currencyCode }
        val fiatCurrencyCode = data.second
        val exchangeRates =
            fetchExchangeRate(FetchExchangeRates.Params.make(walletCoinIds, fiatCurrencyCode)).orEmpty()
        return WalletScreenResult.LoadWalletsResult.LoadedWalletsResult(
            data.first,
            fiatCurrencyCode,
            exchangeRates
        )
    }

}
