package com.aliumujib.cryptoapp.dashboard.presentation

import com.aliumujib.cryptoapp.presentation.base.ViewResult
import com.aliumujib.cryptoapp.coremodels.ExchangeRate
import com.aliumujib.cryptoapp.coremodels.Wallet

sealed class WalletScreenResult : ViewResult {

    sealed class LoadWalletsResult : WalletScreenResult() {
        object Loading : LoadWalletsResult()
        data class LoadingError(val throwable: Throwable) : LoadWalletsResult()
        data class LoadedWalletsResult(
            val wallets: List<Wallet>,
            val baseFiatCurrency: BaseFiatCurrency,
            val exchangeRates: ExchangeRates
        ) : LoadWalletsResult()
    }
}
