package com.aliumujib.cryptoapp.dashboard.presentation

import com.aliumujib.cryptoapp.dashboard.presentation.state.WalletScreenState
import com.aliumujib.cryptoapp.presentation.base.IntentProcessor
import com.aliumujib.cryptoapp.presentation.base.StateReducer
import com.aliumujib.cryptoapp.presentation.statemachine.StateMachine
import com.aliumujib.cryptoapp.presentation.view.ComponentManager

typealias BaseFiatCurrency = String

typealias ExchangeRates = Map<String, Double>

typealias WalletIntentProcessor =
    @JvmSuppressWildcards IntentProcessor<WalletScreenResult>

typealias WalletStateReducer =
    @JvmSuppressWildcards StateReducer<WalletScreenState, WalletScreenResult>

typealias WalletStateMachine =
    @JvmSuppressWildcards StateMachine<WalletScreenState, WalletScreenResult>

typealias WalletComponentManager =
    @JvmSuppressWildcards ComponentManager<WalletScreenState, WalletScreenResult>
