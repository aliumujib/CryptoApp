package com.aliumujib.cryptoapp.dashboard.presentation

import com.aliumujib.cryptoapp.dashboard.domain.StreamWalletsAndExchangeRates
import com.aliumujib.cryptoapp.presentation.base.InvalidViewIntentException
import com.aliumujib.cryptoapp.presentation.base.ViewIntent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class WalletScreenIntentProcessor @Inject constructor(
    private val streamWalletsAndExchangeRates: StreamWalletsAndExchangeRates
) : WalletIntentProcessor {

    override fun intentToResult(viewIntent: ViewIntent): Flow<WalletScreenResult> {
        return when (viewIntent) {
            is FetchWalletsIntent -> streamCryptoWallets()
            else -> throw InvalidViewIntentException(viewIntent)
        }
    }

    private fun streamCryptoWallets(): Flow<WalletScreenResult> {
        return streamWalletsAndExchangeRates.build()
            .map {
                WalletScreenResult.LoadWalletsResult.LoadedWalletsResult(it) as WalletScreenResult
            }.onStart {
                emit(WalletScreenResult.LoadWalletsResult.Loading)
            }.catch { throwable ->
                emit(WalletScreenResult.LoadWalletsResult.LoadingError(throwable))
            }
    }

}
