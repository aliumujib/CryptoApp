package com.aliumujib.cryptoapp.dashboard.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    stateMachine: WalletScreenStateMachine,
) : WalletComponentManager(stateMachine)