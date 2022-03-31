package com.aliumujib.cryptoapp.dashboard.presentation


import com.aliumujib.cryptoapp.presentation.statemachine.RenderStrategy
import com.aliumujib.cryptoapp.dashboard.presentation.state.WalletScreenState
import javax.inject.Inject

class WalletScreenStateMachine @Inject constructor(
    intentProcessor: WalletIntentProcessor,
    reducer: WalletStateReducer
) : WalletStateMachine(
    intentProcessor = intentProcessor,
    reducer = reducer,
    initialState = WalletScreenState.Initial,
    initialIntent = FetchWalletsIntent,
    renderStrategy = RenderStrategy.Latest
)
