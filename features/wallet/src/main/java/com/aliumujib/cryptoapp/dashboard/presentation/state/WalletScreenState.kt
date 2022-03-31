package com.aliumujib.cryptoapp.dashboard.presentation.state

import com.aliumujib.cryptoapp.presentation.base.ScreenState


sealed class WalletScreenState(
    val walletHeaderState: HeaderViewState,
    val walletListState: WalletListState
) : ScreenState {

    object Initial : WalletScreenState(
        walletHeaderState = HeaderViewState.Initial,
        walletListState = WalletListState.Initial
    )

    data class Loading(
        val headerViewState: HeaderViewState,
        val walletListViewState: WalletListState
    ) : WalletScreenState(
        walletHeaderState = headerViewState,
        walletListState = walletListViewState
    )

    data class Error(
        val headerViewState: HeaderViewState,
        val walletListViewState: WalletListState
    ) : WalletScreenState(
        walletHeaderState = headerViewState,
        walletListState = walletListViewState
    )

    data class Loaded(
        val headerViewState: HeaderViewState,
        val walletListViewState: WalletListState
    ) : WalletScreenState(
        walletHeaderState = headerViewState,
        walletListState = walletListViewState
    )
}
