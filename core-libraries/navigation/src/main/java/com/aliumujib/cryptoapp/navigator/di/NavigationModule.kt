package com.aliumujib.cryptoapp.navigator.di

import com.aliumujib.cryptoapp.dashboard.navigation.WalletNavigator
import com.aliumujib.cryptoapp.navigator.WalletNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@[Module InstallIn(FragmentComponent::class)]
interface NavigationModule {

    @Binds
    fun bindsWalletNavigator(walletNavigatorImpl: WalletNavigatorImpl): WalletNavigator
}
