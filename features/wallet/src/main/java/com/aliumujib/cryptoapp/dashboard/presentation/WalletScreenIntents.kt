package com.aliumujib.cryptoapp.dashboard.presentation

import com.aliumujib.cryptoapp.presentation.base.ViewIntent

sealed interface WalletScreenIntent : ViewIntent
object FetchWalletsIntent : WalletScreenIntent
