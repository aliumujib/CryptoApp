package com.aliumujib.cryptoapp.dashboard.di

import com.aliumujib.cryptoapp.dashboard.presentation.WalletIntentProcessor
import com.aliumujib.cryptoapp.dashboard.presentation.WalletScreenIntentProcessor
import com.aliumujib.cryptoapp.dashboard.presentation.WalletScreenStateMachine
import com.aliumujib.cryptoapp.dashboard.presentation.WalletScreenStateReducer
import com.aliumujib.cryptoapp.dashboard.presentation.WalletStateMachine
import com.aliumujib.cryptoapp.dashboard.presentation.WalletStateReducer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
interface WalletsPresentationModule {

    @Binds
    fun bindsIntentProcessor(intentProcessor: WalletScreenIntentProcessor): WalletIntentProcessor

    @Binds
    fun bindsStateReducer(intentProcessor: WalletScreenStateReducer): WalletStateReducer

    @Binds
    fun bindsStateMachine(intentProcessor: WalletScreenStateMachine): WalletStateMachine

}
