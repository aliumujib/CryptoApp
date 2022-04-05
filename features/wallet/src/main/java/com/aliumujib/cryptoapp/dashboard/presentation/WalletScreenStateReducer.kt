package com.aliumujib.cryptoapp.dashboard.presentation

import com.aliumujib.cryptoapp.dashboard.mapper.WalletModelMapper
import com.aliumujib.cryptoapp.dashboard.presentation.state.HeaderViewState
import com.aliumujib.cryptoapp.dashboard.presentation.state.WalletListState
import com.aliumujib.cryptoapp.dashboard.presentation.state.WalletScreenState
import com.aliumujib.cryptoapp.uicommons.errorMessage
import com.aliumujib.cryptoapp.uicommons.orZero
import javax.inject.Inject

class WalletScreenStateReducer @Inject constructor(
    private val walletModelMapper: WalletModelMapper
) : WalletStateReducer {

    override fun reduce(
        oldState: WalletScreenState,
        result: WalletScreenResult
    ): WalletScreenState = when (result) {
        is WalletScreenResult.LoadWalletsResult.LoadedWalletsResult -> {
            val walletModels = walletModelMapper.mapToModel(result.data)

            val totalWalletBalance = walletModels.sumOf { it.fiatAmount }

            val walletListViewState = WalletListState.Loaded(
                data = walletModels
            )

            val walletHeaderState = HeaderViewState.Loaded(
                amount = totalWalletBalance, baseFiatCurrency = result.data.baseFiatCurrency
            )

            WalletScreenState.Loaded(
                headerViewState = walletHeaderState,
                walletListViewState = walletListViewState
            )
        }
        WalletScreenResult.LoadWalletsResult.Loading -> {
            WalletScreenState.Loading(
                headerViewState = HeaderViewState.Loading,
                walletListViewState = WalletListState.Loading(oldState.walletListState.resultState.data)
            )
        }
        is WalletScreenResult.LoadWalletsResult.LoadingError -> {
            val walletHeaderViewState = HeaderViewState.Error(
                message = result.throwable.errorMessage
            )
            val walletListViewState = WalletListState.Error(
                message = result.throwable.errorMessage
            )
            WalletScreenState.Error(
                headerViewState = walletHeaderViewState,
                walletListViewState = walletListViewState
            )
        }
    }
}
